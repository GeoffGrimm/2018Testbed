package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.OI;
import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GroupBuilderCommand extends Command {
	
	String commandString;

	// pass in a string (autonomous)
    public GroupBuilderCommand(String commandString) {
    	this.commandString = commandString;
    }
    
    // use string from SmartDashboard
	public GroupBuilderCommand() {
    	commandString = null;
    }

    protected void initialize() {
    	CommandParser p = new CommandParser();
    	if (commandString == null)
    		commandString = SmartDashboard.getString(OI.CommandString, "");
    	if (!p.init(commandString))
    	{
    		Robot.log(p.debug);
    		return;
    	}
    	
    	CommandGroup g = new CommandGroup();
    	for (int i = 0; i < p.commands.size(); i++) {
    		g.addSequential(p.commands.get(i));
    	}
    	g.start();
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
