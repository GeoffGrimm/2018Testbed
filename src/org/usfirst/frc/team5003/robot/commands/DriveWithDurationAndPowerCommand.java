package org.usfirst.frc.team5003.robot.commands;

import java.util.Date;

import org.usfirst.frc.team5003.robot.subsystems.ControllerSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveWithDurationAndPowerCommand extends Command {
	ControllerSubsystem controller;
	String durationKey = null;
	String powerKey = null;
	double duration;
	double power;

	boolean finished = false;
	
	double start;

	public DriveWithDurationAndPowerCommand(ControllerSubsystem controller, String durationKey, String powerKey) {
    	this.controller = controller;
		this.durationKey = durationKey;
		this.powerKey = powerKey;
        requires(this.controller);
        }
	
    protected void initialize() {
   		duration = SmartDashboard.getNumber(durationKey,  0.0) * 1000;
   		power = SmartDashboard.getNumber(powerKey,  0.0);
    	start = new Date().getTime();
    	controller.set(power);
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
    	controller.set(0);
    }

    protected void interrupted() {
    	end();
    }
}
