package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;
import org.usfirst.frc.team5003.robot.commands.IdleDriveCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DrivetrainSubsystem extends Subsystem {

	Talon motorLeft;
	Talon motorRight;
	DifferentialDrive differential;
	public Boolean isGood;
	
	public DrivetrainSubsystem(){
		isGood = false;
		try{
			motorLeft = new Talon(0);
			motorLeft.stopMotor();
			motorRight = new Talon(1);
			motorRight.stopMotor();
			differential = new DifferentialDrive(motorLeft, motorRight);
			differential.arcadeDrive(0.0,  0.0);
			differential.setSafetyEnabled(false);
		}
		catch (Exception ex){
			motorLeft = null;
			motorRight = null;
			differential = null;
		}
		if (motorLeft != null && motorRight != null && differential != null)
			isGood = true;
	}
    public void initDefaultCommand() {
    	setDefaultCommand(new IdleDriveCommand());
    }
    public void drive(){
    	differential.arcadeDrive(Robot.joystick.getY(), Robot.joystick.getX());
    }
    public void stop(){
    	differential.arcadeDrive(0,0);
    }
}

