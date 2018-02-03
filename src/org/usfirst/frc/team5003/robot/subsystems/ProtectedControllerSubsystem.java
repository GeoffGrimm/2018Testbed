package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;
import org.usfirst.frc.team5003.robot.commands.DriveProtectedControllerWithJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ProtectedControllerSubsystem extends Subsystem {

	public Talon talon;
	public AnalogInput pot;
	public Encoder enc;
	public DigitalInput sw0, sw1;
	// -1 to 12 should never force the motor to stop
	// 1 to 4 ought to provide good buffering (6 turns with 2 on each end to spare
	public double minPos = 0;
	public double maxPos = 0;
	
	public boolean isGood = false;

	// protected by pot
	public ProtectedControllerSubsystem(int controllerCh, int potCh, double minPos, double maxPos){
		this(controllerCh, potCh, -1, -1, -1, -1, -1, minPos, maxPos);
	}	
	// protected by encoder
	public ProtectedControllerSubsystem(int controllerCh, int chA, int chB, int chX, double minPos, double maxPos){
		this(controllerCh, -1, chA, chB, chX, -1, -1, minPos, maxPos);
	}
	// protected by switches
	public ProtectedControllerSubsystem(int controllerCh, int ch0, int ch1){
		this(controllerCh, -1, -1, -1, -1, ch0, ch1, 0, 0);
	}

	public ProtectedControllerSubsystem(int controllerCh, int potCh, int chA, int chB, int chX, int ch0, int ch1, double minPos, double maxPos){
		try {
			talon = new Talon(controllerCh);
			talon.set(0);
			pot = null;
			enc = null;
			sw0 = null;
			sw1 = null;
			if (potCh >= 0) {
				pot = new AnalogInput(potCh);
				pot.getVoltage();
			}
			else if (chA > 0) {
				enc = new Encoder(chA, chB, chX);
				enc.reset();
			}
			else {
				sw0 = new DigitalInput(ch0);
				sw0.get();
				sw1 = new DigitalInput(ch1);
				sw1.get();
			}
			
			this.minPos = minPos;
			this.maxPos = maxPos;
			isGood = true;
		}
		catch (Exception ex) {
			talon = null;
			pot = null;
			enc = null;
			sw0 = null;
			sw1 = null;
			isGood = false;
			Robot.log(String.format("Protected controller on %d failed", controllerCh));
		}
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveProtectedControllerWithJoystick());
    }

    public void set(double speed) {
		if ((speed < 0 && getPosition() < minPos) ||
		    (speed > 0 && getPosition() > maxPos))
			speed = 0;
		talon.set(speed * 0.10);  // slow it down so don't hose pot
	}
    
    public double getPosition() {
    	if (pot != null)
    		return pot.getVoltage();
    	else if (enc != null)
    		return enc.get();
    	else
    	{
    		if (!sw0.get())
    			return Integer.MIN_VALUE;
    		else if (!sw1.get())
    			return Integer.MAX_VALUE;
    		else
    			return 0;
    	}
    		
    }
    
    public int getDirectionTo(double to) {
    	return getPosition() < to?1:-1;
    }
	
	public void driveWithJoystick() {
		set(Robot.xbox.getX());
	}
	
	public void show() {
		SmartDashboard.putNumber("Controller",  getSpeed());
		SmartDashboard.putNumber("Position", getPosition());
		SmartDashboard.putNumber("Min Pos", minPos);
		SmartDashboard.putNumber("Max Pos", maxPos);
		SmartDashboard.putString("Type", pot != null?"POT":enc != null?"ENCODER":"SWITCH");
		SmartDashboard.putNumber("sw0", sw0.get()?1:0);
		SmartDashboard.putNumber("sw1", sw1.get()?1:0);
	}
	   public void resetEncoder() {
	    	enc.reset();
	    }
	    public double getSpeed() {
	    	return talon.get();
	    }
	 
}

