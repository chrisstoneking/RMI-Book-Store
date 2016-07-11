/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ArrayList;

// The actual class for the cart
public class ShopCart extends UnicastRemoteObject implements Cart {
  List<String> products;

  public ShopCart() throws RemoteException {
    products = new ArrayList<>();
  } 

  public void addProduct(String name) {
    System.out.println("add product: "+name);
    products.add(name);
  }

  public void removeProduct(String name) {
    System.out.println("remove product: "+name);
    products.remove(name);
  }

  public String[] listContents() {
    System.out.println("list contents");
    String[] contents = new String[products.size()];
    products.toArray(contents);
    return contents;
  }

  public void buy(String custID) {
    System.out.println("buy products: "+custID);
    // If this were real, it would continue.
    // But this is just a demo, so we're done here.
  } 
}
