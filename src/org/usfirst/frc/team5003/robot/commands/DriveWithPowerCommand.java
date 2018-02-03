package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.subsystems.ControllerSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveWithPowerCommand extends Command {
	ControllerSubsystem controller;
	String powerKey;
	double power;
	int direction;

    public DriveWithPowerCommand(ControllerSubsystem controller, String powerKey, int direction) {
    	this.controller = controller;
    	this.powerKey = powerKey;
    	this.direction = direction;
        requires(this.controller);
    }

    protected void initialize() {
    	power = SmartDashboard.getNumber(powerKey,  0.0);
    	controller.set(power * direction);
    }

    protected void execute() {
    	
    }

    // hooked up to joystick button, just exit right away and come around again?
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	controller.set(0);
    }

    protected void interrupted() {
    	end();
    }
}
