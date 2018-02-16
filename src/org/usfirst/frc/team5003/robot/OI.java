package org.usfirst.frc.team5003.robot;

import org.usfirst.frc.team5003.robot.commands.ArmUpDownCommand;
import org.usfirst.frc.team5003.robot.commands.DriveWithDurationAndPowerCommand;
import org.usfirst.frc.team5003.robot.commands.GrabberCommand;
import org.usfirst.frc.team5003.robot.commands.RotateWithGyroCommand;
import org.usfirst.frc.team5003.robot.commands.RunProtectedControllerWithPowerCommand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	
	public static final String ActuatorUpHigh = "Actuator Up High";
	public static final String ActuatorUpDuration = "Actuator Up Duration";
	public static final String ActuatorUpLow = "Actuator Up Low";
	public static final String ActuatorDown = "Actuator Down";
	public static final String GearInOut = "Gear In Out";
	public static final String ArmDirection = "Arm Direction";
	public static final String ArmDuration = "Arm Duration";
	
	public static SendableChooser<String> botLocationChooser;
	
	
	
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
		btnActuatorUp = new JoystickButton(Robot.xbox, 5);
		btnActuatorDown = new JoystickButton(Robot.xbox, 6);
		btnGearOut = new JoystickButton(Robot.xbox, 3);
		btnGearIn = new JoystickButton(Robot.xbox, 4);
		btnArmUp = new JoystickButton(Robot.xbox, 10);
		btnArmDown = new JoystickButton(Robot.xbox, 11);
		// todo: button for retract arm fully?
		//       button for drop arm to min
		//       button for both?
		
		
		botLocationChooser = new SendableChooser<String>();
		botLocationChooser.addObject("Left",  "L");
		botLocationChooser.addObject("Center",  "C");
		botLocationChooser.addObject("Right",  "R");
		SmartDashboard.putData("Bot Location", botLocationChooser);
					
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

		SmartDashboard.putNumber(ActuatorUpLow,  0.1);
		SmartDashboard.putNumber(ActuatorUpHigh, 0.6);
		SmartDashboard.putNumber(ActuatorUpDuration, 0.25);
		SmartDashboard.putNumber(ActuatorDown,  0.1);
//		if (Robot.actuatorController.isGood) {
//			btnActuatorUp.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, 
//																		ActuatorUpLow, 
//																		ActuatorUpHigh, 
//																		ActuatorUpDuration, 
//																		+1));
//			// easy to extend beyond 16" limit when coming down!
//
//			btnActuatorDown.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, ActuatorDown, -1));
//			
//			if (Robot.gearController.isGood)
//			{
//				btnArmUp.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, 
//						ActuatorUpLow, 
//						ActuatorUpHigh, 
//						ActuatorUpDuration,
//						Robot.gearController,
//						+1));
//				
//				// use acutator down power for hi & lo 'cause that's the only constructor that takes a gear
//				btnArmDown.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, 
//						ActuatorDown, 
//						ActuatorDown, 
//						ActuatorUpDuration,
//						Robot.gearController,
//						-1));
//			}
//		}
		
		if (Robot.arm.isGood) {
			btnArmUp.whileHeld(new ArmUpDownCommand(1));
			btnArmDown.whileHeld(new ArmUpDownCommand(-1));
			SmartDashboard.putNumber(ArmDirection, 1);
			SmartDashboard.putNumber(ArmDuration, 0.5);
			SmartDashboard.putData("Move Arm", new ArmUpDownCommand((int)SmartDashboard.getNumber(ArmDirection, 1),
					                                                     SmartDashboard.getNumber(ArmDuration, 0.5)));
		}

		// todo: set upper limit on gear controller based on actuator
//		if (Robot.gearController.isGood) {
//			SmartDashboard.putNumber(GearInOut,  0.1);
//			btnGearIn.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.gearController, GearInOut, +1));
//			btnGearOut.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.gearController, GearInOut, -1));
//		}

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
