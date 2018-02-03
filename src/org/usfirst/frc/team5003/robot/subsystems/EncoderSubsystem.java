package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderSubsystem extends Subsystem {
	
	Encoder encoder;
	boolean isGood;
	
	public EncoderSubsystem(int chA, int chB, int chX) {
		Robot.log("encoder ctor");
		try {
			encoder  = new Encoder(chA, chB, chX);
			encoder.reset();
			isGood = true;
		}
		catch (Exception ex) {
			isGood = false;
			Robot.log(String.format("Encoder on %d failed", chA));
		}

	}
	
	public void show() {
		SmartDashboard.putNumber("Encoder Count", encoder.get());
		SmartDashboard.putNumber("Encoder Raw",  encoder.getRaw());
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

