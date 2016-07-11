/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.rmi.Naming;

// The client, AKA customer. 
public class Client {

    public static void main(String args[]) {
        try {
            // Connect to server
            ShopServer server = (ShopServer) Naming.lookup("//localhost/RmiServer");
            // Add cart, fill it up
            Cart cart = server.createCart();
            cart.addProduct("A Game of Thrones");
            cart.addProduct("Watchmen");
            cart.addProduct("How to Cook");

            // Print contents
            System.out.println("listing contents ...");
            String[] contents = cart.listContents();
            for (String content : contents) {
                System.out.println(" - " + content);
            }
            
            // Buy stuff, with customer's ID as param
            cart.buy("Mickey Mouse");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
