package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoCommand extends Command {

	int value = 0;
	String dataSource = null;
	
    public ServoCommand(int value) {
        requires(Robot.servoSub);
        this.value = value;
    }
    
    public ServoCommand(String dataSource) {
        requires(Robot.servoSub);
        this.dataSource = dataSource;
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
