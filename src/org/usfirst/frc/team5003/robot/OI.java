package org.usfirst.frc.team5003.robot;

import org.usfirst.frc.team5003.robot.commands.DriveStraightWithGyroCommand;
import org.usfirst.frc.team5003.robot.commands.ExtendArmCommand;
import org.usfirst.frc.team5003.robot.commands.GroupBuilderCommand;
import org.usfirst.frc.team5003.robot.commands.ResetLimitSwitchWithCounterCommand;
import org.usfirst.frc.team5003.robot.commands.RotateWithGyroCommand;
import org.usfirst.frc.team5003.robot.commands.GrabberCommand;
import org.usfirst.frc.team5003.robot.commands.DriveWithDurationAndPowerCommand;
import org.usfirst.frc.team5003.robot.commands.DriveWithPowerCommand;
import org.usfirst.frc.team5003.robot.commands.TalkativeCommand;
import org.usfirst.frc.team5003.robot.commands.ResetEncoderOnControllerCommand;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	
	public JoystickButton bOpen;
	public JoystickButton bClose;
	public JoystickButton b3, b4, b5, b6;
	
	public OI() {
		
		//SmartDashboard.putData("Talkative Command On Click", new TalkativeCommand("click", 15));
		
		bOpen = new JoystickButton(Robot.joystick, 7);
		bClose = new JoystickButton(Robot.joystick, 8);
		

			
		if (Robot.grabber.isGood) {
			SmartDashboard.putNumber("Grabber Value",       0.5);
			SmartDashboard.putNumber("Grabber Open Value",  0.2);
			SmartDashboard.putNumber("Grabber Close Value", 0.8);

			SmartDashboard.putData("Grabber",      new GrabberCommand("Grabber Value"));
			SmartDashboard.putData("Grabber Open",   new GrabberCommand("Grabber Open Value"));
			SmartDashboard.putData("Grabber Close",  new GrabberCommand("Grabber Close Value"));
			
			bOpen.whenPressed(new GrabberCommand("Grabber Open Value"));
			bClose.whenPressed(new GrabberCommand("Grabber Close Value"));
		}
		
		if (Robot.protectedController.isGood && Robot.protectedController.enc != null) {
			SmartDashboard.putData("Reset Controller Encoder", new ResetEncoderOnControllerCommand());
		}
		
		b3 = new JoystickButton(Robot.joystick, 3);
		b4 = new JoystickButton(Robot.joystick, 4);
		b5 = new JoystickButton(Robot.joystick, 5);
		b6 = new JoystickButton(Robot.joystick, 6);		
		if (Robot.actuator.isGood) {
			SmartDashboard.putNumber("Actuator Power",  0.1);
			b3.whenPressed(new DriveWithPowerCommand(Robot.actuator, "Actuator Power", +1));
			b4.whenPressed(new DriveWithPowerCommand(Robot.actuator, "Actuator Power", -1));
		}
		if (Robot.gear.isGood) {
			SmartDashboard.putNumber("Gear Power",  0.1);
			b5.whenPressed(new DriveWithPowerCommand(Robot.gear, "Gear Power", +1));
			b6.whenPressed(new DriveWithPowerCommand(Robot.gear, "Gear Power", -1));
		}

//		if (Robot.armTalonSub.isGood) {
//			SmartDashboard.putNumber("Talon Duration Value", 2);
//			SmartDashboard.putNumber("Talon Power Value", 0.1);
//			SmartDashboard.putData("Talon", new SingleTalonCommand("Talon Duration Value", "Talon Power Value"));
//		}
//		
//		if (Robot.armSwitchASub.isGood)
//			SmartDashboard.putData("Reset Limit Switch", new ResetLimitSwitchWithCounterCommand());
//
//				
//		if (Robot.armTalonSub.isGood &&
//			Robot.armPotSub.isGood &&
//			Robot.armAccSub.isGood)
//		{
//			SmartDashboard.putNumber("Extend Arm Value", 2.5);
//			SmartDashboard.putData("Extend Arm To", new ExtendArmCommand("Extend Arm Value"));
//		}

//		if (Robot.gyroSub.isGood) {
//			SmartDashboard.putNumber("Rotate Value", 90);
//			SmartDashboard.putData("Rotate", new RotateWithGyroCommand("Rotate Value"));
//		}


//		if (Robot.drivetrainSub.isGood && Robot.gyroSub.isGood)
//		{
//			SmartDashboard.putNumber("Duration Value", 2);
//			SmartDashboard.putNumber("Power Value", 0.1);
//			SmartDashboard.putString("CommandString",  "P0.1, D2.0, R90");			
//			SmartDashboard.putData("Drive Straight", new DriveStraightWithGyroCommand("Duration Value", "Power Value"));
//			SmartDashboard.putData("Run Command String", new GroupBuilderCommand());
//		}
		
	}
}
