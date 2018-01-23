package org.usfirst.frc.team5003.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroSubsystem extends Subsystem {

	public ADXRS450_Gyro gyro;
	public Boolean isGood = false;
	
	public GyroSubsystem(){
		isGood = false;
		try{
			gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
			gyro.calibrate();
		}
		catch (Exception ex) {
			gyro = null;
		}
		if (gyro != null)
			isGood = true;
	}


    public void initDefaultCommand() {
    }
    
    public double getAngle(){
    	return gyro.getAngle();
    }
    public void show() {
    	if (isGood) {
    		SmartDashboard.putNumber("Gyro Angle",  gyro.getAngle());
    	}
    }
}

