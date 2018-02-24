package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// http://www.andymark.com/product-p/am-3515.htm
// http://files.andymark.com/PDFs/UsingL16LinearServo.pdf
public class GrabberServoSubsystem extends Subsystem {
	public Servo servoA;
	public Servo servoB;
	public boolean isGood = false;

	public GrabberServoSubsystem(int chA, int chB){
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
			Robot.log(String.format("Grabber on %d failed", chA));
		}
	}

    public void initDefaultCommand() {
    }
    
    public void set(double value) {
    	servoA.set(value);
    	servoB.set(value);
    }
    
    public void show() {
    	if (isGood) {
    		SmartDashboard.putNumber("Grabber A    ", servoA.get());
	    	//SmartDashboard.putNumber("Grabber A Raw", servoA.getRaw());
	    	    	
	    	SmartDashboard.putNumber("Grabber B    ", servoB.get());
	    	//SmartDashboard.putNumber("Grabber B Raw", servoB.getRaw());
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

