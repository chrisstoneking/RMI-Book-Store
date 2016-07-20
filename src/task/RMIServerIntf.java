/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

/**
 *
 * @author Chris
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerIntf extends Remote {
    public String getMessage() throws RemoteException;
    public MainFrame makeFrame() throws RemoteException;
}
