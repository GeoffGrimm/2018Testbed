package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateWithGyroCommand extends Command {

	String dataSource = null;
	double finalHeading = 0;
	boolean finished = false;
	double tolerance = 1.0;
	
	public RotateWithGyroCommand(String dataSource){
		this();
		this.dataSource = dataSource;
	}
	
    public RotateWithGyroCommand(double finalHeading) {
    	this();
    	this.finalHeading = finalHeading;
    	this.dataSource = null;
    }
    
    public RotateWithGyroCommand() {
    	requires(Robot.drivetrain);
    	requires(Robot.gyroSub);    	
    }

    protected void initialize() {
    	// get final heading from ui if data source specified
    	if (dataSource != null)
    		finalHeading = SmartDashboard.getNumber(dataSource,  0);
    	finished = false; 
    	}

    protected void execute() {
    	double delta = finalHeading - Robot.gyroSub.getAngle();
    	double power = delta / 180; // 100% power if 180 degrees off, 0% if right on
    	if (power > 1)
    		power = 1;
    	else if (power < -1)
    		power = -1;

    	if (Math.abs(delta) < tolerance) {
    		power = 0;
    		finished = true;
    	}
    	
    	Robot.drivetrain.tankDrive(power, -power);
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
