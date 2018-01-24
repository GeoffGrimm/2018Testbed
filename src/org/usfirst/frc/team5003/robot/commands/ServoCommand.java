package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoCommand extends Command {

	String dataSource = null;
	double value = 0;
    
    public ServoCommand(String dataSource) {
        this();
        this.dataSource = dataSource;
    }
    
	public ServoCommand(double d) {
        this();
        this.value = d;
    }
	
    public ServoCommand(){
    	requires(Robot.servoSub);
    }
    
    protected void initialize() {
    	if (this.dataSource != null)
    		value = (int)SmartDashboard.getNumber(dataSource, 0);
    	Robot.servoSub.set(value);
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
