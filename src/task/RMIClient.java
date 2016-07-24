package task;

import java.rmi.Naming;

import javax.swing.UIManager;

// The RMI client. Can access server methods.
public class RMIClient { 
    public static void main(String args[]) throws Exception {
        // Connect to the server
        RMIServerIntf obj = (RMIServerIntf)Naming.lookup("//localhost/RmiServer");
        // Get the GUI, the main object of our little shop
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        MainFrame m = obj.makeFrame();
        // This was just for testing
        // System.out.println("Got Frame");
        // Start the GUI
        m.initialise();
        
    }
}
