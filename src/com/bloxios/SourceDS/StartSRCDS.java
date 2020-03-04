package com.bloxios.SourceDS;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StartSRCDS {
	Logger logger;
	String dir;
	Runtime rt = Runtime.getRuntime();
	public StartSRCDS(Logger log,String dir) {
		this.logger=log;
		this.dir=dir;
	}
	
	//Properly starts Garry's mod with Workshop and custom gamemodes
	//String: Server name, String: Max players (an int), String: Map name, String: Extra arguments, String: Gamemode folder name, String: Workshop ID (at the end of the Steam Workshop collection page address)
	public void openGmod(String nm,String mp,String map,String args,String gm,String ws) {
		String start = dir+"\\srcds.exe -game garrysmod -console -nocrashdialog -maxplayers "+mp+" +host_workshop_collection "+ws+" +gamemode "+gm+" +map "+map+" +hostname \""+nm+"\" "+args;
		logger.info("All arguments complete for Garry's Mod. Server name:"+nm+", Max Players: "+mp+", Workshop ID: "+ws+", Map: "+map+", Gamemode: "+gm+", and extra arguments: "+args);
		runServer(start);
	}
	//Starts any server with basic information.
	//String: Game Folder, String: Server Name, String: Max players (an integer, as a string), String: Map name, String: Extra arguments (can be blank)
	public void openBasic(String gt,String nm,String mp,String map,String args) {
        String start = dir+"\\srcds.exe -game "+gt+" -console -nocrashdialog -maxplayers "+mp+" +map "+map+" +hostname \""+nm+"\" "+args;
        logger.info("All arguments complete. Game folder: "+gt+", Server name:"+nm+", Max Players: "+mp+", Map: "+map+", and extra arguments: "+args);
        runServer(start);
    }
	
	//Uses the Runtime to create a new process. The String input should be treated exactly as if you were running the program from the Windows run prompt or a command prompt.
	private void runServer(String str) {
		logger.info("Runtime will attempt to start: "+str);
		try {
            @SuppressWarnings("unused")
			Process p = rt.exec(str);    
        }catch(Exception ex) {
            ex.printStackTrace();
            logger.severe("SRCDS Error: "+ex.getMessage());
            System.out.println("SRCDS Error occured. Logging and exiting. ERR: "+ex.getMessage());
            logger.severe("Exiting with error nr -3. Is this in the same directory as the server executable, or did the server crash?");
            logger.log(Level.SEVERE,"Exception: ",ex);
            System.exit(-3);
        }
	}
	
}
