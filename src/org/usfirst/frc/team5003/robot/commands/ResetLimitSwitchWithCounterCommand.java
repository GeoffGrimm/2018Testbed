package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetLimitSwitchWithCounterCommand extends Command {

    public ResetLimitSwitchWithCounterCommand() {
        requires(Robot.armSwitchASub);
    }

    protected void initialize() {
    	Robot.armSwitchASub.reset();
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
