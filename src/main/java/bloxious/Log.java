package bloxious;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class Log {
    String workingdir = System.getProperty("user.dir");
    File file;
    Logger log;
    public Log() {
        file = new File(workingdir+"/logs");
        if (file.isDirectory()) {
            //Continue as normal
        }
        else {
            if (file.mkdir()) {
                System.out.println("Directory not found, but created succesfully");
            }
            else {
                System.out.println("Failed to find or create directory. Fatal error.");
                bloxious.Gui.simpleErrorDialog("Unable to create or find the \"logs\" directory. Make sure you can read and write files here.");
                System.exit(-1);
            }
        }
        //Initialize the logger and create/attach a file
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM_dd_yyyy HH_mm");  
		LocalDateTime now = LocalDateTime.now();  
		String time=(dtf.format(now));
		//Source: https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger
		log = Logger.getLogger("MyLog");
        FileHandler fh;
		try {
			log.setLevel(Level.ALL);
			fh = new FileHandler(workingdir+"/logs/"+time+".log");
			fh.setLevel(Level.ALL);
			StreamHandler con = new StreamHandler(System.out, new SimpleFormatter());
			con.setLevel(Level.ALL);
			log.addHandler(fh);
			log.addHandler(con);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			
			log.info("Logger started");
		} catch (SecurityException e) {
			bloxious.Gui.simpleErrorDialog("Can't create the file. Do you have permissions to write here? Exception: "+e.getMessage());
			System.exit(-6);
		} catch (IOException e) {
			bloxious.Gui.simpleErrorDialog("Can't create/edit the file. Does the directory exist? Exception: "+e.getMessage());
			
			System.exit(-6);
		}
    }

    public void fineLog(String text) {
        log.fine(text);
    }
    public void infoLog(String text) {
        log.info(text);
    }
    public void warningLog(String text) {
        log.warning(text);
    }
    public void severeLog(String text) {
        log.severe(text);
    }
    public void exceptionLog(String msg, Exception e) {
        log.log(Level.SEVERE, msg, e);
    }
}
