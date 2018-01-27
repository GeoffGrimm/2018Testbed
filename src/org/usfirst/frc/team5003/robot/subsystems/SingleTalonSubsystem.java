package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;
import org.usfirst.frc.team5003.robot.commands.SingleTalonDriveWithJoystickCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SingleTalonSubsystem extends Subsystem {

	public Talon talon;
	public boolean isGood = false;
	
	public SingleTalonSubsystem(){
		try {
			talon = new Talon(3);
			talon.get();
			isGood = true;
		}
		catch (Exception ex) {
			talon = null;
			isGood = false;
		}
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new SingleTalonDriveWithJoystickCommand());
    }
    
    public void set(double value) {
    	talon.set(value);
    }
    
    public void driveWithJoystick() {
    	talon.set(Robot.joystick.getY());
    }
    
    public void show() {
    	if (isGood)
    		SmartDashboard.putNumber("talon", talon.get());
    }
}

