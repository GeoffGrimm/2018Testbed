package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {

    public DriveCommand() {
        requires(Robot.drivetrainSub);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrainSub.drive();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drivetrainSub.stop();
    }

    protected void interrupted() {
    }
}

