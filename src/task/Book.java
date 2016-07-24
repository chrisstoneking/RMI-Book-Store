package task;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//The book class, used to store info on each book
public class Book  extends UnicastRemoteObject{
   public String title;
   public String author;
   public String description;
   public double price;
   public String category;
   public String cover;
   public byte[] coverImage;
   
   private String truncatedDescription = null;
   private final int minDescriptionLength = 150;

    public Book(String title, String author, double price, String category, String cover) throws RemoteException {
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
        this.cover = cover;
    }
    
    /**
     * Create a truncated description. Go through all characters until dot, exclamation mark or interrogation mark.
     * The following and previous characters should not be a digit because of thousands separator.
     * @see {@link Book#minDescriptionLength} - Minimum number of characters.
     * @return truncated description
     */
    public String getTruncatedDescription()
    {
    	if (truncatedDescription == null)
    	{
    		if (description == null || description.length() == 0) return "";
    		char[] characters = description.toCharArray();
    		int i = 0;
    		for (; i < characters.length; i++) {
    			if(i - 1 >= 0 && i + 1 < characters.length){
    				if(i >= 80 && !Character.isDigit(characters[i - 1]) && !Character.isDigit(characters[i + 1]) && ( characters[i] == '.' || characters[i] == '!' || characters[i] == '?' )) break;
    			}
			}
    		truncatedDescription = description.substring(0, i) + "\u2026";
    	}
    	return truncatedDescription;
    }
    
    public Book() throws RemoteException{};
    
}
