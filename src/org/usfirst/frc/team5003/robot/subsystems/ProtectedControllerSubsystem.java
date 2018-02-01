package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;
import org.usfirst.frc.team5003.robot.commands.DriveProtectedControllerWithJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ProtectedControllerSubsystem extends Subsystem {

	public Talon controller;
	public AnalogInput pot;
	public Encoder enc;
	// -1 to 12 should never force the motor to stop
	// 1 to 4 ought to provide good buffering (8 turns with 1 on each end to spare
	public double minPos = 0;
	public double maxPos = 0;
	
	public boolean isGood = false;

	public ProtectedControllerSubsystem(int controllerCh, int chA, int chB, int chX, double minPos, double maxPos){
		this(controllerCh, -1, chA, chB, chX, minPos, maxPos);
	}
	
	public ProtectedControllerSubsystem(int controllerCh, int potCh, double minPos, double maxPos){
		this(controllerCh, potCh, -1, -1, -1, minPos, maxPos);
	}

	public ProtectedControllerSubsystem(int controllerCh, int potCh, int chA, int chB, int chX, double minPos, double maxPos){
		try {
			controller = new Talon(controllerCh);
			controller.set(0);
			if (potCh >= 0) {
				pot = new AnalogInput(potCh);
				pot.getVoltage();
				enc = null;
			}
			else{
				enc = new Encoder(chA, chB, chX);
				enc.reset();
				pot = null;
			}
			this.minPos = minPos;
			this.maxPos = maxPos;
			isGood = true;
		}
		catch (Exception ex) {
			controller = null;
			pot = null;
			enc = null;
			isGood = false;
		}
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveProtectedControllerWithJoystick());
    }

    public void set(double speed) {
		if ((speed < 0 && getPosition() < minPos) ||
		    (speed > 0 && getPosition() > maxPos))
			speed = 0;
		controller.set(speed * 0.10);  // slow it down so don't hose pot
	}
    
    public void resetEncoder() {
    	enc.reset();
    }
    public double getSpeed() {
    	return controller.get();
    }
    public double getPosition() {
    	if (pot != null)
    		return pot.getVoltage();
    	else
    		return enc.get();
    }
	
	public void driveWithJoystick() {
		set(Robot.joystick.getX());
	}
	
	public void show() {
		SmartDashboard.putNumber("Controller",  getSpeed());
		SmartDashboard.putNumber("Position", getPosition());
		SmartDashboard.putNumber("Min Pos", minPos);
		SmartDashboard.putNumber("Max Pos", maxPos);
		SmartDashboard.putString("Type", pot != null?"POT":"ENCODER");
	}

}

