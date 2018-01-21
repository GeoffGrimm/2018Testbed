package org.usfirst.frc.team5003.robot;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team5003.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.EmptySubsystem;
import org.usfirst.frc.team5003.robot.subsystems.ServoSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static OI oi;
	public static Joystick joystick;
	public static ServoSubsystem servoSub;
	public static DrivetrainSubsystem drivetrainSub;
	public static EmptySubsystem emptySubsystem;
	
	@Override
	public void robotInit() {
		try
		{
			oi = new OI();
			joystick = new Joystick(0);
			servoSub = new ServoSubsystem();
			//drivetrainSub = new DrivetrainSubsystem();
			emptySubsystem = new EmptySubsystem();
			
			SmartDashboard.putData(Scheduler.getInstance());
	    	SmartDashboard.putData(servoSub.servoA);
	    	SmartDashboard.putData(servoSub.servoB);
			
		}
		catch (Exception ex)
		{
			Robot.log("\r\n" + ex.getMessage() + "\r\n" + ex.getStackTrace());
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
		SmartDashboard.putString("Driver Station Message", DriverStation.getInstance().getGameSpecificMessage());
		
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
	}

	@Override
	public void testPeriodic() {
	}
	public static void log(String s){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date()) + "  " + s);
    }
}
