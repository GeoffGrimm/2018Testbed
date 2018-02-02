package org.usfirst.frc.team5003.robot.commands;

import org.usfirst.frc.team5003.robot.subsystems.ControllerSubsystem;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveWithPowerCommand extends Command {
	ControllerSubsystem talon;
	String powerKey;
	double power;
	int direction;

    public DriveWithPowerCommand(ControllerSubsystem talon, String powerKey, int direction) {
    	this.talon = talon;
    	this.powerKey = powerKey;
    	this.direction = direction;
        requires(this.talon);
    }

    protected void initialize() {
    	power = SmartDashboard.getNumber(powerKey,  0.0);
    }

    protected void execute() {
    	talon.set(power * direction);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	talon.set(0);
    }

    protected void interrupted() {
    	end();
    }
}
