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
public interface Icoordinador2 extends Remote{
    int iniClient(int segundos) throws RemoteException;
    String getLoadAvg() throws RemoteException;
    
}
