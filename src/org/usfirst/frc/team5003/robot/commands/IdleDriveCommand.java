package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

// this does nothing except send of stream of stop commands to the drivetrain
// keeps the system from thinking the drive has timed out
public class IdleDriveCommand extends Command {

    public IdleDriveCommand() {
        requires(Robot.drivetrainSub);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrainSub.stop();
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