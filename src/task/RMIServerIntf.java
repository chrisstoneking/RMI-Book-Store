package task;

import java.rmi.Remote;
import java.rmi.RemoteException;

// The server interface. All methods to be accessed by the client must be listed here.
public interface RMIServerIntf extends Remote {
    public MainFrame makeFrame() throws RemoteException;
}
