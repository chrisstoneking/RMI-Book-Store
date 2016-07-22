package task;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*; 

// The RMI server. Offers methods to client.
public class RMIServer extends UnicastRemoteObject implements RMIServerIntf {

    public RMIServer() throws RemoteException {
        super(0);    // required to avoid the 'rmic' step, see below
    }
    
    // This is the method the client calls.
    public MainFrame makeFrame() throws RemoteException {
        return new MainFrame();
    }

    // Setup the server.
    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
           
        //Instantiate RmiServer

        RMIServer obj = new RMIServer();

        // Bind this object instance to the name "RmiServer"
        Naming.rebind("//localhost/RmiServer", obj);
        System.out.println("PeerServer bound in registry");
    }

    
}
