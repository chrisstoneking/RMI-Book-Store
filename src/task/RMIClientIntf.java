package task;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Client interface. Not actually used, but we have it here anyway.
public interface RMIClientIntf extends Remote {
    public void initialise () throws RemoteException;
    
}
