package org.usfirst.frc.team5003.robot.commands;
import java.util.ArrayList;
import org.usfirst.frc.team5003.robot.commands.RotateWithGyroCommand;

import edu.wpi.first.wpilibj.command.Command;

public class CommandParser {
	
	public ArrayList<Command> commands = new ArrayList<Command>();
	public String debug = "";

	public Boolean init(String s){
		s = s.toLowerCase();
		String[] fields = s.split(",");
		double power = 0.5;
		for (int i = 0; i < fields.length; i++)
		{
			String field = fields[i].trim();
			String commandText = field.substring(0,1);
			String valueText = field.substring(1, field.length());
					
			double value = 0;
			try{
				value = Double.parseDouble(valueText);
			}
			catch (Exception ex){
				debug += String.format("%s from %s is not a double\r\n",  valueText, field);
			}
			
			//(P)ower
			if (commandText.equals("p")){
				if (Math.abs(value) > 1)
					debug += String.format("%s from %s is not between -1.0 and 1.0\r\n",  valueText, field);
				else
					power = value;
			}
			
			//(D)rive
			else if (commandText.equals("d")){
				if (value < 0 || value > 10)
					debug += String.format("%s from %s is less than 0 or greater than 10\r\n",  valueText, field);
				else{
					DriveWithDurationAndPowerCommand c = null;
					try{
						c = new DriveWithDurationAndPowerCommand(value, power);
					}
					catch (Exception e) {
						c = null;
					}
					if (c != null)
						commands.add(c);
					else
						debug += String.format("DriveWithDurationAndPower is not good\r\n");
				}
			}
			
			
			//(R)otate
			else if (commandText.equals("r")){
				if (Math.abs(value) > 180)
					debug += String.format("%s from %s is not between -180 and 180\r\n",  valueText, field);
				else{
					RotateWithGyroCommand c = null;
					try {
						c = new RotateWithGyroCommand(value);
					}
					catch (Exception ex) {
						c = null;
					}
					if (c != null)
						commands.add(c);
					else
						debug += String.format("RotateWithGyroCommand is not good\r\n");
				}
			}

			else{
				debug += String.format("%s from %s is not (P)ower, (D)rive, (R)otate\r\n",  commandText, field);
			}
			
		}
		if (debug.length() > 0){
			commands.clear();
			return false;
		}
		return true;

	}
}
