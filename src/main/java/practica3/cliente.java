/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

/**
 *
 * @author junior
 */
public class cliente {
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        int logiar = 0; //variable contadora de monitores resgistrados
        int cantidad = 0; //recibira la cantidad de monitores activos a traves de inicliente
        String cadena; //capturar lo que devuelva get loadAvg
        
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        Icoordinador2 objCoordinador = (Icoordinador2) registry.lookup("Coordinador");

        String leer = JOptionPane.showInputDialog("Ingrese el tiempo que se tomara entre cada peticion(seg): ");
        int seg = Integer.parseInt(leer);

        cantidad = objCoordinador.iniClient(seg);//obtenemos nuevamente la cantidad de monitores y enviamos los segundos a la funcion inicliente
        JOptionPane.showMessageDialog(null,"Monitores activos: " + cantidad);
        do {

            if (cantidad != 0) 
            {
                while (!(cadena = objCoordinador.getLoadAvg()).equals("Monitor caido")) {//mientras la cadena no este vacia imprimimos
                    cantidad = objCoordinador.iniClient(seg);//obtenemos nuevamente la cantidad de monitores y enviamos los segundos a la funcion inicliente
                    JOptionPane.showMessageDialog(null,"Monitores activos: " + cantidad);
                    JOptionPane.showMessageDialog(null, "("+logiar + ") " + cadena);
                    logiar++;//aumentamos el contador

                    esperar(seg);//esperamos el tiempo que le usuario ingreso
                }
            }
            else 
            { //si esta vacia no hacemos nada solo esperar
                esperar(seg); //esperamos la cantidad de segundo que ingreso el usuario para volver a hacer la peticion
            }
            

        } while (cantidad == 0);//cuando la cantidad sea 0 es porque no hay mas monitores

        JOptionPane.showMessageDialog(null,"Cliente terminado por falta de  monitores");
    }

    public static void esperar(int x) {
        //hacemos una pausa de segundos ingresados por el usuario
        try {
            Thread.sleep(x * 1000);
            System.out.println("Esperando\n");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
