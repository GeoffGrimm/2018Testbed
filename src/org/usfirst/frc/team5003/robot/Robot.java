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

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static OI oi;
	public static Joystick xbox;
	
	public static GrabberSubsystem grabber;

	public static ControllerSubsystem actuatorController;
	public static AnalogSubsystem actuatorPot;
	
	public static ControllerSubsystem gearController;
	public static AnalogSubsystem gearPot;
	
	public static GyroSubsystem gyro;
	
	public static DrivetrainSubsystem drivetrain;
	
	//public static ProtectedControllerSubsystem protectedController;
	
	public static int GrabberAPwm = 8;
	public static int GrabberBPwm = 9;
	
	public static int ControllerPwm = 5;
	
	public static int ActuatorControllerPwm = 6;
	public static int ActuatorPotAnalog = 0;
	
	public static int GearControllerPwm = 7;
	public static int GearPotAnalog = 1;
	
	public static int ProtectedControllerPwm = 4;
	public static int ProtectedControllerADio = 7;
	public static int ProtectedControllerBDio = 8;
	public static int ProtectedControllerXDio = 9;
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
			
			grabber = new GrabberSubsystem(GrabberAPwm, GrabberBPwm);
			
			actuatorController = new ControllerSubsystem(ActuatorControllerPwm);
			actuatorPot = new AnalogSubsystem(ActuatorPotAnalog);
			
			gearController = new ControllerSubsystem(GearControllerPwm);
			gearPot = new AnalogSubsystem(GearPotAnalog);
			
			//gyro = new GyroSubsystem();
			//drivetrain = new DrivetrainSubsystem(Left0Pwm, Left1Pwm, Right0Pwm, Right1Pwm);
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

		gyro.show();
		
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


//protectedController = new ProtectedControllerSubsystem(
//ProtectedControllerPwm,
//ProtectedControllerADio, 
//ProtectedControllerBDio, 
//ProtectedControllerXDio,
//-20480, 20480);
//protectedController = new ProtectedControllerSubsystem(
//ProtectedControllerPwm,
//ProtectedControllerPotAnalog,
//1, 4);
//protectedController = new ProtectedControllerSubsystem(
//ProtectedControllerPwm,
//ProtectedControllerSw0Dio,
//ProtectedControllerSw1Dio);

//controller = new ControllerSubsystem(ControllerPwm);