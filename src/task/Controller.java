package task;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.border.EmptyBorder;

// This is the controller. It catches commands and reacts to them.
public class Controller extends UnicastRemoteObject implements ActionListener, MouseListener {

    public MainFrame view;
    
    public Controller() throws RemoteException {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // A CardLayout is used to display different categories
        //CardLayout cl = (CardLayout) (this.view.cards.getLayout());
        String con = e.getActionCommand();
        // The first 4 commands are for switching to different categories
        if (con.equals("FANTASY")) {
        	view.updateBookList("Fantasy");
            //cl.show(this.view.cards, "FANTASY");
        } else if (con.equals("SCIFI")) {
        	view.updateBookList("Science Fiction");
            //cl.show(this.view.cards, "SCIFI");
        } else if (con.equals("HORROR")) {
        	view.updateBookList("Horror");
            //cl.show(this.view.cards, "HORROR");
        } else if (con.equals("THRILLER")) {
        	view.updateBookList("Thriller");
            //cl.show(this.view.cards, "THRILLER");
            
        // The BUY command will also contain the name and price of a book, which need to be passed on
        }  else if (con.contains("SHOWALL")) {
            this.view.showProducts();
        } else if (con.contains("BUYALL")) {
            this.view.buy();
        } else {
            JOptionPane.showMessageDialog(this.view, "Unknown Command", "Error", ERROR_MESSAGE);
        }
        // Of course, this must be done as well.
        //this.view.repaint();

    }

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			JPopupMenu pop = new JPopupMenu();
			pop.setBackground(Constants.backgroundColor_02);
			JMenuItem item = new JMenuItem("Add");
			item.setBackground(Constants.backgroundColor_02);
			item.setBorder(new EmptyBorder(10, 10, 10, 20));
			item.setFont(new Font(Constants.fontFamily, Font.PLAIN, 15));
			item.setForeground(Color.WHITE);
			item.addActionListener(new ActionListener() 
			{
                public void actionPerformed(ActionEvent e) 
                {
                	if (view.bookList.isSelectionEmpty()) return;
                    Book b = view.bookList.getSelectedValue();
                    view.addProduct(b);
                }
            });
			pop.add(item);
			pop.show(view.bookList, p.x, p.y);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
