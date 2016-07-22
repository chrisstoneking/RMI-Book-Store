package task;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

// This is the controller. It catches commands and reacts to them.
public class Controller extends UnicastRemoteObject implements ActionListener {

    public MainFrame view;

    public Controller() throws RemoteException {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // A CardLayout is used to display different categories
        CardLayout cl = (CardLayout) (this.view.cards.getLayout());
        String con = e.getActionCommand();
        // The first 4 commands are for switching to different categories
        if (con.equals("FANTASY")) {
            cl.show(this.view.cards, "FANTASY");
        } else if (con.equals("SCIFI")) {
            cl.show(this.view.cards, "SCIFI");
        } else if (con.equals("HORROR")) {
            cl.show(this.view.cards, "HORROR");
        } else if (con.equals("THRILLER")) {
            cl.show(this.view.cards, "THRILLER");
        // The BUY command will also contain the name and price of a book, which need to be passed on
        } else if (con.contains("BUY;")) {
            String[] stuff = con.split(";");
            String title = stuff[1];
            String price = stuff[2];
            this.view.addProduct(title, price);
        // The SHOWALL and BUYALL commands just activate the associated methods
        } else if (con.contains("SHOWALL")) {
            this.view.showProducts();
        } else if (con.contains("BUYALL")) {
            this.view.buy();
        } else {
            JOptionPane.showMessageDialog(this.view, "Unknown Command", "Error", ERROR_MESSAGE);
        }
        // Of course, this must be done as well.
        this.view.repaint();

    }

}
