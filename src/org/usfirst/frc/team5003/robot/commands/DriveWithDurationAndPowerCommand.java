package org.usfirst.frc.team5003.robot.commands;

import java.util.Date;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveWithDurationAndPowerCommand extends Command {
	String durationKey = null;
	String powerKey = null;
	double duration;
	double power;

	boolean finished = false;
	
	double start;

	public DriveWithDurationAndPowerCommand(String durationKey, String powerKey) {
		this();
		this.durationKey = durationKey;
		this.powerKey = powerKey;
	}
	
    public DriveWithDurationAndPowerCommand(double secsDuration, double power) {
    	this();
    	this.duration = secsDuration * 1000;
    	this.power = power;
    }

    public DriveWithDurationAndPowerCommand() {
        requires(Robot.armTalonSub);
    }

    protected void initialize() {
    	if (durationKey != null)
    		duration = SmartDashboard.getNumber(durationKey,  0.0) * 1000;
    	if (powerKey != null)
    		power = SmartDashboard.getNumber(powerKey,  0.0);
    	start = new Date().getTime();
    	Robot.armTalonSub.set(power);
   }

    protected void execute() {
    }

    protected boolean isFinished() {
       	Date now = new Date();
    	if (now.getTime() - start > duration)
    		return true;
    	return false;
    }

    protected void end() {
    	Robot.armTalonSub.set(0);
    }

    protected void interrupted() {
    }
}
