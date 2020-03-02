package com.bloxios.SourceDS;

import java.util.logging.Logger;

public class Gmod extends HL2DM {
	private String gm,ws;
	public Gmod(Logger log, String dir, String n, String m, String max, String args, String WID, String gamemode) {
		super(log, dir, n, m, max, args);
		gm=gamemode;
		ws=WID;
	}
	public Gmod(Logger log, String dir, String n, String m, String max, String WID, String gamemode) {
		super(log, dir, n, m, max);
		gm=gamemode;
		ws=WID;
	}
	@Override
	//Starts a GARRY'S MOD server with the specified arguments.
	public void startServer() {
		Start.openGmod(super.getName(),super.getMaxplayers(),super.getMap(),super.getOptargs(),gm,ws);
	}
}
