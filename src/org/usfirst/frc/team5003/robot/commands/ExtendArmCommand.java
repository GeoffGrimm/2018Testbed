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
        requires(Robot.armTalonSub);
    }

    protected void initialize() {
    	start = Robot.armPotSub.getVoltage();
    	//start = Robot.armCounterSub.start;
    	
    	startDirection = direction(start, destination);
    	//Robot.armCounterSub.setDirection(startDirection);
    	destination = SmartDashboard.getNumber(dataSource, start);
    }

    protected void execute() {
    	double current = Robot.armPotSub.getVoltage();
    	// double current = Robot.armAccSub.get();
    	
    	int currentDirection = direction(current, destination);
    	if (currentDirection != startDirection)
    		isDone = true;
    	else {
    		double speed = 0.3;
    		speed = 0.2 + ((0.8) * Math.abs((current-destination) / (start-destination)));
    		Robot.armTalonSub.set(speed * startDirection);
    	}
    }

    protected boolean isFinished() {
    	Robot.armAccSub.updateAndReset();
        return isDone;
    }
    
    protected void end() {
    	Robot.armTalonSub.set(0);
    }
    
    protected void interrupted() {
    	end();
    }
    
    protected int direction(double from, double to) {
    	return from < to?1:-1;
    }
}
