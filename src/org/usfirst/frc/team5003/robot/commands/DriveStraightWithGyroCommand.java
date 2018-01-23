package org.usfirst.frc.team5003.robot.commands;

import java.util.Date;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightWithGyroCommand extends Command {

	double duration = 0;
	double power = 0.10;
	double start;
	double heading;
	boolean finished = false;
	double tolerance = 1.0;
	
	public DriveStraightWithGyroCommand(String durationKey, String powerKey)
	{
		this(SmartDashboard.getNumber(durationKey,  0), SmartDashboard.getNumber(powerKey,  0.0));
	}
    public DriveStraightWithGyroCommand(double secs, double power) {
    	requires(Robot.drivetrainSub);
    	requires(Robot.gyroSub);
    	this.duration = secs * 1000;
    	this.power = power;
    	finished = false;
    	
    }

    protected void initialize() {
    	start = new Date().getTime();
    	heading = Robot.gyroSub.getAngle();
    }

    protected void execute() {
    	//Robot.drivetrainSub.arcadeDrive(power, 0);
    	double delta = heading - Robot.gyroSub.getAngle();
    	double correction = -delta / 180; // 100% power if 180 degrees off, 0% if right on
    	Robot.drivetrainSub.arcadeDrive(power, correction);
    	
    }

    protected boolean isFinished() {
    	Date now = new Date();
    	if (now.getTime() - start > duration)
    	{
    		Robot.drivetrainSub.stop();
    		return true;
    	}
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
