/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
public MainFrame (String title) throws HeadlessException {
        super(title);
    }
 */
package task;


import java.awt.*;
import java.rmi.RemoteException;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainFrame extends JFrame {
	
    public Controller control = null;
    JPanel cards;
    Book book1;
    Book book2;
    Book book3;
    Book book4;
    
    public void initialise () throws RemoteException {
            this.setTitle("RMI Book Store");
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            //this.setPreferredSize(new Dimension(800, 600));
            this.setBackground(Color.LIGHT_GRAY);
            this.control = new Controller();
            this.control.view = this;

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

            JPanel cats = new JPanel();
            cats.setLayout(new BoxLayout(cats, BoxLayout.Y_AXIS));
            cats.add(fantasyButton);
            cats.add(horrorButton);
            cats.add(scifiButton);
            cats.add(thrillerButton);
            this.add(cats, BorderLayout.LINE_START);

            cards = new JPanel(new CardLayout());
            
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
            this.add(cards, BorderLayout.LINE_END);

            JPanel extras = new JPanel();
            JButton buy = new JButton("Buy all in cart");
            JButton show = new JButton("Show all in cart");
            extras.add(buy);
            extras.add(show);
            this.add(extras, BorderLayout.PAGE_END);

            this.pack();
            this.setVisible(true);
    }
        
    /** Returns an ImageIcon, or null if the path was invalid.
     * @param path
     * @return  */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainFrame.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    protected static JPanel makeBookPanel (Book b) {
        JPanel book = new JPanel();
        book.setLayout(new BoxLayout(book, BoxLayout.Y_AXIS));
        JLabel cover = new JLabel(createImageIcon(b.cover));
        book.add(cover);
        JLabel stuff = new JLabel("<html>"+b.title+"<br>"+b.author+"<br>"+b.price+"</html>", JLabel.CENTER);
        book.add(stuff);
        JPanel cart = new JPanel();
        JButton addToCart = new JButton ("Add to Cart");
        cart.add(addToCart, BorderLayout.CENTER);
        book.add(cart);
        return book;
    }

    @Override
    public void paint(Graphics g) {
            super.paint(g);
    }
    
    public void makeCategory(JPanel cat) {
         GridLayout gl = new GridLayout(2,2, 10, 10);
        cat.setLayout(gl);
        
        JPanel panel1 = makeBookPanel(book1);
        JPanel panel2 = makeBookPanel(book2);
        JPanel panel3 = makeBookPanel(book3);

        cat.add(panel1);
        cat.add(panel2);
        cat.add(panel3);  
    }
    
    public void makeFantasy() throws RemoteException {
        this.book1 = new Book("The Hobbit", "J.R.R.Tolkien", "14.99€", "Fantasy", "/img/hobbit.png");
        this.book2 = new Book("A Storm of Swords", "George.R.R.Martin", "19.99€", "Fantasy", "/img/stormofswords.png");
        this.book3 = new Book("The Blade Itself", "Joe Abercrombie", "16.99€", "Fantasy","/img/blade.png");
    }
    
    public void makeHorror() throws RemoteException  {
        this.book1 = new Book("The Shining", "Stephen King", "17.99€", "Horror", "/img/shining.jpg");
        this.book2 = new Book("Dracula", "Bram Stoker", "19.99€", "Horror", "/img/dracula.jpg");
        this.book3 = new Book("It", "Stephen King", "14.99€", "Horror","/img/it.jpg");
    }
    
    public void makeSciFi() throws RemoteException  {
        this.book1 = new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", "13.99€", "Science Fiction", "/img/sheep.jpg");
        this.book2 = new Book("Ender's Game", "Orson Scott Card", "15.99€", "Science Fiction", "/img/endersgame.jpg");
        this.book3 = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "19.99€", "Science Fiction", "/img/hitchhiker.jpg");
    }
    
    public void makeThriller() throws RemoteException  {
        this.book1 = new Book("The Partner", "John Grishham", "14.99€", "Thriller", "/img/partner.jpg");
        this.book2 = new Book("The Hunt for Red October", "Tom Clancy", "18.99€", "Thriller", "/img/october.jpg");
        this.book3 = new Book("The Street Lawyer", "John Grishham", "14.99€", "Thriller", "/img/street.jpg");
    }
}

