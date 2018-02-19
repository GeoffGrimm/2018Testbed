package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AnalogSubsystem extends Subsystem {

	private AnalogInput analog = null;
	public boolean isGood = false;
	
	public AnalogSubsystem(int ch){
		try {
			analog = new AnalogInput(ch);
			analog.getValue();
			isGood = true;
		}	
		catch (Exception ex) {
			analog = null;
			isGood = false;
			Robot.log(String.format("Analog on %d failed", ch));
		}
	}
   
    public void initDefaultCommand() {
    }
    
    public void show() {
    	if (isGood) {
	    	SmartDashboard.putNumber(String.format("Analog on %d", analog.getChannel()),              analog.getVoltage());
    	}
    }
    public double getVoltage() {
    	return analog.getVoltage();
    }
}

