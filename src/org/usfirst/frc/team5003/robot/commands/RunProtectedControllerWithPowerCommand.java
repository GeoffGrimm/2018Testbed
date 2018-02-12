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
	
	ProtectedControllerSubsystem gearController;
	
	long startTime;
	int direction;
	
	// simple protected controller - just power and direction
	public RunProtectedControllerWithPowerCommand(ProtectedControllerSubsystem controller, String lowPowerKey, int direction){
		this(controller, lowPowerKey, null, null, null, direction);
	}
	
	// actuator style - temporary high power boost if going up
    public RunProtectedControllerWithPowerCommand(ProtectedControllerSubsystem controller, String lowPowerKey, String highPowerKey, String highPowerDurationKey, int direction) {
    	this(controller, lowPowerKey, highPowerKey, highPowerDurationKey, null, direction);
    }

    // arm style - actuator plus gear controller
    public RunProtectedControllerWithPowerCommand(ProtectedControllerSubsystem controller, String lowPowerKey, String highPowerKey, String highPowerDurationKey, ProtectedControllerSubsystem gearController, int direction) {
    	this.controller = controller;
    	this.lowPowerKey = lowPowerKey;
    	this.highPowerKey = highPowerKey;
    	this.highPowerDurationKey = highPowerDurationKey;
    	this.direction = direction;
        requires(this.controller);
        if (this.gearController != null)
        	requires(this.gearController);
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
    	if (highPowerKey == null)
    		currentPower = lowPower;
    	else
    	{
	    	double duration = new Date().getTime() - startTime;
	    	if (duration < highPowerDuration)
	    		currentPower = highPower - (highPower - lowPower) * ((double)duration/(double)highPowerDuration);
	    	else
	    		currentPower = lowPower;
    	}
    	controller.set(currentPower * direction);
    	
    	if (gearController != null) {
	    	double gearPower;
	    	double gearDestination = getGearDestination();
	    	// over extended, reel it in quick
	    	if (gearDestination < gearController.getPosition())
	    		gearPower = -1;
	    	// under extended, extend with scaled power
	    	else
	    		gearPower = lowPower + (1-lowPower) * (gearDestination - gearController.getPosition()) / (gearController.maxPos - gearController.minPos);
	    	gearController.set(gearPower);
    	}
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
    
    protected double getGearDestination() {
    	// a miracle occurs
    	// gear = f(controller.Position);
    	if (gearController == null)
    		return 0;
    	
    	// given array of g (gear) and a (actuator) values
    	// get a
    	// find interval that contains a, then
    	// g = g[i] + (a-a[i]) * ((g[i+1]-g[i])/(a[i+1]-a[i]))
    	return gearController.getPosition();
    }
}
