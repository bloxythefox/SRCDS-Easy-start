package bloxious;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.*;

public class Gui {
    Log log;
    JList li;
    public Gui(Log log) {
        this.log = log;
    }
    public void startUI(String[] instances) {
        li = new JList<>(instances);
        createJGUI();
        //TODO: Make the GUI resturn/call back with a String array formatted like so: game, arguments
    }
    //Source: https://stackoverflow.com/questions/6270354/how-to-open-warning-information-error-dialog-in-swing
	public static void simpleErrorDialog(String text){
        showMessageDialog(null, text, "Error", ERROR_MESSAGE);
    }
    //Experimental amalgamation. Current sources: https://www.guru99.com/java-swing-gui.html 
    private void createJGUI() {
        //Creating the Frame
        JFrame frame = new JFrame("Server UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        m1.add(m11);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Select an Instance");
        JButton select = new JButton("Confirm");
        panel.add(label); // Components Added using Flow Layout
        panel.add(select);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, li);
        frame.setVisible(true);
    }
    //https://stackoverflow.com/questions/28852864/get-the-selected-values-from-jlist-in-java
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("Confirm"))
        {
            int index = li.getSelectedIndex();
            System.out.println("Index Selected: " + index);
            String s = (String) li.getSelectedValue();
            System.out.println("Value Selected: " + s);
        }
    }
    
}
