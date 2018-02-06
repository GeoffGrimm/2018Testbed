package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;
import org.usfirst.frc.team5003.robot.commands.DriveWithJoystickCommand;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainSubsystem extends Subsystem {
//	Spark driveLeft0 = null;
//	Spark driveLeft1 = null;
//	Spark driveRight0 = null;
//	Spark driveRight1 = null;
	
	// Dolores:
	Spark driveLeft0 = null;
	Victor driveLeft1 = null;
	Spark driveRight0 = null;
	Victor driveRight1 = null;

			
	
	SpeedControllerGroup speedControllerLeft = null;
	SpeedControllerGroup speedControllerRight = null;
	
	private DifferentialDrive differential = null;
	
	public boolean isGood = false;
	
	public DrivetrainSubsystem(int left0, int left1, int right0, int right1){
		try {
//			driveLeft0 = new Spark(left0);
//			driveLeft1 = new Spark(left1);
//			driveRight0 = new Spark(right0);
//			driveRight1 = new Spark(right1);
			
			// Dolores
			driveLeft0 = new Spark(left0);
			driveLeft1 = new Victor(left1);
			driveRight0 = new Spark(right0);
			driveRight1 = new Victor(right1);
			speedControllerLeft = new SpeedControllerGroup(driveLeft0, driveLeft1);
			speedControllerRight = new SpeedControllerGroup(driveRight0, driveRight1);
			differential = new DifferentialDrive(speedControllerLeft, speedControllerRight);	
			isGood = true;
		}
		catch (Exception ex) {
			driveLeft0 = null;
			driveLeft1 = null;
			driveRight0 = null;
			driveRight1 = null;
			speedControllerLeft = null;
			speedControllerRight = null;
			differential = null;
			isGood = false;
			Robot.log(String.format("Drivetrain on %d failed", left0));
		}
	}
    
	public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoystickCommand());
    }
    
	public void driveWithJoystick(){
    	arcadeDrive(Robot.xbox.getY(), Robot.xbox.getX());
    }
	public void arcadeDrive(double y, double x) {
		differential.arcadeDrive(y, x);
	}
	
	public void stop() {
		differential.arcadeDrive(0,  0);
	}
    
    public void show() {
    	if (isGood) {
    		SmartDashboard.putNumber("L0", driveLeft0.get());
			SmartDashboard.putNumber("L1", driveLeft1.get());
			SmartDashboard.putNumber("R0", driveRight0.get());
			SmartDashboard.putNumber("R1", driveRight1.get());
			SmartDashboard.putNumber("L", speedControllerLeft.get());
			SmartDashboard.putNumber("R", speedControllerRight.get());
    	}
    }
}

