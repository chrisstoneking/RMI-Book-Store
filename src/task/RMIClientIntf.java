/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientIntf extends Remote {
    public void initialise () throws RemoteException;
    
}
