package org.usfirst.frc.team5003.robot;

import org.usfirst.frc.team5003.robot.commands.DriveWithDurationAndPowerCommand;
import org.usfirst.frc.team5003.robot.commands.GrabberCommand;
import org.usfirst.frc.team5003.robot.commands.RotateWithGyroCommand;
import org.usfirst.frc.team5003.robot.commands.RunProtectedControllerWithPowerCommand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5003.robot.commands.GroupBuilderCommand;

public class OI {
	
	public JoystickButton btnGrabberOpen, btnGrabberClose, btnActuatorUp, btnActuatorDown, btnGearOut, btnGearIn, btnArmUp, btnArmDown;
	
	static public int XBoxA = 1;
	static public int XBoxB = 2;
	static public int XBoxX = 3;
	static public int XBoxY = 4;
	static public int XBoxLeft = 5;
	static public int XBoxRight = 6;
	static public int XBoxBack = 7;
	static public int XBoxStart = 8;
	
	
	
	public OI() {
		
		// xbox mapping
//		btnGrabberOpen = new JoystickButton(Robot.xbox, XBoxLeft);
//		btnGrabberClose = new JoystickButton(Robot.xbox, XBoxRight);
//		btnActuatorDown = new JoystickButton(Robot.xbox, XBoxA);
//		btnActuatorUp = new JoystickButton(Robot.xbox, XBoxB);
//		btnGearIn = new JoystickButton(Robot.xbox, XBoxX);
//		btnGearOut = new JoystickButton(Robot.xbox, XBoxY);
		
		// logitech mapping
		btnGrabberOpen = new JoystickButton(Robot.xbox, 8);
		btnGrabberClose = new JoystickButton(Robot.xbox, 9);
		btnActuatorDown = new JoystickButton(Robot.xbox, 5);
		btnActuatorUp = new JoystickButton(Robot.xbox, 6);
		btnGearIn = new JoystickButton(Robot.xbox, 3);
		btnGearOut = new JoystickButton(Robot.xbox, 4);
		btnArmUp = new JoystickButton(Robot.xbox, 10);
		btnArmDown = new JoystickButton(Robot.xbox, 11);
					
		if (Robot.grabber.isGood) {
			SmartDashboard.putNumber("Grabber Value",       0.5);
			SmartDashboard.putNumber("Grabber Open Value",  0.2);
			SmartDashboard.putNumber("Grabber Close Value", 0.8);

			SmartDashboard.putData("Grabber",      new GrabberCommand("Grabber Value"));
			SmartDashboard.putData("Grabber Open", new GrabberCommand("Grabber Open Value"));
			SmartDashboard.putData("Grabber Close",new GrabberCommand("Grabber Close Value"));
			
			btnGrabberOpen.whenPressed(new GrabberCommand("Grabber Open Value"));
			btnGrabberClose.whenPressed(new GrabberCommand("Grabber Close Value"));
		}
		
		if (Robot.actuatorController.isGood) {
			SmartDashboard.putNumber("Actuator Up Low Power",  0.5);
			SmartDashboard.putNumber("Actuator Up High Power", 0.5);
			SmartDashboard.putNumber("Actuator Up High Power Duration", 0.5);
			btnActuatorUp.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, 
																		"Actuator Up Low Power", 
																		"Actuator Up High Power", 
																		"Actuator Up High Power Duration", 
																		+1));
			SmartDashboard.putNumber("Actuator Down Power",  0.5);
			btnActuatorDown.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, "Actuator Down Power", -1));
			
			if (Robot.gearController.isGood)
			{
				btnActuatorUp.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, 
						"Actuator Up Low Power", 
						"Actuator Up High Power", 
						"Actuator Up High Power Duration",
						Robot.gearController,
						+1));

				btnActuatorUp.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, 
						"Actuator Up Low Power", 
						"Actuator Up High Power", 
						"Actuator Up High Power Duration", 
						-1));

			}
		}
		
		if (Robot.gearController.isGood) {
			SmartDashboard.putNumber("Gear Power",  0.1);
			btnGearIn.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.gearController, "Gear Power", +1));
			btnGearOut.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.gearController, "Gear Power", -1));
		}

		if (Robot.drivetrain.isGood)
		{
			SmartDashboard.putNumber("Drive Duration", 2);
			SmartDashboard.putNumber("Drive Power", 0.1);
			SmartDashboard.putData("Drive", new DriveWithDurationAndPowerCommand("Drive Duration", "Drive Power"));
		}
		
		if (Robot.drivetrain.isGood && Robot.gyro.isGood) {
			SmartDashboard.putNumber("Rotate Value", 90);
			SmartDashboard.putData("Rotate", new RotateWithGyroCommand("Rotate Value"));
			SmartDashboard.putString("CommandString",  "P0.5,R90,R-90");	
			SmartDashboard.putData("Run Command String", new GroupBuilderCommand());
		}
		

//		
//		if (Robot.protectedController.isGood && Robot.protectedController.enc != null)
//		{
//			SmartDashboard.putNumber("Protected Controller Destination", 0.0);
//			SmartDashboard.putData("Go", new DriveProtectedControllerToPositionCommand(Robot.protectedController, "Protected Controller Destination"));
//		}

	}
}
