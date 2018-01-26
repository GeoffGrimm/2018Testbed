package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;
import org.usfirst.frc.team5003.robot.commands.DriveWithJoystickCommand;
import org.usfirst.frc.team5003.robot.commands.SingleTalonDriveWithJoystickCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SingleTalonSubsystem extends Subsystem {

	public Talon talon;
	public Boolean isGood = false;
	
	public SingleTalonSubsystem(){
		try{
			talon = new Talon(3);
			talon.stopMotor();
		}
		catch (Exception ex) {
			talon = null;
		}
		if (talon != null)
			isGood = true;
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
    	SmartDashboard.putNumber("talon", talon.get());
    }
}

