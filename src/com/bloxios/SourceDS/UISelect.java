package com.bloxios.SourceDS;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class UISelect extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String workingdir,selection="";
	private Logger logger;
	private String[] linesArr;
	public UISelect(Logger log, String work) {
		this.logger = log;
		this.workingdir = work;
		logger.info("UI initialized");
	}
	public void UI() {
		
	}
	public void makeSelection(String selection) {
		this.selection=selection;
	}
	public String getSelection() {
		return selection;
	}
	public String getFromArray(int i) {
		return linesArr[i];
	}
	public JComboBox<String> makeComboBox() {
	//TODO: Add a UI for selecting server types from a file!
	JComboBox<String> serverTypes;
	/**if (readFile("servertypes.txt")!= null) {
		String[] servers = readFile("servertypes.txt").toArray();
	}
	else {
		logger.info("Null was returned while trying to read servertypes.txt, does the file exist? Now exiting.");
		System.out.println("Null was returned. Exiting.");
		System.exit(-1);
	}**/
	//Source: http://www.java2s.com/Tutorials/Java/java.nio.file/Files/Java_Files_readAllLines_Path_path_Charset_cs_.htm
	Path txtfile = Paths.get(workingdir, "servertypes.txt");
	//Source https://stackoverflow.com/questions/12857242/java-create-string-array-from-text-file
    List<String> lines;
	try {
		lines = Files.readAllLines(txtfile, Charset.forName("UTF-8"));
	} catch (IOException e) {
		e.printStackTrace();
		lines=null;
		logger.log(Level.SEVERE, "Error: Null was returned when attempting to read from the file. Does the file exist, and do we have permissions to use it? Exception: ", e);
		System.out.println("Exiting with Error code -2, external file failure ERR: "+e.getMessage());
		System.exit(-2);
	}
	if (lines!=null) {
		JComboBox<String> combo = new JComboBox<String>();
		combo.setEditable(false);
		 linesArr = (String[]) lines.toString().split(", ");
			for(int i=0;i<linesArr.length;i++) {
				if (linesArr[i].startsWith("//")) {
					continue;
				}
				int ind1 = linesArr[i].indexOf("typename(");
				int ind2 = linesArr[i].indexOf(")");
				String newi = linesArr[i].substring((ind1+9), (ind2));
				String item = (i+1)+": "+newi;
				combo.addItem(item);
			}
			serverTypes = combo;
			}
	else {
		System.exit(-2);
		serverTypes = null;
	}
	return serverTypes;
}
	//Old code, consolidating
	/**private String initialize(JComboBox<String> comboBox) {
        setSize(300, 300);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create a combo box with four items and set it to editable so that user can
        // enter their own value.
        //final JComboBox<String> comboBox = new JComboBox<>(new String[] {"One", "Two", "Three", "Four"});
        comboBox.setEditable(false);
        getContentPane().add(comboBox);
        JButton button1 = new JButton("Start this");
        getContentPane().add(button1);
        final JTextField textField = new JTextField("");
        String select = new String();
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	textField.setText((String) comboBox.getSelectedItem());
            }
        });
        select=textField.getText();
        return select;
 }**/
}
