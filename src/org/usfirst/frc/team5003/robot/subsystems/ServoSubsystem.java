package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// http://www.andymark.com/product-p/am-3515.htm
// http://files.andymark.com/PDFs/UsingL16LinearServo.pdf
public class ServoSubsystem extends Subsystem {
	private static int chA = 8;
	private static int chB = 9;
	public Servo servoA;
	public Servo servoB;
	public boolean isGood = false;

	public ServoSubsystem(){
		try {
			servoA = new Servo(chA);
			servoA.get();
			servoB = new Servo(chB);
			servoB.get();
			isGood = true;
		}
		catch (Exception ex) {
			servoA = null;
			servoB = null;
			isGood = false;
		}
	}

    public void initDefaultCommand() {
    }
    
    public void set(double value) {
    	servoA.set(value);
    	servoB.set(value);
    }
    
    public void setRaw(int value) {
    	servoA.setRaw(value);
    	servoB.setRaw(value);
    }
    
    public void show() {
    	if (isGood) {
    		SmartDashboard.putNumber("A    ", servoA.get());
	    	SmartDashboard.putNumber("A Pos", servoA.getPosition());
	    	SmartDashboard.putNumber("A Raw", servoA.getRaw());
	    	    	
	    	SmartDashboard.putNumber("B    ", servoB.get());
	    	SmartDashboard.putNumber("B Pos", servoB.getPosition());
	    	SmartDashboard.putNumber("B Raw", servoB.getRaw());
    	}
    }
    /*
    public void setPercent(double percent){
    	double value = ((double)percent / 100 ) * (fullOut-fullIn) + fullIn;
    	if (value < fullIn)
    		value = fullIn;
    	if (value > fullOut)
    		value = fullOut;
    	servoA.set(value);
    	servoB.set(value);
    }
    */
        
}

