
package task;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.JOptionPane.*;


// This is the GUI. It is the only thing actually passed from client to server.
public class MainFrame extends JFrame {

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
        this.setTitle("RMI Book Store");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.LIGHT_GRAY);
        this.control = new Controller();
        this.control.view = this;

        // Set up the switch-category buttons
        JRadioButton fantasyButton = new JRadioButton("Fantasy");
        fantasyButton.addActionListener(this.control);
        fantasyButton.setActionCommand("FANTASY");
        JRadioButton horrorButton = new JRadioButton("Horror");
        horrorButton.addActionListener(this.control);
        horrorButton.setActionCommand("HORROR");
        JRadioButton scifiButton = new JRadioButton("Science Fiction");
        scifiButton.addActionListener(this.control);
        scifiButton.setActionCommand("SCIFI");
        JRadioButton thrillerButton = new JRadioButton("Thriller");
        thrillerButton.addActionListener(this.control);
        thrillerButton.setActionCommand("THRILLER");

        ButtonGroup group = new ButtonGroup();
        group.add(fantasyButton);
        group.add(horrorButton);
        group.add(scifiButton);
        group.add(thrillerButton);

        // Add the buttons to the panel, in a way that they're arranged vertically
        JPanel cats = new JPanel();
        cats.setLayout(new BoxLayout(cats, BoxLayout.Y_AXIS));
        cats.add(fantasyButton);
        cats.add(horrorButton);
        cats.add(scifiButton);
        cats.add(thrillerButton);
        this.add(cats, BorderLayout.LINE_START);

        // Start work on the central part - use CardLayout for switching between categories
        cards = new JPanel(new CardLayout());

        // For each category: Reset the values stored in the local Book vars, 
        // Build the category's main panel and add it to the CardLayout
        makeFantasy();
        JPanel card1 = new JPanel();
        makeCategory(card1);
        cards.add(card1, "FANTASY");

        makeHorror();
        JPanel card2 = new JPanel();
        makeCategory(card2);
        cards.add(card2, "HORROR");

        makeSciFi();
        JPanel card3 = new JPanel();
        makeCategory(card3);
        cards.add(card3, "SCIFI");

        makeThriller();
        JPanel card4 = new JPanel();
        makeCategory(card4);
        cards.add(card4, "THRILLER");
        // Add the central part to the GUI
        this.add(cards, BorderLayout.LINE_END);

        // Next, add the button for buying everything and the button for showing everything
        JPanel extras = new JPanel();
        JButton buy = new JButton("Buy all in cart");
        buy.setActionCommand("BUYALL");
        buy.addActionListener(control);
        JButton show = new JButton("Show all in cart");
        show.setActionCommand("SHOWALL");
        show.addActionListener(control);
        extras.add(buy);
        extras.add(show);
        this.add(extras, BorderLayout.PAGE_END);

        // Other standard GUI stuff
        this.pack();
        this.setVisible(true);
    }

    /**
     * Make an ImageIcon
     * @author: Someone else, I unfortunately forget who and lost the source. Sorry.
     * @param path: Local path to the image.
     * @return an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainFrame.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Make a JPanel containing all the info on one book
     * @param b: The book for the JPanel
     * @return a JPanel containing the info
     */
    public JPanel makeBookPanel(Book b) {
        JPanel book = new JPanel();
        book.setLayout(new BoxLayout(book, BoxLayout.Y_AXIS));
        JLabel cover = new JLabel(createImageIcon(b.cover));
        book.add(cover);
        JLabel stuff = new JLabel("<html>" + b.title + "<br>" + b.author + "<br>" + b.price + "</html>", JLabel.CENTER);
        book.add(stuff);
        JPanel cart = new JPanel();
        JButton addToCart = new JButton("Add to Cart");
        String com = "BUY;" + b.title + ";" + b.price;
        addToCart.setActionCommand(com);
        addToCart.addActionListener(this.control);
        cart.add(addToCart, BorderLayout.CENTER);
        book.add(cart);
        return book;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
        
    /**
     * Set up the main panel (card) for a category
     * @param cat: The card to contain the book panels
     */
    public void makeCategory(JPanel cat) {
        GridLayout gl = new GridLayout(2, 2, 10, 10);
        cat.setLayout(gl);

        JPanel panel1 = makeBookPanel(book1);
        JPanel panel2 = makeBookPanel(book2);
        JPanel panel3 = makeBookPanel(book3);

        cat.add(panel1);
        cat.add(panel2);
        cat.add(panel3);
    }

    /**
     * Switch the book content to that of all the fantasy books
     * Near-identical methods exist for the horror, scifi and thriller books
     * @throws java.rmi.RemoteException
     */ 
    public void makeFantasy() throws RemoteException {
        this.book1 = new Book("The Hobbit", "J.R.R.Tolkien", "14.99€", "Fantasy", "/img/hobbit.png");
        this.book2 = new Book("A Storm of Swords", "George.R.R.Martin", "19.99€", "Fantasy", "/img/stormofswords.png");
        this.book3 = new Book("The Blade Itself", "Joe Abercrombie", "16.99€", "Fantasy", "/img/blade.png");
    }

    public void makeHorror() throws RemoteException {
        this.book1 = new Book("The Shining", "Stephen King", "17.99€", "Horror", "/img/shining.jpg");
        this.book2 = new Book("Dracula", "Bram Stoker", "19.99€", "Horror", "/img/dracula.jpg");
        this.book3 = new Book("It", "Stephen King", "14.99€", "Horror", "/img/it.jpg");
    }

    public void makeSciFi() throws RemoteException {
        this.book1 = new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", "13.99€", "Science Fiction", "/img/sheep.jpg");
        this.book2 = new Book("Ender's Game", "Orson Scott Card", "15.99€", "Science Fiction", "/img/endersgame.jpg");
        this.book3 = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "19.99€", "Science Fiction", "/img/hitchhiker.jpg");
    }

    public void makeThriller() throws RemoteException {
        this.book1 = new Book("The Partner", "John Grishham", "14.99€", "Thriller", "/img/partner.jpg");
        this.book2 = new Book("The Hunt for Red October", "Tom Clancy", "18.99€", "Thriller", "/img/october.jpg");
        this.book3 = new Book("The Street Lawyer", "John Grishham", "14.99€", "Thriller", "/img/street.jpg");
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
