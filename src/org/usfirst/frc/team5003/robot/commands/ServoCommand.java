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
        Robot.log(String.format("ctor %s",  dataSource));
    }
    
	public ServoCommand(double d) {
        this();
        this.value = d;
    }
	
    public ServoCommand(){
    	requires(Robot.servoSub);
    }
    
    protected void initialize() {
    	if (this.dataSource != null) {
    		Robot.log(String.format("init data source %s",  dataSource));
    		value = SmartDashboard.getNumber(dataSource, 0);
    		
    	}
    	Robot.log(String.format("init %d",  value));
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
