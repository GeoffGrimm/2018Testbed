package org.usfirst.frc.team5003.robot.commands;

import java.util.Date;

import org.usfirst.frc.team5003.robot.subsystems.ControllerSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunControllerWithPowerCommand extends Command {
	ControllerSubsystem controller;
	String lowPowerKey;
	String highPowerKey;
	String highPowerDurationKey;
	double lowPower;
	double highPower;
	int highPowerDuration;
	long startTime;
	int direction;
	
	public RunControllerWithPowerCommand(ControllerSubsystem controller, String lowPowerKey, int direction){
		this(controller, lowPowerKey, null, null, direction);
	}

    public RunControllerWithPowerCommand(ControllerSubsystem controller, String lowPowerKey, String highPowerKey, String highPowerDurationKey, int direction) {
    	this.controller = controller;
    	this.lowPowerKey = lowPowerKey;
    	this.highPowerKey = highPowerKey;
    	this.highPowerDurationKey = highPowerDurationKey;
    	this.direction = direction;
        requires(this.controller);
    }

    protected void initialize() {
    	lowPower = SmartDashboard.getNumber(lowPowerKey,  0.0);
    	if (highPowerKey != null)
    	{
	    	highPower = SmartDashboard.getNumber(highPowerKey, 0.0);
	    	highPowerDuration = (int)(1000 * SmartDashboard.getNumber(highPowerDurationKey,  0.0));
    	}
    	startTime = new Date().getTime();
    	
    }

    protected void execute() {
    	if (highPowerKey == null)
    		controller.set(lowPower);
    	else
    	{
	    	double duration = new Date().getTime() - startTime;
	    	double currentPower;
	    	if (duration < highPowerDuration)
	    		currentPower = highPower - (highPower - lowPower) * ((double)duration/(double)highPowerDuration);
	    	else
	    		currentPower = lowPower;
	    		
	    	controller.set(currentPower * direction);
    	}
    	
    }

    // hooked up to joystick button, just exit right away and come around again?
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	controller.set(0);
    }

    protected void interrupted() {
    	end();
    }
}
