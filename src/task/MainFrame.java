
package task;

import java.awt.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import database.DatabaseConnection;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.JOptionPane.*;


// This is the GUI. It is the only thing actually passed from client to server.
public class MainFrame extends JFrame {
	JList<Book> bookList;
	JScrollPane scroll;
    public Controller control = null;
    JPanel cards;
    Book book1;
    Book book2;
    Book book3;
    Book book4;
    ArrayList products = new ArrayList();
    double totalprice = 0;
   
    /**
     * Build the GUI together and display it.
     * @throws java.rmi.RemoteException
     */
    public void initialise() throws RemoteException {
    	    	
        // Do all the setup stuff
    	ImageIcon img = new ImageIcon("./src/img/frame_icon.png");
    	this.setIconImage(img.getImage());
        this.setTitle("RMI Book Store");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.control = new Controller();
        this.control.view = this;
        this.setSize(1024,920);
        JPanel contentPane = new JPanel(new BorderLayout());
        
        // Set up the switch-category buttons
        JRadioButton fantasyButton = Constants.CreateMyRadioButton("Fantasy");
        fantasyButton.addActionListener(this.control);
        fantasyButton.setActionCommand("FANTASY");
        fantasyButton.setSelected(true);
        
        JRadioButton horrorButton = Constants.CreateMyRadioButton("Horror");
        horrorButton.addActionListener(this.control);
        horrorButton.setActionCommand("HORROR");
        
        JRadioButton scifiButton = Constants.CreateMyRadioButton("Science Fiction");
        scifiButton.addActionListener(this.control);
        scifiButton.setActionCommand("SCIFI");
        
        JRadioButton thrillerButton = Constants.CreateMyRadioButton("Thriller");
        thrillerButton.addActionListener(this.control);
        thrillerButton.setActionCommand("THRILLER");

        ButtonGroup group = new ButtonGroup();
        group.add(fantasyButton);
        group.add(horrorButton);
        group.add(scifiButton);
        group.add(thrillerButton);
        
        JLabel categoryLabel = Constants.CreateMyLabel("Categories:", 15, Font.BOLD, JLabel.LEFT_ALIGNMENT);
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setBorder(new EmptyBorder(10, 5, 5, 5));
        // Add the buttons to the panel, in a way that they're arranged vertically
        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setBackground(Constants.backgroundColor_02);
        categoriesPanel.setLayout(new BoxLayout(categoriesPanel, BoxLayout.Y_AXIS));
        categoriesPanel.add(categoryLabel);
        categoriesPanel.add(fantasyButton);
        categoriesPanel.add(horrorButton);
        categoriesPanel.add(scifiButton);
        categoriesPanel.add(thrillerButton);
        contentPane.add(categoriesPanel, BorderLayout.WEST);

//        // Start work on the central part - use CardLayout for switching between categories
//        cards = new JPanel(new CardLayout());
//
//        // For each category: Reset the values stored in the local Book vars, 
//        // Build the category's main panel and add it to the CardLayout
//        makeFantasy();
//        JPanel card1 = new JPanel();
//        makeCategory(card1, "Fantasy");
//        cards.add(card1, "FANTASY");
//        
//        makeHorror();
//        JPanel card2 = new JPanel();
//        makeCategory(card2, "Horror");
//        cards.add(card2, "HORROR");
//        
//        makeSciFi();
//        JPanel card3 = new JPanel();
//        makeCategory(card3, "Science Fiction");
//        cards.add(card3, "SCIFI");
//        
//        makeThriller();
//        JPanel card4 = new JPanel();
//        makeCategory(card4, "Thriller");
//        cards.add(card4, "THRILLER");
        // Add the central part to the GUI
    	bookList = new JList<>();
    	scroll = new JScrollPane(bookList);
    	scroll.getVerticalScrollBar().setUnitIncrement(25);
    	scroll.setBorder(new EmptyBorder(5, 5, 5, 5));
    	DefaultListModel<Book> dlm = new DefaultListModel<>();
    	bookList.setCellRenderer(new BookListCellRenderer(control));
    	bookList.setModel(dlm);
    	this.updateBookList("Fantasy");
        contentPane.add(scroll, BorderLayout.CENTER);

        // Next, add the button for buying everything and the button for showing everything
        JPanel extras = new JPanel();
        extras.setBorder(new EmptyBorder(10, 0, 10, 0));
        extras.setBackground(Constants.accentColor);
        JButton buy = Constants.CreateMyButton("Buy all in cart");
        buy.setActionCommand("BUYALL");
        buy.addActionListener(control);
        JButton show = Constants.CreateMyButton("Show all in cart");
        show.setActionCommand("SHOWALL");
        show.addActionListener(control);
        extras.add(buy);
        extras.add(show);
        contentPane.add(extras, BorderLayout.SOUTH);

        // Other standard GUI stuff
        this.setContentPane(contentPane);
        //this.pack();
        this.setVisible(true);
    }

