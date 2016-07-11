/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.rmi.Naming;

public class Client {

  public static void main(String args[]) {
    try {
      ShopServer server = (ShopServer)Naming.lookup("//localhost/RmiServer");
      Cart cart = server.createCart();
      cart.addProduct("Coffee");
      cart.addProduct("Tea");
      cart.addProduct("Java");

      System.out.println("listing contents ..."); 
      String[] contents = cart.listContents();
        for (String content : contents) {
            System.out.println(" - " + content);
        }

      cart.buy("rsinger");    
    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}

