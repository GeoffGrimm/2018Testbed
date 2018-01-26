package org.usfirst.frc.team5003.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimitSwitchWithCounterSubsystem extends Subsystem {

	private DigitalInput limitSwitch;
	private Counter counter;
	public Boolean isGood = false;
	
	public LimitSwitchWithCounterSubsystem(){
		isGood = false;
		try{
			limitSwitch = new DigitalInput(5);
			counter = new Counter(limitSwitch);
			limitSwitch.get();
			counter.get();
			counter.reset();
		}
		catch (Exception ex){
			limitSwitch = null;
			counter = null;
		}
		if (limitSwitch != null && counter != null)
			isGood = true;
	}

    public void initDefaultCommand() {
    }
    
    public int get(){
    	return counter.get();
    }
    
    public void reset() {
    	counter.reset();
    }
    
    public void show() {
    	SmartDashboard.putString("limit switch", String.format("%s",  limitSwitch.get()));
    	SmartDashboard.putNumber("counter", counter.get());
    }
    
    
}

