package org.usfirst.frc.team5003.robot.commands;

import java.util.Date;

import org.usfirst.frc.team5003.robot.OI;
import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmUpDownCommand extends Command {
	
	private double actuatorUpHigh;
	private double actuatorHighDuration;
	private double actuatorUpLow;
	private double actuatorDown;
	private double gearInOut;
	private long startTime;
	private long commandDuration = -1;
	private boolean isDone = false;
	
	private int direction;
	
    public ArmUpDownCommand(int direction, double duration) {
    	this.direction = direction;
    	this.commandDuration = (long)(1000*duration);
    	isDone = false;
        requires (Robot.arm);
    }
    public ArmUpDownCommand(int direction) {
    	this(direction, -1);
    }

    protected void initialize() {
    	actuatorUpHigh = SmartDashboard.getNumber(OI.ActuatorUpHigh, 0);
    	actuatorHighDuration = 1000 * SmartDashboard.getNumber(OI.ActuatorUpDuration, 0);
    	actuatorUpLow = SmartDashboard.getNumber(OI.ActuatorUpLow,  0);
    	actuatorDown = SmartDashboard.getNumber(OI.ActuatorDown,  0.0);
    	gearInOut = SmartDashboard.getNumber(OI.GearInOut, 0.0);
    	startTime = new Date().getTime();
    }

    protected void execute() {
		long elapsed = new Date().getTime() - startTime;
		
		if (commandDuration > 0 && elapsed > commandDuration) {
			isDone = true;
			return;
		}
    	
    	double actuatorPower;
    	if (direction > 0)
    	{
    		if (elapsed <= actuatorHighDuration)
    		{
    			//actuatorPower = actuatorUpHigh;
    			actuatorPower = actuatorUpHigh - (actuatorUpHigh - actuatorUpHigh) * ((double)elapsed/(double)actuatorHighDuration);
    		}
    		else
    		{
    			actuatorPower = actuatorUpHigh;
    		}
    	}
    	else
    		actuatorPower = -actuatorDown;
    	
    	// gear goes out if negative, in if positive
    	double gearPower = -direction * gearInOut;
    	
    	Robot.arm.set(actuatorPower, gearPower);
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
    	Robot.arm.set(0,  0);
    }

    protected void interrupted() {
    	end();
    }
}
