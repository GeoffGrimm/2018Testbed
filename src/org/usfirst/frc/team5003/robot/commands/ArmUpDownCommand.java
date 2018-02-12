package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArmUpDownCommand extends Command {
	
	double power;

    public ArmUpDownCommand(double power) {
        requires(Robot.gearController);
        requires(Robot.actuatorController);
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.gearController.set(0);
    	Robot.actuatorController.set(0);
    }

    protected void interrupted() {
    	end();
    }
}
