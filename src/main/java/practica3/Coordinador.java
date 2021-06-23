/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;

/**
 *
 * @author junior
 */
public class Coordinador extends UnicastRemoteObject implements Icoordinado1, Icoordinador2
{
    int monitores = 0; //variable que contara los monitores registrados
    int time;
    String msj = "No hay monitores en este momento";
    
    public Coordinador() throws RemoteException {
        super();
    }
    
    @Override
    public int iniClient(int seg) throws RemoteException 
    {
        time = seg;

        //Aqui se llama a ping monitor

        return monitores;
    }

    @Override
    public String getLoadAvg() throws RemoteException {
        return msj;
    }

    @Override
    public int iniMonitor() throws RemoteException 
    {
        monitores++;
        return time;    
    }

    @Override
    public void loadMonitor(String mensaje) throws RemoteException 
    {
        if ("Monitor caido".equals(mensaje)) { //comparamos que la cadena que nos indica si el monitor esta activo
            monitores--;//restamos la cantidad de monitores activos en 1
        }

        msj = mensaje;
    }
    
    public static void main(String[] args) throws RemoteException, AlreadyBoundException 
    {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("Coordinador", new Coordinador());
        JOptionPane.showMessageDialog(null,"Servidor conectado...");
    }
    
}
