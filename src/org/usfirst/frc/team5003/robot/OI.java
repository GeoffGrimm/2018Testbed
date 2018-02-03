package org.usfirst.frc.team5003.robot;

import org.usfirst.frc.team5003.robot.commands.DriveProtectedControllerToPositionCommand;
import org.usfirst.frc.team5003.robot.commands.DriveStraightWithGyroCommand;
import org.usfirst.frc.team5003.robot.commands.ExtendArmCommand;
import org.usfirst.frc.team5003.robot.commands.GroupBuilderCommand;
import org.usfirst.frc.team5003.robot.commands.RotateWithGyroCommand;
import org.usfirst.frc.team5003.robot.commands.GrabberCommand;
import org.usfirst.frc.team5003.robot.commands.DriveWithDurationAndPowerCommand;
import org.usfirst.frc.team5003.robot.commands.DriveWithPowerCommand;
import org.usfirst.frc.team5003.robot.commands.TalkativeCommand;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	
	public JoystickButton bOpen;
	public JoystickButton bClose;
	public JoystickButton bX, bY, bA, bB;
	
	public OI() {
		
		//SmartDashboard.putData("Talkative Command On Click", new TalkativeCommand("click", 15));
		
		bOpen = new JoystickButton(Robot.xbox, 5);
		bClose = new JoystickButton(Robot.xbox, 6);
			
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
		
		bA = new JoystickButton(Robot.xbox, 1);
		bB = new JoystickButton(Robot.xbox, 2);
		if (Robot.actuatorController.isGood) {
			SmartDashboard.putNumber("Actuator Power",  0.1);
			bA.whileHeld(new DriveWithPowerCommand(Robot.actuatorController, "Actuator Power", +1));
			bB.whileHeld(new DriveWithPowerCommand(Robot.actuatorController, "Actuator Power", -1));
		}
		
		bX = new JoystickButton(Robot.xbox, 3);
		bY = new JoystickButton(Robot.xbox, 4);
		if (Robot.gearController.isGood) {
			SmartDashboard.putNumber("Gear Power",  0.1);
			bX.whileHeld(new DriveWithPowerCommand(Robot.gearController, "Gear Power", +1));
			bY.whileHeld(new DriveWithPowerCommand(Robot.gearController, "Gear Power", -1));
		}
		
//		if (Robot.protectedController.isGood && Robot.protectedController.enc != null)
//		{
//			SmartDashboard.putNumber("Protected Controller Destination", 0.0);
//			SmartDashboard.putData("Go", new DriveProtectedControllerToPositionCommand(Robot.protectedController, "Protected Controller Destination"));
//		}

//		if (Robot.armTalonSub.isGood) {
//			SmartDashboard.putNumber("Talon Duration Value", 2);
//			SmartDashboard.putNumber("Talon Power Value", 0.1);
//			SmartDashboard.putData("Talon", new SingleTalonCommand("Talon Duration Value", "Talon Power Value"));
//		}
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
