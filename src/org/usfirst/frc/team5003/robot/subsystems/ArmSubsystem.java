package org.usfirst.frc.team5003.robot.subsystems;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmSubsystem extends Subsystem {

	private PWMSpeedController actuator;
	private AnalogSubsystem actuatorPot;
	private double actuatorMin = 1, actuatorMax = 4;
	private PWMSpeedController gear;
	private AnalogSubsystem gearPot;
	private double gearMin = 1, gearMax = 4;
	
	public Boolean isGood;
	
	public ArmSubsystem(PWMSpeedController actuator, int actuatorPotCh, PWMSpeedController gear, int gearPotCh) {
		try {
			this.actuator = actuator;
			this.actuator.set(0);
			actuatorPot = new AnalogSubsystem(actuatorPotCh);
			actuatorPot.getVoltage();
			this.gear = gear;
			this.gear.set(0);
			gearPot = new AnalogSubsystem(gearPotCh);
			gearPot.getVoltage();
		}
		catch (Exception ex){
			isGood = false;
		}
	}
	
	public void set(double actuatorPower, double gearPower)
	{
		actuator.set(checkPower(actuatorPower, actuatorPot.getVoltage(), actuatorMin, actuatorMax));
		gear.set(checkPower(gearPower, gearPot.getVoltage(), gearMin, Math.min(getGearLimit(), gearMax)));
		
	}
	
	private double checkPower(double power, double pos, double min, double max)
	{
		if ((pos < min && power < 0) || (pos > max && power > 0))
			return 0;
		return power;
	}

    // given actuator position, what should the gear position be to maintain 16" away from robot edge
    private static double[] aVals = new double[] {1, 2, 3, 4, 5, 6};
    private static double[] gVals = new double[] {1, 1, 2, 3, 5, 8};
    private double getGearLimit() {
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
    	else {
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
    	SmartDashboard.putNumber("Actuator Min", actuatorMin);
    	SmartDashboard.putNumber("Actuator Max", actuatorMax);
    	SmartDashboard.putNumber("Gear", gear.get());
    	SmartDashboard.putNumber("Gear Pot", gearPot.getVoltage());
    	SmartDashboard.putNumber("Gear Min", gearMin);
    	SmartDashboard.putNumber("Gear Max", gearMax);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

