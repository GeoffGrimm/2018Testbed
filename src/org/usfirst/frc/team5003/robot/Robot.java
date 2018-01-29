package org.usfirst.frc.team5003.robot;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team5003.robot.subsystems.AnalogSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.DigitalInputAccumulator;
import org.usfirst.frc.team5003.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.EmptySubsystem;
import org.usfirst.frc.team5003.robot.subsystems.GyroSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.LimitSwitchWithCounterSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.SingleTalonSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.ThreeChannelEncoderSubsystem;
import org.usfirst.frc.team5003.robot.subsystems.UltrasonicSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static OI oi;
	public static Joystick joystick;
	public static DrivetrainSubsystem drivetrainSub;
	public static SingleTalonSubsystem armTalonSub;
	public static LimitSwitchWithCounterSubsystem armSwitchASub;
	public static LimitSwitchWithCounterSubsystem armSwitchBSub;
	public static AnalogSubsystem armPotSub;
	public static UltrasonicSubsystem ultrasonicSub;
	public static DigitalInputAccumulator armAccSub;
	public static ThreeChannelEncoderSubsystem threeChannelEncoderSub;
	
	public static GrabberSubsystem grabberSub;
	public static GyroSubsystem gyroSub;
	public static EmptySubsystem emptySub;
	
	@Override
	public void robotInit() {
		try
		{
			joystick = new Joystick(0);

			grabberSub = new GrabberSubsystem(8,9);
			
			armTalonSub = new SingleTalonSubsystem(3);
			armSwitchASub = new LimitSwitchWithCounterSubsystem(3);
			armSwitchBSub = new LimitSwitchWithCounterSubsystem(4);
			armPotSub = new AnalogSubsystem(0);
			armAccSub = new DigitalInputAccumulator(0);
			threeChannelEncoderSub = new ThreeChannelEncoderSubsystem(1,2,3);
			
			//drivetrainSub = new DrivetrainSubsystem();
			
			gyroSub = new GyroSubsystem();
//			emptySub = new EmptySubsystem();

			//encoderSub = new ThreeChannelEncoderSubsystem(0,1,2);
			//ultrasonicSub = new UltrasonicSubsystem();
			
//			SmartDashboard.putData(Scheduler.getInstance());
//			SmartDashboard.putData(servoSub);
//	    	SmartDashboard.putData(gyroSub);
//	    	SmartDashboard.putData(emptySub);
//	    	SmartDashboard.putData(talonSub);
	    	
			
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
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("Joy X", joystick.getX());
		SmartDashboard.putNumber("Joy Y", joystick.getY());
		grabberSub.show();
		gyroSub.show();
		armTalonSub.show();
		armSwitchASub.show();
		armPotSub.show();
		threeChannelEncoderSub.show();
		//ultrasonicSub.show();
		//drivetrainSub.show();
	}

	@Override
	public void testPeriodic() {
	}
	public static void log(String s){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date()) + "  " + s);
    }
}
