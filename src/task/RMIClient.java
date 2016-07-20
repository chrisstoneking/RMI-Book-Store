/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.rmi.Naming;

public class RMIClient { 
    public static void main(String args[]) throws Exception {
        RMIServerIntf obj = (RMIServerIntf)Naming.lookup("//localhost/RmiServer");
        System.out.println(obj.getMessage()); 
        MainFrame m = obj.makeFrame();
        System.out.println("Got Frame");
        m.initialise();
        
    }
}
