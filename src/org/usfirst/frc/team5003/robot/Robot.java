package org.usfirst.frc.team5003.robot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.usfirst.frc.team5003.robot.subsystems.AnalogSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.EncoderSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.GyroSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.ProtectedControllerSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.GrabberSubsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static OI oi;
	public static XboxController xbox;
	
	public static GrabberSubsystem grabber;
	public static ArmSubsystem arm;

	public static ProtectedControllerSubsystem actuatorController;
	public static AnalogSubsystem actuatorPot;
	
	public static ProtectedControllerSubsystem gearController;
	public static AnalogSubsystem gearPot;
	
	public static GyroSubsystem gyro;
	
	public static DrivetrainSubsystem drivetrain;
	
	public static final int GrabberAPwm = 7;
	public static final int GrabberBPwm = 9;
	
	public static final int ActuatorControllerPwm = 6;
	public static final int ActuatorPotCh = 0;
	
	public static final int GearControllerPwm = 8;
	public static final int GearPotCh = 1;
	
	public static final int Left0Pwm = 1;
	public static final int Left1Pwm = 2;
	public static final int Right0Pwm = 3;
	public static final int Right1Pwm = 4;
	
	public static final double ActuatorPotNegativeLimit = 2.335;
	public static final double ActuatorPotPositiveLimit = 3.804;
	public static final double GearPotNegativeLimit = 2.698;
	public static final double GearPotPositiveLimit = 1.150;
	
	public static final int DRIVE_AXIS = 1;
	public static final int TURN_AXIS = 4;
	public static final double DRIVE_POWER = 1.0;
	
	public static final String AutoSwitchX = "SWX";
	public static final String AutoSwitchY = "SWY";
	public static final String AutoScaleX = "SCX";
	public static final String AutoScaleY = "SCY";
	public static final String AutoCenterX = "CEX";
	public static final String AutoRotateLeft = "RL";
	public static final String AutoRotateRight = "RR";
	public static final String AutoRotate180 = "R180";
	public static final String AutoUpScale = "USC";
	public static final String AutoUpSwitch = "USW";
	public static final String AutoClawRelease = "REL";
	public static final String AutoDone = "DONE";
	public ArrayList<String> autoCommands;
	public int autoIndex;
	
	
	long autonomousStart = -1;
	long commandStart = -1;
	String autonomousCommand;
	String autonmousMessage = null;
	
	@Override
	public void robotInit() {
		try
		{
			xbox = new XboxController(0);
			
			grabber = new GrabberSubsystem(GrabberAPwm, GrabberBPwm);
			
			Talon talon = new Talon(ActuatorControllerPwm);
			Victor victor = new Victor(GearControllerPwm);
			
			actuatorController = new ProtectedControllerSubsystem("Actuator", talon);//, ActuatorPotCh, ActuatorPotMin, ActuatorPotMax);
			gearController = new ProtectedControllerSubsystem("Gear", victor);//, GearPotCh, GearPotMin, GearPotMax);
			gearPot = new AnalogSubsystem(GearPotCh);
			actuatorPot = new AnalogSubsystem(ActuatorPotCh);
			
			//actuatorController = new ProtectedControllerSubsystem("Actuator", talon, ActuatorPotCh, ActuatorPotNegativeLimit, ActuatorPotPositiveLimit);
			//gearController = new ProtectedControllerSubsystem("Gear", victor, GearPotCh, GearPotNegativeLimit, GearPotPositiveLimit);

			
			arm = new ArmSubsystem(talon, ActuatorPotCh, victor, GearPotCh);
			
			gyro = new GyroSubsystem();

			// = new DrivetrainSubsystem(Left0Pwm, Left1Pwm, Right0Pwm, Right1Pwm);
			//dolores
			//drivetrain = new DrivetrainSubsystem(3,7,2,6);
			
			oi = new OI();
		
		}
		catch (Exception ex)
		{
			Robot.log("\r\n********** robotInit() EXCEPTION ************\r\nMessage: " + ex.getMessage() + "\r\nCause: " + ex.getCause() + "\r\nClass: " + ex.getClass() + "\r\nStack: " + ex.getStackTrace() + "\r\nnuts ends");
		}
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		String scaleLocations = DriverStation.getInstance().getGameSpecificMessage();
		Robot.log(String.format("Driver Station Message is [%s]", scaleLocations));
		
		if (scaleLocations.length() != 3)
			return;
		scaleLocations.toUpperCase();
		
		String nearSwitch = scaleLocations.substring(0,  0);
		String scale = scaleLocations.substring(1, 1);
		String farSwitch = scaleLocations.substring(2, 2);
		
		String location = OI.botLocationChooser.getSelected();
		location.toUpperCase();
		//String location = nearSwitch;
		
		autoCommands = new ArrayList<String>();
		
		if (location == nearSwitch)
		{
			autoCommands.add(AutoSwitchX);
			if (location == "R")
				autoCommands.add(AutoRotateLeft);
			else
				autoCommands.add(AutoRotateRight);
			autoCommands.add(AutoUpSwitch);
			autoCommands.add(AutoSwitchY);
			autoCommands.add(AutoClawRelease);
		}
		else if (location == scale) 
		{
			autoCommands.add(AutoScaleX);
			if (location == "R")
				autoCommands.add(AutoRotateLeft);
			else
				autoCommands.add(AutoRotateRight);
			autoCommands.add(AutoUpScale);
			autoCommands.add(AutoScaleY);
			autoCommands.add(AutoClawRelease);
		}
		// we are at L or R position, but both switch and scale are on other side
		else if (location == "L" || location == "R") 
		{
			autoCommands.add(AutoSwitchX);
		}
		else if (location == "C") {
			autoCommands.add(AutoCenterX);
			if (nearSwitch == "R") {
				autoCommands.add(AutoClawRelease);
			}
		}
		autoCommands.add(AutoDone);
		autoIndex = 0;
			
			
		// driver station get location doesn't seem to work
		// Robot.log(String.format("Driver Station Number %d", DriverStation.getInstance().getLocation()));
	}

	@Override
	public void autonomousPeriodic() 
	{
		Scheduler.getInstance().run();
				
		// cheezy autonomous for use in non-command based robots
		long current = System.currentTimeMillis();
		
		// rough autonomous
		if (autonomousStart == -1)
		{
			autonomousStart = current;
			autoIndex++;
			Robot.log(autoCommands.get(autoIndex));
			commandStart = current;
		}
		
		long commandElapsed = current - commandStart;
		double[] values = new double[2];
		boolean increment = false;
		if (getDriveParameters(autoCommands.get(autoIndex), values))
		{
			if (commandElapsed < values[1]) 
			{
				//drivetrain.arcadeDrive(values[0], 0.0);
			}
			else 
			{
				increment = true;
			}
		}
		else if (autoCommands.get(autoIndex) == AutoRotateLeft || 
				autoCommands.get(autoIndex) == AutoRotateRight)
		{
			double power = 0.5;
			if (autoCommands.get(autoIndex) == AutoRotateLeft)
				power = -power;
			if (Math.abs(gyro.getAngle()) < 90)
			{
				//drivetrain.arcadeDrive(0, power);
			}
			else
			{
				increment = true;
			}
		}
		else if (autoCommands.get(autoIndex) == AutoUpSwitch || 
				autoCommands.get(autoIndex) == AutoUpScale)
		{
			// todo: run until reaches pot settings
			int duration;
			if (autoCommands.get(autoIndex) == AutoUpSwitch)
				duration = 1000;
			else
				duration = 3000;
			if (commandElapsed < duration) 
			{
				//arm.set(0.60, -0.60);
			}
			else 
			{
				increment = true;
			}		
		}
		else if (autoCommands.get(autoIndex) == AutoClawRelease)
		{
			grabber.set(0.8);
			increment = true;
		}
		else if (autoCommands.get(autoIndex) == AutoDone)
			increment = false;
		else
		{
			Robot.log("Unrecognized autonomous command " + autoCommands.get(autoIndex));
			increment = true;
		}
			
		
		
		if (increment)
		{
			autoIndex++;
			Robot.log(autoCommands.get(autoIndex));
			commandStart = current;
		}
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		
		try {
			if (gyro != null)
				gyro.show();
			if (grabber != null)
				grabber.show();
			if (gearController != null)
				gearController.show();
			if (actuatorController != null)
				actuatorController.show();
			if (actuatorPot != null)
				actuatorPot.show();
			if (gearPot != null)
				gearPot.show();
			if (arm != null)
				arm.show();
		}
		catch (Exception ex){
			Robot.log("custom code in teleopPeriodic is broken!");
		}
		
	

		//////////////////////////////////
		Scheduler.getInstance().run();
		//////////////////////////////////
	}

	@Override
	public void testPeriodic() {
	}
	
	
	public static void log(String s){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date()) + "  " + s);
    }
	
	public boolean getDriveParameters(String command,  double[] values)
	{
		boolean isDrive = true;
		if (command == AutoScaleX)
		{
			values[0] = 0.5;
			values[1] = 4;
		}
		else if (command == AutoSwitchX)
		{
			values[0] = 0.5;
			values[1] = 2;
		}
		else if (command == AutoCenterX)
		{
			values[0] = 0.5;
			values[1] = 1;
		}
		else if (command == AutoScaleY)
		{
			values[0] = 0.3;
			values[1] = 0.5;
		}
		else if (command == AutoSwitchY)
		{
			values[0] = 0.3;
			values[1] = 0.25;
		}
		else
			isDrive = false;
		
		return isDrive;
	}
}
