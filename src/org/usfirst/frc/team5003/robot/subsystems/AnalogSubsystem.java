package org.usfirst.frc.team5003.robot.subsystems;

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
		}
	}
   
    public void initDefaultCommand() {
    }
    
    public void show() {
    	if (isGood) {
	    	SmartDashboard.putNumber("analog",              analog.getValue());
	    	SmartDashboard.putNumber("analog voltage",      analog.getVoltage());
	    	SmartDashboard.putNumber("analog ave",          analog.getAverageValue());
	    	SmartDashboard.putNumber("analog ave voltage",  analog.getAverageVoltage());
	    	SmartDashboard.putNumber("analog ave bits",     analog.getAverageBits());
    	}
    }
    public double getVoltage() {
    	return analog.getVoltage();
    }
}

