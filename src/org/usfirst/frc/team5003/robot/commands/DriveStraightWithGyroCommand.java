package org.usfirst.frc.team5003.robot.commands;

import java.util.Date;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightWithGyroCommand extends Command {

	String durationKey = null;
	String powerKey = null;
	double duration;
	double power;

	boolean finished = false;
	double tolerance = 1.0;
	
	double start;
	double heading;

	public DriveStraightWithGyroCommand(String durationKey, String powerKey) {
		this();
		this.durationKey = durationKey;
		this.powerKey = powerKey;
	}
    public DriveStraightWithGyroCommand(double secsDuration, double power) {
    	this();
    	this.duration = secsDuration * 1000;
    	this.power = power;
    }
    public DriveStraightWithGyroCommand() {
    	requires(Robot.drivetrain);
    	requires(Robot.gyro);
    }

    protected void initialize() {
    	if (durationKey != null)
    		duration = SmartDashboard.getNumber(durationKey,  0.0) * 1000;
    	if (powerKey != null)
    		power = SmartDashboard.getNumber(powerKey,  0.0);
    	start = new Date().getTime();
    	heading = Robot.gyro.getAngle();
    }

    protected void execute() {
    	//Robot.drivetrainSub.arcadeDrive(power, 0);
    	double delta = heading - Robot.gyro.getAngle();
    	double correction = -delta / 180; // 100% power if 180 degrees off, 0% if right on
    	Robot.drivetrain.arcadeDrive(power, correction);
    }

    protected boolean isFinished() {
    	Date now = new Date();
    	if (now.getTime() - start > duration)
    	{
    		Robot.drivetrain.stop();
    		return true;
    	}
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
