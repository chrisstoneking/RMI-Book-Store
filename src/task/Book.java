package task;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Book  extends UnicastRemoteObject{
   public String title;
   public String author;
   public String price;
   public String category;
   public String cover;

    public Book(String title, String author, String price, String category, String cover) throws RemoteException {
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
        this.cover = cover;
    }
    
}
