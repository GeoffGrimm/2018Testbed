package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmSubsystem extends Subsystem {

	private PWMSpeedController actuator;
	private AnalogSubsystem actuatorPot;
	private PWMSpeedController gear;
	private AnalogSubsystem gearPot;
	
	public Boolean isGood;
	
	public ArmSubsystem(PWMSpeedController actuator, int actuatorPotCh, PWMSpeedController gear, int gearPotCh) 
	{
		try 
		{
			this.actuator = actuator;
			this.actuator.set(0);
			actuatorPot = new AnalogSubsystem(actuatorPotCh);
			actuatorPot.getVoltage();
			this.gear = gear;
			this.gear.set(0);
			gearPot = new AnalogSubsystem(gearPotCh);
			gearPot.getVoltage();
		}
		catch (Exception ex)
		{
			isGood = false;
		}
	}
	
	public void set(double actuatorPower, double gearPower)
	{
		actuator.set(ProtectedControllerSubsystem.checkPower(actuatorPower, actuatorPot.getVoltage(), Robot.ActuatorPotNegativeLimit, Robot.ActuatorPotPositiveLimit));
		gear.set(ProtectedControllerSubsystem.checkPower(gearPower, gearPot.getVoltage(), Math.min(getGearLimit(), Robot.GearPotNegativeLimit), Robot.GearPotPositiveLimit));
	}
	
	
    // given actuator position, what should the gear position be to maintain 16" away from robot edge
	// we have N points, numbered 0->N-1
	// iMax is N-1
	private static double[] aVals = new double[] {2.335, 2.423, 2.796, 3.031, 3.291, 3.369, 3.459};
    private static double[] gVals = new double[] {1.162, 1.350, 1.401, 1.650, 1.980, 2.335, 2.682};
    private double getGearLimit() 
    {
    	// start with actuator postion
    	double a = actuatorPot.getVoltage();
    	
    	// find interval containing a
    	int iMax = aVals.length - 1;
    	int j = -1;
    	
    	// first two cases are outside the range of values.  may want to do something different
    	if (a < aVals[0])
    		j = 0;
    	else if (a >= aVals[iMax])
    		j = iMax-1;
    	// a in the range of a values, look for interval
    	else 
    	{
	    	for (int i = 0; i < iMax; i++) {
	    		if (a >= aVals[i] && a < aVals[i+1]) {
	    			j = i;
	    			break;
	    		}
	    	}
    	}
    	// fell thru, shouldn't happen
    	if (j == -1)
    		return gear.getPosition();

    	return gVals[j] + ((gVals[j+1] - gVals[j]) / (aVals[j+1] - aVals[j])) * (a - aVals[j]);
    	
    }
    
    public void show()
    {
    	SmartDashboard.putNumber("Actuator", actuator.get());
    	SmartDashboard.putNumber("Actuator Pot", actuatorPot.getVoltage());
    	SmartDashboard.putNumber("Actuator Positive Limit", Robot.ActuatorPotPositiveLimit);
    	SmartDashboard.putNumber("Actuator Negative Limit", Robot.ActuatorPotNegativeLimit);
    	SmartDashboard.putNumber("Gear", gear.get());
    	SmartDashboard.putNumber("Gear Pot", gearPot.getVoltage());
    	SmartDashboard.putNumber("Gear Positive Limit", Robot.GearPotPositiveLimit);
    	SmartDashboard.putNumber("Gear Negative Limit", Robot.GearPotNegativeLimit);
    	SmartDashboard.putNumber("Gear Limit", getGearLimit());
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

