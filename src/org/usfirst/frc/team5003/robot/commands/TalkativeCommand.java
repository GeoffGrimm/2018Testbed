package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TalkativeCommand extends Command {

	String name = "name";
	boolean finished = true;
	
    public TalkativeCommand() {
    	requires(Robot.emptySub);
    }
    
    public TalkativeCommand(String name, boolean finished)
    {
    	this.name = name;
    	this.finished = finished;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.log(name + " : initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.log(name + " : execute");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.log(name + " : isFinished? " + String.valueOf(finished));
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.log(name + " : end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.log(name + " : interrupted");
    }
}
