package task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class BookListCellRenderer extends DefaultListCellRenderer {
	
	Controller control = null;
	
	public BookListCellRenderer(Controller control)
	{
		this.control = control;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Color backgroundColor = cellHasFocus ? Constants.focusColor : Constants.backgroundColor;
		
		JPanel bookItemPanel = new JPanel(new BorderLayout());
		bookItemPanel.setBackground(backgroundColor);
		
		bookItemPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
		bookItemPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		if (value instanceof Book)
		{
			Book b = (Book) value;
			
	        JLabel cover = new JLabel(new ImageIcon(b.coverImage));
	        cover.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	        bookItemPanel.add(cover, BorderLayout.WEST);
	        
	        JPanel descriptionPanel = new JPanel();
	        descriptionPanel.setBackground(backgroundColor);
	        descriptionPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
	        descriptionPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
	        //descriptionPanel.setBackground(Color.DARK_GRAY);
	        
	        BoxLayout boxLayout = new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS);
	        descriptionPanel.setLayout(boxLayout);
	        JLabel title = Constants.CreateMyLabel(b.title, 24, Font.BOLD, JLabel.LEFT_ALIGNMENT);
	        title.setBorder(new EmptyBorder(0, 0, 10, 0));
	        
	        JLabel author = Constants.CreateMyLabel("von " + b.author, 16, Font.PLAIN, JLabel.LEFT_ALIGNMENT);
	        author.setBorder(new EmptyBorder(0, 0, 10, 0));
	        
	        JTextArea description = Constants.CreateMyTextarea(b.getTruncatedDescription(), 16, Font.ITALIC, JTextArea.LEFT_ALIGNMENT);
	        description.setBorder(new EmptyBorder(0, 0, 10, 0));
	        
	        JLabel price = Constants.CreateMyLabel( String.format("%.2f%n", b.price) + " €", 20, Font.PLAIN, JLabel.LEFT_ALIGNMENT);
	        price.setBorder(new EmptyBorder(0, 0, 10, 0));
	        
	        descriptionPanel.add(title);
	        descriptionPanel.add(author);
	        descriptionPanel.add(description);
	        descriptionPanel.add(price);
	        
//	        JButton addToCart = new JButton("Add to Cart");
//	        String com = "BUY;" + b.title + ";" + b.price + " €";
//	        addToCart.setActionCommand(com);
//	        addToCart.addActionListener(this.control);
//	        addToCart.setAlignmentX(JButton.LEFT_ALIGNMENT);
//	        descriptionPanel.add(addToCart, BorderLayout.CENTER);
			
	        bookItemPanel.add(descriptionPanel, BorderLayout.CENTER);
		}
		return bookItemPanel;
	}
	
}
