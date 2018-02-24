package org.usfirst.frc.team5003.robot.commands;

import java.util.Date;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveWithPowerAndDurationCommand extends Command {
	String powerKey = null;
	double power;
	String durationKey = null;
	double duration;

	double start;

	public DriveWithPowerAndDurationCommand(String powerKey, String durationKey) {
		this.powerKey = powerKey;
		this.durationKey = durationKey;
        requires(Robot.drivetrain);
    }
	
	public DriveWithPowerAndDurationCommand(double power, double duration) {
		this.power = power;
		powerKey = null;
		this.duration = duration;
		durationKey = null;
	}
	
    protected void initialize() {
    	if (powerKey != null)
    	{
    		power = SmartDashboard.getNumber(powerKey,  0.0);
    	}
    	if (durationKey != null) 
    	{
    		duration = SmartDashboard.getNumber(durationKey,  0.0) * 1000;
    	}
    	start = new Date().getTime();
    	Robot.drivetrain.arcadeDrive(power,  0);
   }

    protected void execute() {
    	//Robot.drivetrain.arcadeDrive(power,  0);
    }

    protected boolean isFinished() {
       	Date now = new Date();
    	if (now.getTime() - start > duration)
    		return true;
    	return false;
    }

    protected void end() {
    	Robot.drivetrain.stop();
    }

    protected void interrupted() {
    	end();
    }
}
