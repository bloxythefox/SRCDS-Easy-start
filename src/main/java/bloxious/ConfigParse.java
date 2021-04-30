package bloxious;

import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class ConfigParse {

	JSONParser parser = new JSONParser();
	Reader reader;
	
	
	Log log;
    public ConfigParse(Log log) {
		this.log = log;
		try {
			reader = new FileReader("config.json");
			}
			catch (Exception e) {
				log.exceptionLog("Could not read from the config file. Exception:", e);
				log.severeLog("Make sure you have a config.json file in the same directory as the jar. Download the Github example for a basic example.");
				System.exit(-1);
			}
	}
	public String findExecutable(String game) {
		String gamedir = null;
		try {
			Object jsonObj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) jsonObj;
			gamedir = (String) jsonObject.get("game_"+game);
			
		}
		catch (Exception e) {
			log.exceptionLog("Failed to find a valid game directory in the config file. Exception:", e);
		}
		if (gamedir == null) {
			log.severeLog("Failed to find a valid game directory in the config file. Make sure you have specified a directory for game_"+game);
			System.exit(-1);
		}
		return gamedir;
	}
}

