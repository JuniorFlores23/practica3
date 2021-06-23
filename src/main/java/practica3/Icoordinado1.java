/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author junior
 */
public interface Icoordinado1 extends Remote{
    int iniMonitor() throws RemoteException;
    void loadMonitor(String mensaje) throws RemoteException;
    
}
