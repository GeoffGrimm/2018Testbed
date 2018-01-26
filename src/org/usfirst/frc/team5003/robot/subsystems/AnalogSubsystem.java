package org.usfirst.frc.team5003.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AnalogSubsystem extends Subsystem {

	private AnalogInput analog;
	public Boolean isGood = false;
	
	public AnalogSubsystem(){
		try{
			analog = new AnalogInput(3);
			analog.getVoltage();
		}
		catch (Exception ex) {
			analog = null;
		}
		if (analog != null)
			isGood = true;
	}
   
    public void initDefaultCommand() {
    }
    
    public void show() {
    	SmartDashboard.putNumber("analog",              analog.getValue());
    	SmartDashboard.putNumber("analog voltage",      analog.getVoltage());
    	SmartDashboard.putNumber("analog ave",          analog.getAverageValue());
    	SmartDashboard.putNumber("analog ave voltage",  analog.getAverageVoltage());
    }
}

