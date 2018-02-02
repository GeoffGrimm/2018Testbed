package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GrabberCommand extends Command {

	String dataSource = null;
	double value = 0;
    
    public GrabberCommand(String dataSource) {
        this();
        this.dataSource = dataSource;
    }
    
	public GrabberCommand(double d) {
        this();
        this.value = d;
    }
	
    public GrabberCommand(){
    	requires(Robot.grabber);
    }
    
    protected void initialize() {
    	if (this.dataSource != null) {
    		value = SmartDashboard.getNumber(dataSource, 0);
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
