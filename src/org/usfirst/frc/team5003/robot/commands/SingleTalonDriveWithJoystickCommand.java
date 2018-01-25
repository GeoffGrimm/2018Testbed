package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SingleTalonDriveWithJoystickCommand extends Command {

    public SingleTalonDriveWithJoystickCommand() {
        requires(Robot.talonSub);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.talonSub.driveWithJoystick();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.talonSub.set(0);
    }

    protected void interrupted() {
    }
}
