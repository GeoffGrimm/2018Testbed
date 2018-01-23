package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;
import org.usfirst.frc.team5003.robot.commands.DriveWithJoystickCommand;
import org.usfirst.frc.team5003.robot.commands.IdleDriveCommand;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DrivetrainSubsystem extends Subsystem {

	Talon motorLeft;
	Talon motorRight;
	Spark driveLeft0;
	Victor driveLeft1;
	Spark driveRight0;
	Victor driveRight1;
	
	SpeedControllerGroup left;
	SpeedControllerGroup right;
	
	DifferentialDrive differential;
	public Boolean isGood;
	
	public DrivetrainSubsystem(){
		isGood = false;
		try{
			driveLeft0 = new Spark(3);
			driveLeft0.stopMotor();
			driveLeft1 = new Victor(7);
			driveLeft1.stopMotor();
			driveRight0 = new Spark(2);
			driveRight0.stopMotor();
			driveRight1 = new Victor(6);
			driveRight1.stopMotor();
			left = new SpeedControllerGroup(driveLeft0, driveLeft1);
			right = new SpeedControllerGroup(driveRight0, driveRight1);
			differential = new DifferentialDrive(left, right);	
			differential.arcadeDrive(0,  0);
		}
		catch (Exception ex){
			driveLeft0 = null;
			driveLeft1 = null;
			driveRight0 = null;
			driveRight1 = null;
			left = null;
			right = null;
			differential = null;
		}
		if (differential != null)
			isGood = true;
	}
    
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoystickCommand());
    }
    
	public void driveWithJoystick(){
    	arcadeDrive(Robot.joystick.getY(), Robot.joystick.getX());
    }
    
    public void arcadeDrive(double y, double x){
    	differential.arcadeDrive(y, x);
    }

    public void tankDrive(double left, double right)
    {
    	differential.tankDrive(left, right);
    }
    
    public void stop(){
    	differential.arcadeDrive(0,0);
    }
}

