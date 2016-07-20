
package task;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;


public class Controller extends UnicastRemoteObject implements ActionListener  {

    public MainFrame view;
    
    public Controller () throws RemoteException {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout)(this.view.cards.getLayout());
		String con = e.getActionCommand();
		if (con.equals("FANTASY")) {
			cl.show(this.view.cards, "FANTASY");
		}
		else if (con.equals("SCIFI")) {
			cl.show(this.view.cards, "SCIFI");
		}
                else if (con.equals("HORROR")) {
			cl.show(this.view.cards, "HORROR");
		}
                else if (con.equals("THRILLER")) {
			cl.show(this.view.cards, "THRILLER");
		}
		else {
			JOptionPane.showMessageDialog(this.view, "Kommando unbekannt");
		}
		this.view.repaint();
		
	}
    
}
