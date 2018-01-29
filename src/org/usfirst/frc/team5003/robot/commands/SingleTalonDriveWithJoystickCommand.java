package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SingleTalonDriveWithJoystickCommand extends Command {

    public SingleTalonDriveWithJoystickCommand() {
        requires(Robot.armTalonSub);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.armTalonSub.driveWithJoystick();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.armTalonSub.set(0);
    }

    protected void interrupted() {
    }
}
