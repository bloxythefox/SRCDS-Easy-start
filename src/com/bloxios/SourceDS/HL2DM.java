package com.bloxios.SourceDS;

import java.util.logging.Logger;

public class HL2DM {
	private Logger logger;
	private String maxplayers;
	private String name,map,game,optargs,dir;
	protected StartSRCDS Start;
	
	public HL2DM(Logger log, String dir, String n, String m, String max, String args) {
		this.name = n;
		this.map = m;
		this.maxplayers = max;
		this.optargs = args;
		this.logger = log;
		this.Start = new StartSRCDS(logger,dir);
	}
	public HL2DM(Logger log, String dir, String n, String m, String max) {
		this.logger=log;
		this.dir=dir;
		this.name = n;
		this.map = m;
		this.maxplayers = max;
		this.Start = new StartSRCDS(logger,dir);
	}
	//Getter for Max player count
	public String getMax() {
		return this.getMaxplayers();
	}
	//Getter for server name
	public String getName() {
		return this.name;
	}
	//Starts a HALF-LIFE: 2 DEATH MATCH server with supplied structured arguments and no extra arguments
	public void startServer() {
		Start.openBasic("hl2mp",name,getMaxplayers(),getMap());
	}
	//Starts a HALF-LIFE: 2 DEATH MATCH server with supplied structured arguments and extra arguments
	public void startServerWArgs() {
		Start.openBasic("hl2mp",name,getMaxplayers(),getMap(),getOptargs());
	}
	//Auto-generated stuff, too lazy to replace on my own.
	
	public String getMaxplayers() {
		return maxplayers;
	}
	public String getMap() {
		return map;
	}
	public String getOptargs() {
		return optargs;
	}
}
