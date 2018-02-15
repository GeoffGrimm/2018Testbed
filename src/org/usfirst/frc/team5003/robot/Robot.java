package org.usfirst.frc.team5003.robot;

import java.text.SimpleDateFormat;
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
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static OI oi;
	public static Joystick xbox;
	
	public static GrabberSubsystem grabber;
	public static ArmSubsystem arm;

	public static ProtectedControllerSubsystem actuatorController;
	public static AnalogSubsystem actuatorPot;
	
	public static ProtectedControllerSubsystem gearController;
	public static AnalogSubsystem gearPot;
	
	public static GyroSubsystem gyro;
	
	public static DrivetrainSubsystem drivetrain;
	
	//public static ProtectedControllerSubsystem protectedController;
	
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
	
	public static final double ActuatorPotMin = 1;
	public static final double ActuatorPotMax = 4;
	public static final double GearPotMin = 1;
	public static final double GearPotMax = 4;
	
	@Override
	public void robotInit() {
		try
		{
			//xbox = new XboxController(0);
			xbox = new Joystick(0);
			
//			Relay relay = new Relay(3);
//			relay.setDirection(Direction.kForward);
//			relay.set(Value.kOn);
			
			grabber = new GrabberSubsystem(GrabberAPwm, GrabberBPwm);
			
			Talon talon = new Talon(ActuatorControllerPwm);
			Victor victor = new Victor(GearControllerPwm);
			
			arm = new ArmSubsystem(talon, ActuatorPotCh, victor, GearPotCh);
			
			actuatorController = new ProtectedControllerSubsystem("Actuator", talon, ActuatorPotCh, ActuatorPotMin, ActuatorPotMax);
			gearController = new ProtectedControllerSubsystem("Gear", victor, GearPotCh, GearPotMin, GearPotMax);
			
			gyro = new GyroSubsystem();
			
			drivetrain = new DrivetrainSubsystem(Left0Pwm, Left1Pwm, Right0Pwm, Right1Pwm);
			
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
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Robot.log(String.format("Driver Station Message is [%s]", DriverStation.getInstance().getGameSpecificMessage()));
		Robot.log(String.format("Driver Station Number %d", DriverStation.getInstance().getLocation()));
	}

	@Override
	public void teleopPeriodic() {
		
		try {
			if (gyro != null)
				gyro.show();
			if (grabber != null)
				grabber.show();
//			if (actuatorController != null)
//				actuatorController.show();
//			if (gearController != null)
//				gearController.show();
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
}
