package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;
import org.usfirst.frc.team5003.robot.commands.RunGrabberMotorCommand;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GrabberMotorSubsystem extends Subsystem 
{
	private PWMSpeedController controller;
	
	int axis = -1;
	int otherAxis = -1;
	
	public boolean isGood;

	public GrabberMotorSubsystem(PWMSpeedController controller)
	{
		this(controller, -1, -1);
	}

	public GrabberMotorSubsystem(PWMSpeedController controller, int axis)
	{
		this(controller, axis, -1);
	}

	public GrabberMotorSubsystem(PWMSpeedController controller, int axis, int otherAxis) 
	{
		try 
		{
			this.controller = controller;
			this.controller.set(0);
			this.axis = axis;
			if (this.axis >= 0)
				Robot.joy.getRawAxis(this.axis);
			this.otherAxis = otherAxis;
			if (this.otherAxis >= 0)
				Robot.joy.getRawAxis(this.otherAxis);
			isGood = true;
		}
		catch (Exception ex)
		{
			isGood = false;
		}
	}
	
	public void setFromJoy()
	{
		double power = 0;
		if (axis >= 0)
			power = Robot.joy.getRawAxis(axis);
		if (otherAxis >= 0)
			power -= Robot.joy.getRawAxis(otherAxis);
		set(power);
	}
	public void set(double power)
	{
		controller.set(power);
	}
	
	public int getChannel()
	{
		return controller.getChannel();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new RunGrabberMotorCommand());
    }
    
    public void show()
    {
    	if (isGood)
    	{
    		SmartDashboard.putNumber(String.format("Grabber on %d", controller.getChannel()), controller.get());
    	}
    }
}

