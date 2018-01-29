package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RawServoCommand extends Command {
	String dataSource = null;
	int value = 0;
    
    public RawServoCommand(String dataSource) {
        this();
        this.dataSource = dataSource;
    }
    
	public RawServoCommand(int d) {
        this();
        this.value = d;
    }
	
    public RawServoCommand(){
    	requires(Robot.grabberSub);
    }
    
    protected void initialize() {
    	if (this.dataSource != null)
    		value = (int)SmartDashboard.getNumber(dataSource, 0);
    	Robot.grabberSub.setRaw(value);
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
