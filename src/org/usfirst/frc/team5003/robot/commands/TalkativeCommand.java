package org.usfirst.frc.team5003.robot.commands;

import java.util.Date;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TalkativeCommand extends Command {

	String configuration;
	long duration;
	long start;
	long lastMessage;

    public TalkativeCommand(String configuration, long secsToRun){
    	this();
    	this.configuration = configuration;
    	this.duration = secsToRun*1000;
    	//Robot.log(String.format("%s ctor", this.configuration));
    }
    
    public TalkativeCommand() {
    	//requires(Robot.emptySub);
    }
    
    protected void initialize() {
    	Robot.log(String.format("%s init", configuration));
    	start = new Date().getTime();
    	lastMessage = start;
    }

    protected void execute() {
    	long now = new Date().getTime();
    	// display executing message every 1 sec, not on EVERY call to execute()
    	if (now - lastMessage > 1000)
    	{
    		Robot.log(String.format("%s execute", configuration));
    		lastMessage = now;
    	}
    }

    protected boolean isFinished() {
    	long now = new Date().getTime();
    	if (now - start > duration){
    		Robot.log(String.format("%s isFinished", configuration));
    		return true;
    	}
        return false;
    }

    protected void end() {
    	Robot.log(String.format("%s end", configuration));
    }

    protected void interrupted() {
    	Robot.log(String.format("%s interrupted", configuration));
    }
}
