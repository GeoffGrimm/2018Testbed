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
	
	public static GrabberSubsystem grabberSub;

	public static SingleTalonSubsystem armTalonSub;
	public static AnalogSubsystem armPotSub;
	public static DigitalInputAccumulator armAccSub;
	public static LimitSwitchWithCounterSubsystem armSwitchASub;
	public static LimitSwitchWithCounterSubsystem armSwitchBSub;

	public static GyroSubsystem gyroSub;
	public static ThreeChannelEncoderSubsystem threeChannelEncoderSub;
	
	public static EmptySubsystem emptySub;
	public static DrivetrainSubsystem drivetrainSub;
	public static UltrasonicSubsystem ultrasonicSub;

	
	@Override
	public void robotInit() {
		try
		{
			joystick = new Joystick(0);
			gyroSub = new GyroSubsystem();
			
			grabberSub = new GrabberSubsystem(8,9);
			
			armTalonSub = new SingleTalonSubsystem(0);
			armPotSub = new AnalogSubsystem(0);
			armAccSub = new DigitalInputAccumulator(0);
			armSwitchASub = new LimitSwitchWithCounterSubsystem(8);
			armSwitchBSub = new LimitSwitchWithCounterSubsystem(9);
						
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
		SmartDashboard.putNumber("Joy X", joystick.getX());
		SmartDashboard.putNumber("Joy Y", joystick.getY());
		gyroSub.show();
		
		grabberSub.show();
		
		armTalonSub.show();
		armPotSub.show();
		armAccSub.show();
		armSwitchASub.show();
		armSwitchBSub.show();
		
		//////////////////////////////////
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
	public static void log(String s){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date()) + "  " + s);
    }
}
