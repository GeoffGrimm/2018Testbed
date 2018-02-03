package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveProtectedControllerWithJoystick extends Command {

    public DriveProtectedControllerWithJoystick() {
        requires(Robot.protectedController);
    }

    protected void initialize() {
    }
    
    protected void execute() {
    	Robot.protectedController.driveWithJoystick();
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    	Robot.protectedController.set(0);
    }
    
    protected void interrupted() {
    	end();
    }
}
