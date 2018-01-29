package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetThreeChannelEncoderCommand extends Command {

    public ResetThreeChannelEncoderCommand() {
        requires(Robot.threeChannelEncoderSub);
    }

    protected void initialize() {
    	Robot.threeChannelEncoderSub.resetAll();
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
