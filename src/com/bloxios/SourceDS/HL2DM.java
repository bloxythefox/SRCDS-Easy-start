package com.bloxios.SourceDS;

public class HL2DM {
	private int maxplayers;
	private boolean lan;
	private String name,map,game;
	
	public HL2DM(String n, String m, int max, Boolean lan) {
		this.name = n;
		this.map = m;
		this.lan = lan;
		this.maxplayers = max;
	}
	
	public int getMax() {
		return this.maxplayers;
	}
	
	public boolean isLan() {
		return this.lan;
	}
	
	public String getName() {
		return this.name;
	}
}
