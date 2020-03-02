package com.bloxios.SourceDS;

import java.util.logging.Logger;

public class StartSRCDS {
	Logger logger;
	String dir;
	Runtime rt = Runtime.getRuntime();
	public StartSRCDS(Logger log,String dir) {
		this.logger=log;
		logger.info("External program activation is ready.");
		this.dir=dir;
	}
	//Properly starts Garry's mod with Workshop and custom gamemodes
	//String: Server name, String: Max players (an int), String: Map name, String: Extra arguments, String: Gamemode folder name, String: Workshop ID (at the end of the Steam Workshop collection page address)
	public void openGmod(String nm,String mp,String map,String args,String gm,String ws) {
        try {
        	//runs the Garry's mod dedicated serve executable with the proper flags.
            @SuppressWarnings("unused")
			Process p = rt.exec(dir+"\\srcds.exe -game garrysmod -console -nocrashdialog -maxplayers "+mp+" +host_workshop_collection "+ws+" +gamemode "+gm+" +map "+map+" +hostname \""+nm+"\" "+args);
            logger.info("Started Gmod with gm "+gm+" map "+map+"maxplayers "+mp+" name of "+nm+" and optional arguments "+args);
            System.out.println(dir+"\\srcds.exe -game garrysmod -console -nocrashdialog -maxplayers "+mp+" +host_workshop_collection "+ws+" +gamemode "+gm+" +map "+map+" +hostname \""+nm+"\"");
        }catch(Exception ex) {
            ex.printStackTrace();
            logger.info("SRCDS Error: "+ex.getMessage());
            System.out.println("SRCDS Error occured. Logging and exiting. ERR: "+ex.getMessage());
            logger.info("Exiting with error nr -3, External program launch failure");
            System.exit(-3);
        }
    }
	//Starts any server with basic information.
	public void openBasic(String gt,String nm,String mp,String map,String args) {
        try {
            @SuppressWarnings("unused")
			Process p = rt.exec(dir+"\\srcds.exe -game "+gt+" -console -nocrashdialog -maxplayers "+mp+" +map "+map+" +hostname \""+nm+"\" "+args);
            logger.info("Started SRCDS with game folder "+gt+", map "+map+", maxplayers "+mp+", with the server name of "+nm+", and extra arguments "+args);
        }catch(Exception ex) {
            ex.printStackTrace();
            logger.info("SRCDS Error: "+ex.getMessage());
            System.out.println("SRCDS Error occured. Logging and exiting. ERR: "+ex.getMessage());
            logger.info("Exiting with error nr -3, External program launch failure");
            System.exit(-3);
        }
    }
	
}
