package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.subsystems.ProtectedControllerSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunProtectedControllerToPositionCommand extends Command {
	ProtectedControllerSubsystem controller;
	String positionKey;
	double position;
	int initialDirection;
	boolean isDone = false;
	double lowPower = 0.20;  // lowest power to use when close to destination

    public RunProtectedControllerToPositionCommand(ProtectedControllerSubsystem controller, String positionKey) {
    	this.controller = controller;
    	this.positionKey = positionKey;
    	requires(this.controller);
    }

    protected void initialize() {
    	position = SmartDashboard.getNumber(positionKey, controller.getPosition());
    	initialDirection = controller.getDirectionTo(position);
    	isDone = false;

    }

    protected void execute() {
    	double power;
    	// if the direction to get to the target has changed, we've passed it and need to stop
    	if (controller.getDirectionTo(position) != initialDirection)
    	{
    		power = 0;
    		isDone = true;
    	}
    	else
    		power = lowPower + (1-lowPower) * (position - controller.getPosition()) / (Math.abs(controller.positiveLimit-controller.negativeLimit));
    	controller.set(power);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	controller.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
