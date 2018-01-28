package org.usfirst.frc.team5003.robot;

import org.usfirst.frc.team5003.robot.commands.DriveStraightWithGyroCommand;
import org.usfirst.frc.team5003.robot.commands.GroupBuilderCommand;
import org.usfirst.frc.team5003.robot.commands.ResetLimitSwitchWithCounterCommand;
import org.usfirst.frc.team5003.robot.commands.RotateWithGyroCommand;
import org.usfirst.frc.team5003.robot.commands.ServoCommand;
import org.usfirst.frc.team5003.robot.commands.SingleTalonCommand;
import org.usfirst.frc.team5003.robot.commands.TalkativeCommand;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	
	public JoystickButton bOpen;
	public JoystickButton bClose;
	
	public OI() {
		
		//SmartDashboard.putData("Talkative Command On Click", new TalkativeCommand("click", 15));
		
		bOpen = new JoystickButton(Robot.joystick, 7);
		bClose = new JoystickButton(Robot.joystick, 8);
		
		
		
		if (Robot.servoSub.isGood) {
			SmartDashboard.putNumber("Servo Value",       0.5);
			SmartDashboard.putNumber("Servo In Value",    0.2);
			SmartDashboard.putNumber("Servo Out Value",   0.8);

			SmartDashboard.putData("Servo",      new ServoCommand("Servo Value"));
			SmartDashboard.putData("Servo In",   new ServoCommand("Servo In Value"));
			SmartDashboard.putData("Servo Out",  new ServoCommand("Servo Out Value"));
			
			bOpen.whenPressed(new ServoCommand("Servo In Value"));
			bClose.whenPressed(new ServoCommand("Servo Out Value"));;
			
			//SmartDashboard.putNumber("Servo RAW Value",   128);
			//SmartDashboard.putNumber("Servo Stop Value",  0.0);			
			//SmartDashboard.putData("Servo Stop", new ServoCommand("Servo Stop Value"));
			//SmartDashboard.putData("Servo RAW",  new ServoCommand("Servo RAW Value"));
		}

		if (Robot.talonSub.isGood) {
			SmartDashboard.putNumber("Talon Duration Value", 2);
			SmartDashboard.putNumber("Talon Power Value", 0.1);
			SmartDashboard.putData("Talon", new SingleTalonCommand("Talon Duration Value", "Talon Power Value"));
		}
		
		if (Robot.switchSub.isGood)
			SmartDashboard.putData("Reset Limit Switch", new ResetLimitSwitchWithCounterCommand());

		if (Robot.gyroSub.isGood) {
			SmartDashboard.putNumber("Rotate Value", 90);
			SmartDashboard.putData("Rotate", new RotateWithGyroCommand("Rotate Value"));
		}


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
