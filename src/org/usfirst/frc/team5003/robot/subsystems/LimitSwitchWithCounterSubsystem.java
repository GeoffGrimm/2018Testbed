package org.usfirst.frc.team5003.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimitSwitchWithCounterSubsystem extends Subsystem {

	private DigitalInput limitSwitch;
	private Counter counter;
	public boolean isGood = false;
	
	public LimitSwitchWithCounterSubsystem(int ch){
		try {
			limitSwitch = new DigitalInput(ch);
			counter = new Counter(limitSwitch);
			counter.reset();
			isGood = true;
		}
		catch (Exception ex) {
			limitSwitch = null;
			counter = null;
			isGood = false;
		}
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
    	if (isGood) {
    		SmartDashboard.putString("Limit Switch", String.format("%s",  limitSwitch.get()));
    		SmartDashboard.putNumber("Counter", counter.get());
    	}
    }
    
    
}

