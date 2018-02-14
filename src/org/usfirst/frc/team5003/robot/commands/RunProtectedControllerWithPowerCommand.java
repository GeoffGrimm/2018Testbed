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
    	
    	// if we have a gear controller attached, set its power based on where it should be
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
    
    // given actuator position, what should the gear position be to maintain 16" away from robot edge
    static double[] aVals = new double[] {1, 2, 3, 4, 5, 6};
    static double[] gVals = new double[] {1, 1, 2, 3, 5, 8};
    protected double getGearDestination() {
    	// start with actuator postion
    	double a = controller.getPosition();
    	
    	// find interval containing a
    	int iMax = aVals.length - 1;
    	int j = -1;
    	
    	// first two cases are outside the range of values.  may want to do something different
    	if (a < aVals[0])
    		j = 0;
    	else if (a >= aVals[iMax])
    		j = iMax-1;
    	// a in the range of a values, look for interval
    	else {
	    	for (int i = 0; i < iMax; i++) {
	    		if (a >= aVals[i] && a < aVals[i+1]) {
	    			j = i;
	    			break;
	    		}
	    	}
    	}
    	// fell thru, shouldn't happen
    	if (j == -1)
    		return gearController.getPosition();

    	return gVals[j] + ((gVals[j+1] - gVals[j]) / (aVals[j+1] - aVals[j])) * (a - aVals[j]);
    	
    }
}
// we have N points, numbered 0->N-1
// iMax is N-1
// we have N-1 intervals, numbered 0->N-2 or 0->iMax-1 using the leftmost coordinate