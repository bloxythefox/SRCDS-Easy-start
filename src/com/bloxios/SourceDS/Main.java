package com.bloxios.SourceDS;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

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
import java.util.logging.StreamHandler;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static boolean silent;
	static String gametype;
	private static HL2DM dm=null;
	private static Gmod gmod=null;
	//Source: https://stackoverflow.com/questions/6270354/how-to-open-warning-information-error-dialog-in-swing
		private static void simpleDialog(String text){
		        showMessageDialog(null, text, "Error", ERROR_MESSAGE);
		}
	public static void main(String[] args) {
		//Initialize the logger and create a file
		String workingdir = System.getProperty("user.dir");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM_dd_yyyy HH_mm");  
		LocalDateTime now = LocalDateTime.now();  
		String time=(dtf.format(now));
		//Source: https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger
		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;
		try {
			logger.setLevel(Level.ALL);
			fh = new FileHandler(time+"servermgr.log");
			fh.setLevel(Level.ALL);
			StreamHandler con = new StreamHandler(System.out, new SimpleFormatter());
			con.setLevel(Level.ALL);
			logger.addHandler(fh);
			logger.addHandler(con);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			
			logger.info("Logger started");
		} catch (SecurityException e) {
			simpleDialog("Can't create the file. Do you have permissions to write here? Exception: "+e.getMessage());
			System.exit(-6);
		} catch (IOException e) {
			simpleDialog("Can't create/edit the file. Do you have permissions to read/write here? Exception: "+e.getMessage());
			
			System.exit(-6);
		}
		for (int i=0;i<args.length;i++) {
			if (args[i].equals("silent;")) {
				silent=true;
				logger.fine("Server will start automatically with supplied arguments.");
			}
			
		}
		if (!silent) {
			//Initializes a J-Frame and calls the ComboBox creator, adding it and other UI elements
			//Only called if the silent flag is not present.
			logger.info("Silent argument not found, starting UI.");
			JFrame j = new JFrame("Select Server type");
			UISelect ui = new UISelect(logger, workingdir);
			JComboBox<String> box = ui.makeComboBox();
			JButton button1 = new JButton("Start this");
			JTextArea logArea = new JTextArea();
		    JScrollPane logPane = new JScrollPane(logArea);
			final JTextField textField = new JTextField("");
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
	            	ui.makeSelection(textField.getText());
	            	logger.info("Selected item: "+ui.getSelection());
	            	j.getContentPane().add(logPane);
	            	j.remove(box);
	            	j.remove(button1);
	            	button1.removeActionListener(box);
	            	final int select = (getSelNum(ui)-1);
	            	final String[] argarray = ui.getFromArray(select).split(";");
	            	final String[] fixedargs = (fixArr(argarray));
	            	setServ(fixedargs,logger,workingdir);
	            }
	        });
		}
		
		
		else if (silent) {
			//Automatically initalizes the server variables from comand-line arguments. Requires no extra user input.
			//Only called if the silent flag was present
			setServ(args,logger,workingdir);
		}
	}
	
	private static int getSelNum(UISelect ui) {
		String integ = (ui.getSelection().substring(0, ui.getSelection().indexOf(":")));
        int in;
        in = Integer.parseInt(integ);
        return in;
	}
	private static String[] fixArr(String[] arr) {
		String[] fix = new String[arr.length];
		for (int i=0;i<arr.length;i++) {
			fix[i]=(arr[i]+";");
		}
		return fix;
	}
	private static void setServ(String[] arguArr,Logger logger,String workingdir) {
		String gm=null,gt=null,ws=null,name=null,mp=null,map=null,oargs=null;
		for (int i=0;i<arguArr.length;i++) {
			if (arguArr[i].contains("gt:")) {
				gametype= arguArr[i].substring((arguArr[i].indexOf(":")+1),arguArr[i].indexOf(";"));
				gt= arguArr[i].substring((arguArr[i].indexOf(":")+1),arguArr[i].indexOf(";"));
				logger.info("Game folder name: "+gt);
			}
			else if (arguArr[i].contains("gm:")) {
				gm= arguArr[i].substring((arguArr[i].indexOf(":")+1),arguArr[i].indexOf(";"));
				logger.info("Garry's mod gamemode set to \""+gm+"\".");
			}
			else if (arguArr[i].contains("ws:")) {
				ws= arguArr[i].substring((arguArr[i].indexOf(":")+1),arguArr[i].indexOf(";"));
				logger.info("Garry's mod Workshop ID set to \""+ws+"\".");
			}
			else if (arguArr[i].contains("nm:")) {
				int ind1 = (arguArr[i].indexOf(":")+1);
				int ind2 = (arguArr[i].indexOf(";"));
				System.out.println(""+ind1+" "+ind2);
				name= arguArr[i].substring(ind1,ind2);
				logger.info("Server name will be "+name+".");
			}
			else if (arguArr[i].contains("mp:")) {
				mp= arguArr[i].substring((arguArr[i].indexOf(":")+1),arguArr[i].indexOf(";"));
				logger.info("Max players limit set to "+mp+".");
				}
			else if (arguArr[i].contains("map:")) {
				map= arguArr[i].substring((arguArr[i].indexOf(":")+1),arguArr[i].indexOf(";"));
				logger.info("Server will start on the map \""+map+"\".");
				}
			else if (arguArr[i].contains("oarguArr:")) {
				oargs= arguArr[i].substring((arguArr[i].indexOf(":")+1),arguArr[i].indexOf(";"));
				logger.info("The following argument(s) will be appended to the end of the run command: "+oargs);
			}
			else {
				logger.warning("Can't recognize the argument \""+arguArr[i]+"\". If you don't know why this is here, ignore it.");
			}
		}
		String game = null;
		if (gt.equals("hl2mp")) {
			game = "HL2DM";
		}
		else if (gt.equals("garrysmod")) {
			game = "GMod";
		}
		else {
			logger.severe("No valid game folder was specified, exiting with error code -4");
			System.exit(-4);
		}
		if (game.equals("HL2DM")) {
			if (name!=null&&mp!=null&&map!=null) {
				if (oargs!=null) {
					
					dm = new HL2DM(logger,workingdir,name,map,mp,oargs);
				}
				else {
					dm = new HL2DM(logger,workingdir,name,map,mp);
				}
			}
		}
		else if (game.equals("GMod")) {
			if (gm!=null&&ws!=null&&name!=null&&mp!=null&&map!=null) {
				if (oargs!=null) {
					
					gmod = new Gmod(logger,workingdir,name,map,mp,oargs,ws,gm);
				}
				else {
					gmod = new Gmod(logger,workingdir,name,map,mp,ws,gm);
				}
			}
		}
		if (dm!=null) {
			dm.startServer();
		}
		else if (gmod!=null) {
			gmod.startServer();
		}
		else {
			System.out.println("Unknown error: Somehow couldn't find any server to start");
			logger.severe("Unknown error: Somehow couldn't find any server to start. Check all arguments and try again.");
			System.exit(-999);
		}
	}

}
