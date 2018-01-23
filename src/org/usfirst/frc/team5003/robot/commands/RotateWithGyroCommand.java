package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateWithGyroCommand extends Command {

	double finalHeading = 0;
	boolean finished = false;
	double tolerance = 1.0;
	
	public RotateWithGyroCommand(String key)
	{
		this(SmartDashboard.getNumber(key,  0));
	}
    public RotateWithGyroCommand(double finalHeading) {
    	requires(Robot.drivetrainSub);
    	requires(Robot.gyroSub);
    	this.finalHeading = finalHeading;
    	finished = false;
    	
    }

    protected void initialize() {
    }

    protected void execute() {
    	double delta = finalHeading - Robot.gyroSub.getAngle();
    	double power = delta / 180; // 100% power if 180 degrees off, 0% if right on

    	if (Math.abs(delta) < tolerance) {
    		power = 0;
    		finished = true;
    	}
    	
    	Robot.drivetrainSub.tankDrive(power, -power);
    }

    protected boolean isFinished() {
    	return finished;
    }

    protected void end() {
    	Robot.drivetrainSub.stop();
    }

    protected void interrupted() {
    }
}
