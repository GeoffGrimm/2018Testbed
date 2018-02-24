package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateWithGyroCommand extends Command {

	String targetHeadingKey = null;
	double targetHeading = 0;
	boolean finished = false;
	double tolerance = 1.0;
	double maxPower = 1;
	
	public RotateWithGyroCommand(String targetHeadingKey){
		this();
		this.targetHeadingKey = targetHeadingKey;
	}
	
    public RotateWithGyroCommand(double targetHeading) {
    	this();
    	this.targetHeading = targetHeading;
    	this.targetHeadingKey = null;
    }
    
    public RotateWithGyroCommand() {
    	requires(Robot.drivetrain);
    	requires(Robot.gyro);    	
    }

    protected void initialize() {
    	// get final heading from ui if data source specified
    	if (targetHeadingKey != null)
    		targetHeading = SmartDashboard.getNumber(targetHeadingKey,  0);
    	finished = false; 
    	}

    protected void execute() {
    	double delta = targetHeading - Robot.gyro.getAngle();
    	double power = 0.5 * (delta < 0?-1:1);
    	//double power =  0.2 + 0.8 * maxPower * (delta / 45); // 100% power if 180 degrees off, 0% if right on
    	if (power > maxPower)
    		power = maxPower;
    	else if (power < -maxPower)
    		power = -maxPower;

    	if (Math.abs(delta) < tolerance) {
    		power = 0;
    		finished = true;
    	}
    	
    	Robot.drivetrain.arcadeDrive(0,  power);
    }

    protected boolean isFinished() {
    	return finished;
    }

    protected void end() {
    	Robot.drivetrain.stop();
    }

    protected void interrupted() {
    }
}
