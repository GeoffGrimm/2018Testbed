package org.usfirst.frc.team5003.robot.subsystems;

import org.usfirst.frc.team5003.robot.commands.TalkativeCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

public class EmptySubsystem extends Subsystem {

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TalkativeCommand("default for empty subsystem", false));
    }
}

