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
		return gamedir;
	}
}

