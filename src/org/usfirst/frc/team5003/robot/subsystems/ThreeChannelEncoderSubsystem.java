package org.usfirst.frc.team5003.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ThreeChannelEncoderSubsystem extends Subsystem {

	private DigitalInput inA, inB, inC;
	private Counter countA, countB, countC;
	public boolean isGood = false;
	
	public ThreeChannelEncoderSubsystem(int chA, int chB, int chC){
		try {
			inA = new DigitalInput(chA);
			inB = new DigitalInput(chB);
			inC = new DigitalInput(chC);
			countA = new Counter(inA);
			countB = new Counter(inB);
			countC = new Counter(inC);
			resetAll();
			isGood = true;
		}
		catch (Exception ex) {
			inA = null;
			inB = null;
			inC = null;
			countA = null;
			countB = null;
			countC = null;
			isGood = false;
		}
	}
	
	public void resetAll() {
		countA.reset();
		countB.reset();
		countC.reset();
	}
	public void show(){
		if (isGood)
		{
			SmartDashboard.putNumber("a", inA.get()?1:0);
			SmartDashboard.putNumber("a count",  countA.get());
			SmartDashboard.putNumber("b", inB.get()?1:0);
			SmartDashboard.putNumber("b count",  countB.get());
			SmartDashboard.putNumber("c", inC.get()?1:0);
			SmartDashboard.putNumber("c count",  countC.get());
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

