package com.bloxios.SourceDS;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String workingdir = System.getProperty("user.dir");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM_dd_yyyy HH_mm");  
		LocalDateTime now = LocalDateTime.now();  
		String time=(dtf.format(now));
		//workingdir.replaceAll("\\", "/");
		//Source: https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger
		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;
		
		try {
			fh = new FileHandler(time+"servermgr.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			
			//Basic message, also startup.
			logger.info("Logger started");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
