package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GrabberCommand extends Command {

	String valueKey = null;
	double value = 0;
    
    public GrabberCommand(String valueKey) {
        this();
        this.valueKey = valueKey;
    }
    
	public GrabberCommand(double value) {
        this();
        this.value = value;
    }
	
    public GrabberCommand(){
    	requires(Robot.grabber);
    }
    
    protected void initialize() {
    	if (this.valueKey != null) {
    		value = SmartDashboard.getNumber(valueKey, 0);
    	}
    	Robot.grabber.set(value);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
