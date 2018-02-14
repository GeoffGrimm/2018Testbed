package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystickCommand extends Command {

    public DriveWithJoystickCommand() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	//Robot.drivetrain.driveWithJoystick();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drivetrain.stop();
    }

    protected void interrupted() {
    }
}

