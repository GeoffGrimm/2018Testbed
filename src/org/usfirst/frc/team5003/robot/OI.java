package org.usfirst.frc.team5003.robot;

import org.usfirst.frc.team5003.robot.commands.ArmUpDownCommand;
import org.usfirst.frc.team5003.robot.commands.DriveWithPowerAndDurationCommand;
import org.usfirst.frc.team5003.robot.commands.GrabberServoCommand;
import org.usfirst.frc.team5003.robot.commands.RotateWithGyroCommand;
import org.usfirst.frc.team5003.robot.commands.RunGrabberMotorCommand;
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
	public static final String GrabberMotorOpen = "Grabber Motor Open";
	public static final String GrabberMotorClose = "Grabber Motor Close";
	public static final String GrabberMotorDuration = "Grabber Motor Duration";
	public static final String DrivePower = "Drive Power";
	public static final String DriveDuration = "Drive Duration";
	public static final String RotateAngle = "Rotate Angle";
	public static final String CommandString = "Command String";
	
	public static final String AutoSwitchX = "Auto Switch X";
	public static final String AutoSwitchY = "Auto Switch Y";
	public static final String AutoScaleX = "Auto Scale X";
	public static final String AutoScaleY = "Auto Scale Y";
	public static final String AutoCenterX = "Auto Center X";
	public static final String AutoGrabberOpen = "Auto Grabber Open";

	
	public static SendableChooser<String> botLocationChooser;
	
	
	
	public OI() 
	{
		btnGrabberOpen = new JoystickButton(Robot.joy, XBoxLeft);
		btnGrabberClose = new JoystickButton(Robot.joy, XBoxRight);
		
		if (Robot.grabber != null && Robot.grabber.isGood) 
		{
			SmartDashboard.putNumber(GrabberMotorOpen, 0.25);
			SmartDashboard.putNumber(GrabberMotorClose, -0.25);
			SmartDashboard.putNumber(GrabberMotorDuration, 1);
			
			btnGrabberOpen.whenPressed(new RunGrabberMotorCommand(GrabberMotorOpen));
			btnGrabberClose.whenPressed(new RunGrabberMotorCommand(GrabberMotorClose));
			
			SmartDashboard.putData("Open", new RunGrabberMotorCommand(GrabberMotorOpen, GrabberMotorDuration));
			SmartDashboard.putData("Close", new RunGrabberMotorCommand(GrabberMotorClose, GrabberMotorDuration));
		}
		
		if (Robot.drivetrain != null && Robot.drivetrain.isGood)
		{
			SmartDashboard.putNumber(DriveDuration, 2);
			SmartDashboard.putNumber(DrivePower, 0.1);
			SmartDashboard.putData("Drive", new DriveWithPowerAndDurationCommand(DriveDuration, DrivePower));
			
			if (Robot.gyro != null && Robot.gyro.isGood)
			{
				SmartDashboard.putNumber(RotateAngle, 90);
				SmartDashboard.putData("Rotate", new RotateWithGyroCommand(RotateAngle));
				SmartDashboard.putString(CommandString,  "P0.5,R90,R-90");	
				SmartDashboard.putData("Run Command String", new GroupBuilderCommand());
			}
		}
		
		SmartDashboard.putString(AutoSwitchX, "Drive,0.5,2;");
		SmartDashboard.putString(AutoSwitchY, "Drive,0.5,4;");
		SmartDashboard.putString(AutoScaleX, "Drive,0.2,0.4;");
		SmartDashboard.putString(AutoScaleY, "Drive,0.2,0.2;");
		SmartDashboard.putString(AutoCenterX, "Drive,0.5,1;");
		SmartDashboard.putString(AutoGrabberOpen, "Grabber,0.5,0.2");
	}
	
	public void OIForConrad()
	{
		
		// xbox mapping
		btnGrabberOpen = new JoystickButton(Robot.joy, XBoxLeft);
		btnGrabberClose = new JoystickButton(Robot.joy, XBoxRight);
		btnActuatorDown = new JoystickButton(Robot.joy, XBoxA);
		btnActuatorUp = new JoystickButton(Robot.joy, XBoxB);
		btnGearIn = new JoystickButton(Robot.joy, XBoxX);
		btnGearOut = new JoystickButton(Robot.joy, XBoxY);
		btnArmDown = new JoystickButton(Robot.joy, XBoxBack);
		btnArmUp = new JoystickButton(Robot.joy, XBoxStart);
		
		// logitech mapping
//		btnGrabberOpen = new JoystickButton(Robot.xbox, 8);
//		btnGrabberClose = new JoystickButton(Robot.xbox, 9);
//		btnActuatorUp = new JoystickButton(Robot.xbox, 6);
//		btnActuatorDown = new JoystickButton(Robot.xbox, 5);
//		btnGearOut = new JoystickButton(Robot.xbox, 4);
//		btnGearIn = new JoystickButton(Robot.xbox, 3);
//		btnArmUp = new JoystickButton(Robot.xbox, 11);
//		btnArmDown = new JoystickButton(Robot.xbox, 10);
		// todo: button for retract arm fully?
		//       button for drop arm to min
		//       button for both?
		
		
		botLocationChooser = new SendableChooser<String>();
		botLocationChooser.addObject("Left",  "L");
		botLocationChooser.addDefault("Center",  "C");
		botLocationChooser.addObject("Right",  "R");
		SmartDashboard.putData("Bot Location", botLocationChooser);
					
		if (Robot.grabber != null && Robot.grabber.isGood) {
			//SmartDashboard.putNumber("Grabber Value",       0.5);
			SmartDashboard.putNumber("Grabber Open Value",  0.17);
			SmartDashboard.putNumber("Grabber Close Value", 0.82);

			//SmartDashboard.putData("Grabber",      new GrabberCommand("Grabber Value"));
			SmartDashboard.putData("Grabber Open", new GrabberServoCommand("Grabber Open Value"));
			SmartDashboard.putData("Grabber Close",new GrabberServoCommand("Grabber Close Value"));
			
			btnGrabberOpen.whenPressed(new GrabberServoCommand("Grabber Open Value"));
			btnGrabberClose.whenPressed(new GrabberServoCommand("Grabber Close Value"));
		}

		SmartDashboard.putNumber(ActuatorUpLow,  0.4);
		SmartDashboard.putNumber(ActuatorUpHigh, 0.6);
		SmartDashboard.putNumber(ActuatorUpDuration, 0.25);
		SmartDashboard.putNumber(ActuatorDown,  0.3);
		if (Robot.actuatorController != null && Robot.actuatorController.isGood) {
			btnActuatorUp.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, 
																		ActuatorUpLow, 
																		ActuatorUpHigh, 
																		ActuatorUpDuration, 
																		+1));
			// easy to extend beyond 16" limit when coming down!
			btnActuatorDown.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, ActuatorDown, -1));
			
		}
		
		// todo: set upper limit on gear controller based on actuator
		if (Robot.gearController != null && Robot.gearController.isGood) {
			SmartDashboard.putNumber(GearInOut,  0.3);
			btnGearIn.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.gearController, GearInOut, +1));
			btnGearOut.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.gearController, GearInOut, -1));
		}
		
		if (Robot.arm != null && Robot.arm.isGood) {
			// auto
			btnArmUp.whileHeld(new ArmUpDownCommand(1));
			btnArmDown.whileHeld(new ArmUpDownCommand(-1));
			// manual
			SmartDashboard.putNumber(ArmDirection, 1);
			SmartDashboard.putNumber(ArmDuration, 0.5);
			SmartDashboard.putData("Move Arm", new ArmUpDownCommand((int)SmartDashboard.getNumber(ArmDirection, 1),
					                                                     SmartDashboard.getNumber(ArmDuration, 0.5)));
		}

		if (Robot.drivetrain != null && Robot.drivetrain.isGood)
		{
			SmartDashboard.putNumber("Drive Duration", 2);
			SmartDashboard.putNumber("Drive Power", 0.1);
			SmartDashboard.putData("Drive", new DriveWithPowerAndDurationCommand("Drive Duration", "Drive Power"));
			
			if (Robot.gyro != null && Robot.gyro.isGood)
			{
				SmartDashboard.putNumber("Rotate Value", 90);
				SmartDashboard.putData("Rotate", new RotateWithGyroCommand("Rotate Value"));
				SmartDashboard.putString("CommandString",  "P0.5,R90,R-90");	
				SmartDashboard.putData("Run Command String", new GroupBuilderCommand());
			}
		}

	}
}

//if (Robot.gearController.isGood)
//{
//	btnArmUp.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, 
//			ActuatorUpLow, 
//			ActuatorUpHigh, 
//			ActuatorUpDuration,
//			Robot.gearController,
//			+1));
//	
//	// use acutator down power for hi & lo 'cause that's the only constructor that takes a gear
//	btnArmDown.whileHeld(new RunProtectedControllerWithPowerCommand(Robot.actuatorController, 
//			ActuatorDown, 
//			ActuatorDown, 
//			ActuatorUpDuration,
//			Robot.gearController,
//			-1));
//}

//
//
//
//if (Robot.protectedController.isGood && Robot.protectedController.enc != null)
//{
//	SmartDashboard.putNumber("Protected Controller Destination", 0.0);
//	SmartDashboard.putData("Go", new DriveProtectedControllerToPositionCommand(Robot.protectedController, "Protected Controller Destination"));
//}

