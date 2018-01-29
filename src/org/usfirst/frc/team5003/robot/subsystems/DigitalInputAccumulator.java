package org.usfirst.frc.team5003.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DigitalInputAccumulator extends Subsystem {

	private DigitalInput input;
	private Counter counter;
	public int start;
	private int direction;
	public boolean isGood = false;
	
	public DigitalInputAccumulator(int ch){
		try {
			input = new DigitalInput(ch);
			counter = new Counter(input);
			counter.reset();
			start = 0;
			direction = 1;
			isGood = true;
		}
		catch (Exception ex) {
			input = null;
			counter = null;
			isGood = false;
		}
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void updateAndReset() {
		start = get();
		counter.reset();
	}
	
	public int get() {
		return start + counter.get()*direction;
	}
	
	public void show(){
		if (isGood)
		{
			SmartDashboard.putNumber("DIC", input.get()?1:0);
			SmartDashboard.putNumber("DIC count",  counter.get());
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

