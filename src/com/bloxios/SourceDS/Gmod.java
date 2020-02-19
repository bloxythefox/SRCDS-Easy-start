package com.bloxios.SourceDS;

import java.util.logging.Logger;

public class Gmod extends HL2DM {
	private String workshop,gamem;
	public Gmod(Logger log, String dir, String n, String m, String max, String args, String WID, String gamemode) {
		super(log, n, m, max, args);
		this.workshop = WID;
		this.gamem = gamemode;
	}
	public Gmod(Logger log, String dir, String n, String m, String max, String WID, String gamemode) {
		super(log, dir, n, m, max);
		this.workshop = WID;
		this.gamem = gamemode;
	}
	@Override
	//Starts a GARRY'S MOD server with the previously specified arguments, with no optional arguments.
	public void startServer() {
		Start.openGmod(gamem,getName(),getMaxplayers(),getMap(),workshop);
	}
	@Override
	//Starts a GARRY'S MOD server with the previously specified arguments, including optional arguments.
	public void startServerWArgs() {
		Start.openGmod(gamem,getName(),getMaxplayers(),getMap(),workshop,getOptargs());
	}
}
