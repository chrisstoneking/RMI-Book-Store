/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;

public class Server extends UnicastRemoteObject implements ShopServer {

  public Server() throws RemoteException {
  }

  public Cart createCart() throws RemoteException {
    System.out.println("create cart");
    return new ShopCart();
  }
  
  

  public static void main(String args[]) throws Exception {
	  System.out.println("RMI server started");
      
      // Create the registry
      try { //special exception handler for registry creation
          LocateRegistry.createRegistry(1099); 
          System.out.println("java RMI registry created.");
      } catch (RemoteException e) {
          //do nothing, error means registry already exists
          System.out.println("java RMI registry already exists.");
      }
         
      //Instantiate RmiServer

      Server obj = new Server();

      // Bind this object instance to the name "RmiServer"
      Naming.rebind("//localhost/RmiServer", obj);
      System.out.println("PeerServer bound in registry");
  }
}
