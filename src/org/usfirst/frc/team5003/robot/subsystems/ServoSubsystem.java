package org.usfirst.frc.team5003.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// http://files.andymark.com/PDFs/UsingL16LinearServo.pdf
public class ServoSubsystem extends Subsystem {
	public static double fullIn = 0.17;
	public static double fullOut = 0.82;
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
    
    public void set(int percent){
    	System.out.println(percent);
    	double value = ((double)percent / 100 ) * (fullOut-fullIn) + fullIn;
    	if (value < fullIn)
    		value = fullIn;
    	if (value > fullOut)
    		value = fullOut;
    	servoA.set(value);
    	servoB.set(value);
    }
    
    public void show() {
    	SmartDashboard.putNumber("A    ", servoA.get());
    	SmartDashboard.putNumber("A Pos", servoA.getPosition());
    	SmartDashboard.putNumber("A Raw", servoA.getRaw());
    	
    	
    	SmartDashboard.putNumber("B    ", servoB.get());
    	SmartDashboard.putNumber("B Pos", servoB.getPosition());
    	SmartDashboard.putNumber("B Raw", servoB.getRaw());
    	


    	}
        
}

