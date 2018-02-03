package org.usfirst.frc.team5003.robot;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team5003.robot.subsystems.AnalogSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.EncoderSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.GyroSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.ProtectedControllerSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.ControllerSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static OI oi;
	public static Joystick xbox;
	
	public static GrabberSubsystem grabber;

	public static ControllerSubsystem controller;
	
	public static ControllerSubsystem actuatorController;
	public static AnalogSubsystem actuatorPot;
	
	public static ControllerSubsystem gearController;
	public static AnalogSubsystem gearPot;
	
	public static GyroSubsystem gyroSub;
	
	public static DrivetrainSubsystem drivetrain;
	
	public static ProtectedControllerSubsystem protectedController;
	
	public static EncoderSubsystem encoder;
	
	public static int GrabberAPwm = 8;
	public static int GrabberBPwm = 9;
	
	public static int ControllerPwm = 5;
	
	public static int ActuatorControllerPwm = 6;
	public static int ActuatorPotAnalog = 0;
	
	public static int GearControllerPwm = 7;
	public static int GearPotAnalog = 1;
	
	public static int ProtectedControllerPwm = 4;
	public static int ProtectedControllerADio = 0;
	public static int ProtectedControllerBDio = 1;
	public static int ProtectedControllerXDio = 2;
	public static int ProtectedControllerPotAnalog = 2;
	public static int ProtectedControllerSw0Dio = 0;
	public static int ProtectedControllerSw1Dio = 1;
	
	public static int Left0Pwm = 0;
	public static int Left1Pwm = 1;
	public static int Right0Pwm = 2;
	public static int Right1Pwm = 3;
	
	
	
	
	
	@Override
	public void robotInit() {
		try
		{
			//xbox = new XboxController(0);
			xbox = new Joystick(0);
			
			//drivetrain = new DrivetrainSubsystem(Left0Pwm, Left1Pwm, Right0Pwm, Right1Pwm);
			
			grabber = new GrabberSubsystem(GrabberAPwm, GrabberBPwm);
			
//			protectedController = new ProtectedControllerSubsystem(
//					ProtectedControllerPwm,
//					ProtectedControllerADio, 
//					ProtectedControllerBDio, 
//					ProtectedControllerXDio,
//					-2048, 2048);
//			protectedController = new ProtectedControllerSubsystem(
//					ProtectedControllerPwm,
//					ProtectedControllerPotAnalog,
//					1, 4);
			protectedController = new ProtectedControllerSubsystem(
					ProtectedControllerPwm,
					ProtectedControllerSw0Dio,
					ProtectedControllerSw1Dio);
			
			//controller = new ControllerSubsystem(ControllerPwm);
			
			actuatorController = new ControllerSubsystem(ActuatorControllerPwm);
			actuatorPot = new AnalogSubsystem(ActuatorPotAnalog);
			
			gearController = new ControllerSubsystem(GearControllerPwm);
			gearPot = new AnalogSubsystem(GearPotAnalog);
			//encoder = new EncoderSubsystem(0, 1, 2);
			oi = new OI();
			
		}
		catch (Exception ex)
		{
			Robot.log("\r\n********** EXCEPTION ************.\r\nMessage: " + ex.getMessage() + "\r\nCause: " + ex.getCause() + "\r\nClass: " + ex.getClass() + "\r\nStack: " + ex.getStackTrace() + "\r\nnuts ends");
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
		//SmartDashboard.putString("Driver Station Message is", "[" + DriverStation.getInstance().getGameSpecificMessage() + "]");
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		//SmartDashboard.putNumber("Joy X", joystick.getX());
		//SmartDashboard.putNumber("Joy Y", joystick.getY());
		//gyroSub.show();
		
		//grabber.show();
		
//		armTalonSub.show();
//		armPotSub.show();
//		armAccSub.show();
//		armSwitchASub.show();
//		armSwitchBSub.show();
		
		//protectedController.show();
		
		//actuator.show();
		//gear.show();
		
		//encoder.show();
		//gearPot.show();
		
		protectedController.show();
		
		SmartDashboard.putNumber("x", xbox.getX());
		
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
