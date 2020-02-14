package com.bloxios.SourceDS;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main extends JFrame {

	static boolean silent;
	static String gametype;
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
		for (int i=0;i<args.length;i++) {
			if (args[i].equals("silent")) {
				silent=true;
				logger.info("Server will start automatically with supplied arguments.");
			}
			else if (args[i].contains("gt:")) {
				gametype= args[i].substring((args[i].indexOf(":")+1));
				logger.info("I now have the game folder name. I'll check for additional arguments once I discover which game this folder should contain.");
				//logger.info("Gamemode argument detected. Server will start in \""+gametype+"\".");
			}
		}
		if (!silent) {
			JFrame j = new JFrame("Select Server type");
			UISelect ui = new UISelect(logger, workingdir);
			JComboBox box = ui.makeComboBox();
			JButton button1 = new JButton("Start this");
			final JTextField textField = new JTextField("");
	        String select = new String();
	        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        j.setLayout(new FlowLayout(FlowLayout.LEFT));
	        j.setSize(800, 600);
	        j.setVisible(true);
	        j.setLocationRelativeTo(null);
	        j.getContentPane().add(box);
	        j.getContentPane().add(button1);
	        button1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	textField.setText((String) box.getSelectedItem());
	            }
	        });
	        select=textField.getText();
		}
		else {
			
		}
	}
	

}
