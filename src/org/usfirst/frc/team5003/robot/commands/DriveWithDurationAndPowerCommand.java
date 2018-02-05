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
		this.durationKey = durationKey;
		this.powerKey = powerKey;
        requires(Robot.drivetrain);
    }
	
	public DriveWithDurationAndPowerCommand(double duration, double power) {
			this.duration = duration;
			this.power = power;
			durationKey = null;
			powerKey = null;
	}
	
    protected void initialize() {
    	if (durationKey != null) {
    		duration = SmartDashboard.getNumber(durationKey,  0.0) * 1000;
    		power = SmartDashboard.getNumber(powerKey,  0.0);
    	}
    	start = new Date().getTime();
    	Robot.drivetrain.arcadeDrive(power,  0);
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
    	Robot.drivetrain.stop();
    }

    protected void interrupted() {
    	end();
    }
}
