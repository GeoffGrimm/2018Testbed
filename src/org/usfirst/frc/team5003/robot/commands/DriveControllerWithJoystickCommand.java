package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveControllerWithJoystickCommand extends Command {

    public DriveControllerWithJoystickCommand() {
        requires(Robot.controller);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.controller.driveWithJoystick();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.controller.set(0);
    }

    protected void interrupted() {
    }
}

