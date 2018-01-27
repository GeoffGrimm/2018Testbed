package org.usfirst.frc.team5003.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicSubsystem extends Subsystem {

	public Ultrasonic ultrasonic = null;
	public boolean isGood = false;
	
	public UltrasonicSubsystem(){
		try {
			ultrasonic = new Ultrasonic(2,2);
			ultrasonic.setAutomaticMode(true);
			ultrasonic.setEnabled(true);
			ultrasonic.getRangeMM();
			isGood = true;
		}
		catch (Exception ex) {
			ultrasonic = null;
			isGood = true;
		}
	}
    public void initDefaultCommand() {
    }
    
    public void show() {
    	if (isGood)
    		SmartDashboard.putNumber("Ultrasonic",  ultrasonic.getRangeMM());
    	
    }
}

