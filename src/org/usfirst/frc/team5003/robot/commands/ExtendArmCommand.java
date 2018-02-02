package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// if A or B tripped, reset destination a little within A and B

public class ExtendArmCommand extends Command {
	String dataSource = null;
	double start;
	double destination;
	int startDirection;
	boolean isDone = false;

	public ExtendArmCommand(String dataSource)
	{
		this();
		this.dataSource = dataSource;
	}
    
	public ExtendArmCommand() {
        requires(Robot.armPotSub);
        requires(Robot.armSwitchASub);
        requires(Robot.armSwitchBSub);
        requires(Robot.armControllerSub);
    }

    protected void initialize() {
    	start = Robot.armPotSub.getVoltage();
    	
    	startDirection = direction(start, destination);
    	destination = SmartDashboard.getNumber(dataSource, start);
    }

    protected void execute() {
    	double current = Robot.armPotSub.getVoltage();
    	
    	int currentDirection = direction(current, destination);
    	if (currentDirection != startDirection)
    		isDone = true;
    	else {
    		double speed = 0.3;
    		speed = 0.2 + ((0.8) * Math.abs((current-destination) / (start-destination)));
    		Robot.controllerSub.set(speed * startDirection);
    	}
    }

    protected boolean isFinished() {
        return isDone;
    }
    
    protected void end() {
    	Robot.controllerSub.set(0);
    }
    
    protected void interrupted() {
    	end();
    }
    
    // returns +1 if from < to, meaning we move to the right in a positive direction and make the arm longer
    // returns -1 if need to make arm shorter
    protected int direction(double from, double to) {
    	return from < to?1:-1;
    }
}
