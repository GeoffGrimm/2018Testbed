package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;
import org.usfirst.frc.team5003.robot.commands.DriveControllerWithJoystickCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ControllerSubsystem extends Subsystem {

	public int ch;
	public Talon talon;
	public boolean isGood = false;
	
	public ControllerSubsystem(int ch){
		try {
			this.ch = ch;
			talon = new Talon(this.ch);
			talon.get();
			isGood = true;
		}
		catch (Exception ex) {
			talon = null;
			isGood = false;
			Robot.log(String.format("Controller on %d failed", ch));
		}
	}

    public void initDefaultCommand() {
    	if (talon.getChannel() == 5)
    		setDefaultCommand(new DriveControllerWithJoystickCommand());
    }
    
    public void set(double value) {
    	talon.set(value);
    }
    
    public void driveWithJoystick() {
    	talon.set(Robot.xbox.getX());
    }
    
    public void show() {
    	if (isGood)
    		SmartDashboard.putNumber(String.format("Talon on %d", ch), talon.get());
    }
}

