/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cart extends Remote {

  public void addProduct(String name) throws RemoteException;
  public void removeProduct(String name) throws RemoteException;
  public String[] listContents() throws RemoteException;
  public void buy(String custID) throws RemoteException;

}

