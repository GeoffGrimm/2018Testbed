package org.usfirst.frc.team5003.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// http://files.andymark.com/PDFs/UsingL16LinearServo.pdf
public class ServoSubsystem extends Subsystem {
	public static double fullIn = 0.18;
	public static double fullOut = 1.0;
	private static int channelA = 8;
	private static int channelB = 9;
	public Servo servoA;
	public Servo servoB;
	public Boolean isGood = false;

	public ServoSubsystem(){
		isGood = false;
		try{
			servoA = new Servo(channelA);
			servoA.getRaw();
			servoB = new Servo(channelB);
			servoB.getRaw();
		}
		catch (Exception ex){
			servoA = null;
			servoB = null;
		}
		if (servoA != null && servoB != null)
			isGood = true;	
	}

    public void initDefaultCommand() {
    }
    
    public void set(double percent){
    	double value = ((double)percent / 100 ) * (fullOut-fullIn) + fullIn;
    	if (value < fullIn)
    		value = fullIn;
    	if (value > fullOut)
    		value = fullOut;
    	servoA.set(value);
    	servoB.set(value);
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
        
}

