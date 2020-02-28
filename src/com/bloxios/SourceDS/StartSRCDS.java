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
	public void openGmod(String gm,String nm,String mp,String map,String ws,String args) {
        Runtime rt = Runtime.getRuntime();
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
	public void openGmod(String gm,String nm,String mp,String map,String ws) {
        try {
        	//runs the Garry's mod dedicated serve executable with the proper flags.
            @SuppressWarnings("unused")
			Process p = rt.exec(dir+"\\srcds.exe -game garrysmod -console -nocrashdialog -maxplayers "+mp+" +host_workshop_collection "+ws+" +gamemode "+gm+" +map "+map+" +hostname \""+nm+"\"");
            logger.info("Started Gmod with Game Mode "+gm+", Workshop ID "+ws+", Map name "+map+", Max player limit of "+mp+", and name of "+nm);
            System.out.println(dir+"\\srcds.exe -game garrysmod -console -nocrashdialog -maxplayers "+mp+" +host_workshop_collection "+ws+" +gamemode "+gm+" +map "+map+" +hostname \""+nm+"\"");
        }catch(Exception ex) {
            ex.printStackTrace();
            logger.info("SRCDS Error: "+ex.getMessage());
            System.out.println("SRCDS Error occured. Logging and exiting. ERR: "+ex.getMessage());
            logger.info("Exiting with error nr -3, External program launch failure");
            System.exit(-3);
        }
    }
	//Starts a basic source engine server. Requires a: game folder name, server name, max players, starting map, directory, and optional arguments at the end in string form.
	public void openBasic(String gt,String nm,String mp,String map) {
        try {
            @SuppressWarnings("unused")
			Process p = rt.exec(dir+"\\srcds.exe -game "+gt+" -console -nocrashdialog -maxplayers "+mp+" +map "+map+" +hostname \""+nm);
            logger.info("Started SRCDS with game folder "+gt+", map "+map+", maxplayers "+mp+", with the server name of "+nm);
        }catch(Exception ex) {
            ex.printStackTrace();
            logger.info("SRCDS Error: "+ex.getMessage());
            System.out.println("SRCDS Error occured. Logging and exiting. ERR: "+ex.getMessage());
            logger.info("Exiting with error nr -3, External program launch failure");
            System.exit(-3);
        }
    }
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
