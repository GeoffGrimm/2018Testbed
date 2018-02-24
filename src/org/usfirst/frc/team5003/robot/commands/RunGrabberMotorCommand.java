package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunGrabberMotorCommand extends Command 
{
	String powerKey;
	double power;
	String durationKey;
	int duration;
	
	long startTime;

	// run with both power and duration for autonmous
	public RunGrabberMotorCommand(double power, double duration)
	{
		this();
		this.power = power;
		this.duration = (int)(1000* duration);
	}
	
	// run with both power and duration from SmartDashboard, for testing
	public RunGrabberMotorCommand(String powerKey, String durationKey)
	{
		this();
		this.powerKey = powerKey;
		this.durationKey = durationKey;
	}

	
	// run with just power, for whileHeld
	public RunGrabberMotorCommand(String powerKey)
	{
		this();
		this.powerKey = powerKey;
	}

	// run with joystick input
    public RunGrabberMotorCommand() 
    {
    	powerKey = null;
    	power = Double.MAX_VALUE;
    	durationKey = null;
    	duration = -1;
        requires(Robot.grabber);
    }

    protected void initialize() 
    {
    	if (powerKey != null)
    		power = SmartDashboard.getNumber(powerKey, 0);
    	if (durationKey != null)
    		duration = (int)(1000*SmartDashboard.getNumber(durationKey, 0));
    	startTime = System.currentTimeMillis();
    }

    protected void execute() 
    {
    	// using joystick
    	if (power == Double.MAX_VALUE)
    		Robot.grabber.setFromJoy();
    	// fixed power
    	else
    		Robot.grabber.set(power);
    }

    protected boolean isFinished() 
    {
    	if (duration > 0)
    	{
	    	long elapsed = System.currentTimeMillis() - startTime;
	    	if (elapsed > duration)
	    		return true;
    	}
    	return false;
    }

    protected void end() 
    {
    	Robot.grabber.set(0);
    }

    protected void interrupted() 
    {
    	end();
    }
    

}
