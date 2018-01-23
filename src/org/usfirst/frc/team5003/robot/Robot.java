package org.usfirst.frc.team5003.robot;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team5003.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.EmptySubsystem;
import org.usfirst.frc.team5003.robot.subsystems.GyroSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.ServoSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static OI oi;
	public static Joystick joystick;
	public static DrivetrainSubsystem drivetrainSub;
	
	public static ServoSubsystem servoSub;
	public static GyroSubsystem gyroSub;
	public static EmptySubsystem emptySub;
	
	@Override
	public void robotInit() {
		try
		{
			joystick = new Joystick(0);
			drivetrainSub = new DrivetrainSubsystem();
			servoSub = new ServoSubsystem();
			gyroSub = new GyroSubsystem();
			emptySub = new EmptySubsystem();
			
			SmartDashboard.putData(Scheduler.getInstance());
			SmartDashboard.putData(servoSub);
	    	SmartDashboard.putData(gyroSub);
			
			oi = new OI();
		}
		catch (Exception ex)
		{
			Robot.log("\r\nNuts.\r\nMessage: " + ex.getMessage() + "\r\nCause: " + ex.getCause() + "\r\nClass: " + ex.getClass() + "\r\nStack: " + ex.getStackTrace() + "\r\nnuts ends");
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
		SmartDashboard.putString("Driver Station Message is", "[" + DriverStation.getInstance().getGameSpecificMessage() + "]");
		
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
		Scheduler.getInstance().run();
		servoSub.show();
		gyroSub.show();
	}

	@Override
	public void testPeriodic() {
	}
	public static void log(String s){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date()) + "  " + s);
    }
}
