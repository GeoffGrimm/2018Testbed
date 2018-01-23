package org.usfirst.frc.team5003.robot;

import org.usfirst.frc.team5003.robot.commands.DriveStraightWithGyroCommand;
import org.usfirst.frc.team5003.robot.commands.GroupBuilderCommand;
import org.usfirst.frc.team5003.robot.commands.RotateWithGyroCommand;
import org.usfirst.frc.team5003.robot.commands.ServoCommand;
import org.usfirst.frc.team5003.robot.commands.TalkativeCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	
	public OI() {
		SmartDashboard.putNumber("Servo Data", 50);
		
		if (Robot.servoSub.isGood)
		{
			SmartDashboard.putData("Servo", new ServoCommand("Servo Data"));
			SmartDashboard.putData("Full In", new ServoCommand(0));
			SmartDashboard.putData("Full Out", new ServoCommand(100));
		}
		
		SmartDashboard.putData("Finite Talkative Command", new TalkativeCommand("finite", 15));
		
		SmartDashboard.putNumber("Rotate", 90);
		SmartDashboard.putNumber("Duration", 2);
		SmartDashboard.putNumber("Power", 0.1);
		SmartDashboard.putString("CommandString",  "P0.1, D2.0, R90");
		if (Robot.drivetrainSub.isGood && Robot.gyroSub.isGood)
		{
			SmartDashboard.putData("Rotate", new RotateWithGyroCommand("Rotate"));
			SmartDashboard.putData("Drive Straight", new DriveStraightWithGyroCommand("Duration", "Power"));
			SmartDashboard.putData("Run Command String", new GroupBuilderCommand());
		}
		
	}
}
