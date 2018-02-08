package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;
import org.usfirst.frc.team5003.robot.commands.DriveWithJoystickCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ControllerSubsystem extends Subsystem {

	public int ch;
	public Talon talon = null;
	public Victor victor = null;
	public boolean isGood = false;
	
	public ControllerSubsystem(int ch, boolean useVictor){
		try {
			this.ch = ch;
			if (useVictor) {
				victor = new Victor(this.ch);
				talon = null;
			}
			else {
				talon = new Talon(this.ch);
				talon.get();
			}
			isGood = true;
		}
		catch (Exception ex) {
			victor = null;
			talon = null;
			isGood = false;
			Robot.log(String.format("Controller on %d failed", ch));
		}
	}

    public void initDefaultCommand() {
 //   	if (talon.getChannel() == 5)
 //   		setDefaultCommand(new DriveWithJoystickCommand());
    }
    
    public void set(double value) {
    	if (victor != null) {
    		victor.set(value);
    	}
    	else {
    		talon.set(value);
    	}
    }
    
    public void driveWithJoystick() {
    	if (victor != null) {
    		victor.set(Robot.xbox.getX());
    	}
    	else {
    		talon.set(Robot.xbox.getX());
    	}
    }
    
    public void show() {
    	if (isGood)
    		if (victor != null)
        		SmartDashboard.putNumber(String.format("Victor on %d", ch), victor.get());
    		else
    			SmartDashboard.putNumber(String.format("Talon on %d", ch), talon.get());
    }
}

