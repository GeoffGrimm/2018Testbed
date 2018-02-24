package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;
import org.usfirst.frc.team5003.robot.commands.RunProtectedControllerWithJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ProtectedControllerSubsystem extends Subsystem {

	private String name;
	private PWMSpeedController controller;
	private AnalogInput pot;
	private Encoder enc;
	private DigitalInput sw0, sw1;
	
	public double negativeLimit = 0;
	public double positiveLimit = 0;
	public boolean isGood = false;

	// protected by pot
	public ProtectedControllerSubsystem(String name, PWMSpeedController controller, int potCh, double negativeLimit, double positiveLimit){
		this(name, controller, potCh, -1, -1, -1, -1, -1, negativeLimit, positiveLimit);
	}	
	// protected by encoder
	public ProtectedControllerSubsystem(String name, PWMSpeedController controller, int chA, int chB, int chX, double negativeLimit, double positiveLimit){
		this(name, controller, -1, chA, chB, chX, -1, -1, negativeLimit, positiveLimit);
	}
	// protected by switches
	public ProtectedControllerSubsystem(String name, PWMSpeedController controller, int ch0, int ch1){
		// min/max is set to 0
		// position of switch will return 0 if not tripped, or -1/+1 if tripped, which exceed the limits
		this(name, controller, -1, -1, -1, -1, ch0, ch1, 0, 0);
	}
	// not really protected
	public ProtectedControllerSubsystem(String name, PWMSpeedController controller)
	{
		this(name, controller, -1, -1, -1, -1, -1, -1, 0, 0);
	}

	public ProtectedControllerSubsystem(String name, PWMSpeedController controller, int potCh, int chA, int chB, int chX, int ch0, int ch1, double negativeLimit, double positiveLimit){
		try {
			this.name = name;
			this.controller = controller;
			controller.set(0);
			pot = null;
			enc = null;
			sw0 = null;
			sw1 = null;
			if (potCh >= 0) {
				pot = new AnalogInput(potCh);
				pot.getVoltage();
			}
			else if (chA >= 0) {
				enc = new Encoder(chA, chB, chX);
				enc.reset();
			}
			else if (ch0 >= 0){
				sw0 = new DigitalInput(ch0);
				sw0.get();
				sw1 = new DigitalInput(ch1);
				sw1.get();
			}
			
			this.negativeLimit = negativeLimit;
			this.positiveLimit = positiveLimit;
			isGood = true;
		}
		catch (Exception ex) {
			Robot.log(String.format("Protected controller on %d failed", this.controller.getChannel()));
			this.controller = null;
			pot = null;
			enc = null;
			sw0 = null;
			sw1 = null;
			isGood = false;
		}
	}
	
    public void initDefaultCommand() {
        //setDefaultCommand(new RunProtectedControllerWithJoystick());
    }
    
    public void set(double power) 
    {
		controller.set(checkPower(power, getPosition(), negativeLimit, positiveLimit)); 
	}
    
	public static double checkPower(double power, double pos, double negativeLimit, double positiveLimit)
	{
		if (negativeLimit < positiveLimit)
		{
			if ((pos < negativeLimit && power < 0) || (pos > positiveLimit && power > 0))
				power = 0;
		}
		else 
		{
			if ((pos > negativeLimit && power < 0) || (pos < positiveLimit && power > 0))
				power = 0;
		}
		return power;
	}

   
 	public void driveWithJoystick() {
		set(Robot.joy.getX());
	}
 	
 	public double getPosition() 
 	{
    	if (pot != null)
    		return pot.getVoltage();
    	else if (enc != null)
    		return enc.get();
    	else if (sw0 != null)
    	{
    		// switch doesn't have a position, just returns -1 for min reached or +1 for max, or 0 the rest of the time
    		// requires max = min = 0
    		// test switches were normally closed, so trigger on open
    		if (!sw0.get())
    			return -1;
    		else if (!sw1.get())
    			return 1;
    		else
    			return 0;
    	}
    	else
    		return 0;
    }
    
    public int getDirectionTo(double to) {
    	return getPosition() < to?1:-1;
    }
	
    
    public double get() {
    	return controller.get();
    }  
    
	public void show() {
		if (isGood) {
			SmartDashboard.putNumber(name + " Power",  get());
			SmartDashboard.putNumber(name + " Position", getPosition());
			SmartDashboard.putNumber(name + " Neg Limit", negativeLimit);
			SmartDashboard.putNumber(name + " Pos Limit", positiveLimit);
			if (pot != null) {
				SmartDashboard.putNumber(name + " Ave PotVoltage", pot.getAverageVoltage());
			}
		}
	}
}

