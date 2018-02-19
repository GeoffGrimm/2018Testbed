package org.usfirst.frc.team5003.robot.commands;

import java.util.Date;

import org.usfirst.frc.team5003.robot.subsystems.ProtectedControllerSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunProtectedControllerWithPowerCommand extends Command {
	ProtectedControllerSubsystem controller;
	String lowPowerKey;
	String highPowerKey;
	String highPowerDurationKey;
	double lowPower;
	double highPower;
	int highPowerDuration;
	
	long startTime;
	int direction;
	
	// simple protected controller - just power and direction
	public RunProtectedControllerWithPowerCommand(ProtectedControllerSubsystem controller, String lowPowerKey, int direction){
		this(controller, lowPowerKey, null, null, direction);
	}
	
	// actuator style - temporary high power boost if going up
    public RunProtectedControllerWithPowerCommand(ProtectedControllerSubsystem controller, String lowPowerKey, String highPowerKey, String highPowerDurationKey, int direction) {
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
    	double currentPower = 0;
    	
    	// high power key not set, just use low power all the time
    	if (highPowerKey == null)
    		currentPower = lowPower;
    	// high power set, determin power based on elapsed time
    	else{
	    	double duration = new Date().getTime() - startTime;
	    	// in high power range, scale high down to low linearly over duration
	    	if (duration < highPowerDuration)
	    		currentPower = highPower - (highPower - lowPower) * ((double)duration/(double)highPowerDuration);
	    	else
	    		currentPower = lowPower;
    	}
    	controller.set(currentPower * direction);
    }

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
