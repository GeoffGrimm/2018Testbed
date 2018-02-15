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
	
	private int direction;
	
    public ArmUpDownCommand(int direction) {
    	this.direction = direction;
        requires (Robot.arm);
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
    	double actuatorPower;
    	
    	if (direction > 0)
    	{
    		long elapsed = new Date().getTime() - startTime;
    		if (elapsed <= actuatorHighDuration)
    			actuatorPower = actuatorUpHigh;
    		else
    			actuatorPower = actuatorUpLow;
    	}
    	else
    		actuatorPower = -actuatorDown;
    	
    	Robot.arm.set(actuatorPower, gearInOut);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.arm.set(0,  0);
    }

    protected void interrupted() {
    	end();
    }
}