    /**
     * Make an ImageIcon
     * @author: Someone else, I unfortunately forget who and lost the source. Sorry.
     * @param path: Local path to the image.
     * @return an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {

    	return null;
        //java.net.URL imgURL = MainFrame.class.getResource(path);
        //if (imgURL != null) {
        //    return new ImageIcon(imgURL);
        //} else {
        //    System.err.println("Couldn't find file: " + path);
        //    return null;
        //}
    }
    
    public void updateBookList()
    {
    	
    }
    
    public void updateBookList(String category)
    {
    	DefaultListModel<Book> dlm = (DefaultListModel<Book>) bookList.getModel();
    	dlm.clear();
    	try {
    		DatabaseConnection dbc = new DatabaseConnection();
			ArrayList<Book> books = dbc.getBooksByCategory(category);
			for (Book book2 : books) {
				dlm.addElement(book2);
			}
		} catch (RemoteException | SQLException e) 
    	{
			JOptionPane.showMessageDialog(null, "The database is not reachable. Please try it again in a few minutes.");
		}
    	scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMinimum());
    }


    /**
     * Make a JPanel containing all the info on one book
     * @param b: The book for the JPanel
     * @return a JPanel containing the info
     */
//    public Component makeBookPanel(String category) {
//    	bookList = new JList<>();
//    	JScrollPane scroll = new JScrollPane(bookList);
//    	DefaultListModel<Book> dlm = new DefaultListModel<>();
//    	bookList.setCellRenderer(new BookListCellRenderer(control));
//    	bookList.setModel(dlm);
//    	DatabaseConnection dbc = new DatabaseConnection();
//    	try {
//			ArrayList<Book> books = dbc.getBooksByCategory(category);
//			for (Book book2 : books) {
//				dlm.addElement(book2);
//			}
//		} catch (RemoteException | SQLException e) {
//			e.printStackTrace();
//		}
//    	
////        JPanel book = new JPanel();
////        book.setLayout(new BoxLayout(book, BoxLayout.Y_AXIS));
////        JLabel cover = new JLabel(createImageIcon(b.cover));
////        book.add(cover);
////        JLabel stuff = new JLabel("<html>" + b.title + "<br>" + b.author + "<br>" + b.price + "</html>", JLabel.CENTER);
////        book.add(stuff);
////        JPanel cart = new JPanel();
////        JButton addToCart = new JButton("Add to Cart");
////        String com = "BUY;" + b.title + ";" + b.price + " �";
////        addToCart.setActionCommand(com);
////        addToCart.addActionListener(this.control);
////        cart.add(addToCart, BorderLayout.CENTER);
////        book.add(cart);
//        return scroll;
//    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
        
    /**
     * Set up the main panel (card) for a category
     * @param cat: The card to contain the book panels
     */
    public void makeCategory(JPanel cat, String category) {
//        JPanel panel1 = makeBookPanel(category);
//        cat.add(panel1);
    }

    /**
     * Switch the book content to that of all the fantasy books
     * Near-identical methods exist for the horror, scifi and thriller books
     * @throws java.rmi.RemoteException
     */ 
    public void makeFantasy() throws RemoteException {
        this.book1 = new Book("The Hobbit", "J.R.R.Tolkien", 14.99, "Fantasy", "/img/hobbit.png");
        this.book2 = new Book("A Storm of Swords", "George.R.R.Martin", 19.99, "Fantasy", "/img/stormofswords.png");
        this.book3 = new Book("The Blade Itself", "Joe Abercrombie", 16.99, "Fantasy", "/img/blade.png");
    }

    public void makeHorror() throws RemoteException {
        this.book1 = new Book("The Shining", "Stephen King", 17.99, "Horror", "/img/shining.jpg");
        this.book2 = new Book("Dracula", "Bram Stoker", 19.99, "Horror", "/img/dracula.jpg");
        this.book3 = new Book("It", "Stephen King", 14.99, "Horror", "/img/it.jpg");
    }

    public void makeSciFi() throws RemoteException {
        this.book1 = new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 13.99, "Science Fiction", "/img/sheep.jpg");
        this.book2 = new Book("Ender's Game", "Orson Scott Card", 15.99, "Science Fiction", "/img/endersgame.jpg");
        this.book3 = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 19.99, "Science Fiction", "/img/hitchhiker.jpg");
    }

    public void makeThriller() throws RemoteException {
        this.book1 = new Book("The Partner", "John Grishham", 14.99, "Thriller", "/img/partner.jpg");
        this.book2 = new Book("The Hunt for Red October", "Tom Clancy", 18.99, "Thriller", "/img/october.jpg");
        this.book3 = new Book("The Street Lawyer", "John Grishham", 14.99, "Thriller", "/img/street.jpg");
    }

    /**
     * Add a book to the cart
     * @param name: The book title, to be added to the 
     * @param price: The book price, to be added to the total price
     */
    public void addProduct(String name, String price) {
        String total = name + "     " + price + '\n';
        // Must remove euro sign so value can be parsed
        price = price.replace("€","");
        totalprice += Double.parseDouble(price);
        products.add(total);
        // Tell user the product has been added
        JOptionPane.showMessageDialog(this, "Added to Cart:\n" + name, "Information", INFORMATION_MESSAGE);
    }

    // This method isn't actually used yet - maybe in the future.
    public void removeProduct(String name) {
        products.remove(name);
    }

    /**
     * Show all products in the cart, unless the cart is empty of course
     */
    public void showProducts() {
        if (this.products.isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Your cart is empty", "Error", ERROR_MESSAGE);
        }
        else {
            Iterator it = products.iterator();
            String contents = "";
            while (it.hasNext() == true) {
                contents += it.next();
            }
            JOptionPane.showMessageDialog(this, "Your cart contains:\n" + contents, "Information", INFORMATION_MESSAGE);
        }
        
    }

    /**
     * Buy all products in the cart, reset the price
     */
    public void buy() {
        JOptionPane.showMessageDialog(this, "You have bought items worth " + totalprice + "€.\nThank you for doing business with us!", "Information", INFORMATION_MESSAGE);
        this.products.clear();
        totalprice = 0;
    }
}
