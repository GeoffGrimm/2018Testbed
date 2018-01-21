package org.usfirst.frc.team5003.robot;

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
		
		SmartDashboard.putData("Finite Talkative Command", new TalkativeCommand("finite", true));
		
		SmartDashboard.putData("Infinite Talkative Command", new TalkativeCommand("infinite", false));
		
	}
}
