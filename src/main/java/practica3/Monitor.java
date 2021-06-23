/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author junior
 */
public class Monitor {
    public void pingMonitor(){}
    
    static Icoordinado1 objCoordinador;
    static int seg;
    static int contador = 1;
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        String Ip = JOptionPane.showInputDialog("Ingresa la direccion ip del server");
        try
        {
            objCoordinador = (Icoordinado1) Naming.lookup("rmi://"+Ip+"/Coordinador");//en coordinador se guarda la informacion de cada monitor
            seg = objCoordinador.iniMonitor();
            
        }
        catch(Exception e){JOptionPane.showMessageDialog(null, e.getMessage());}
        
        
        System.out.print("Ingrese 0 para finalizar:");
        Scanner leer = new Scanner(System.in);
        contador = leer.nextInt();
        
        Monitor ejecucion = new Monitor();
        ejecucion.run();
        
    }
    
    public void run() {//corremos el monitor para mostrar el resultado de proc/loadavg
        while (contador == 1) 
        {

            File comando = new File("/proc/loadavg");//creamos un archivo con el comando a ejecutar
            FileReader leer;//creamos la variable donde guardaremos lo leido del comando ejecutado
            String cadena = "";
            try {
                leer = new FileReader(comando);//leemos el resultado del comando ejecutado
                BufferedReader leido = new BufferedReader(leer);//en la variable leido guardamos lo que contiene la variable leer
                cadena = leido.readLine();//en la variable cadena guardamos lo mismo para poder mostrarlo en pantalla
                objCoordinador.loadMonitor(cadena);//enviamos el dato al coordinador
                Thread.sleep(seg * 1000);
            } catch (FileNotFoundException ex) {
                System.out.print(ex.getMessage());
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }

        try {
            objCoordinador.loadMonitor("NoMonitor");//enviamos la informacion al coordinador
        } catch (RemoteException ex) {
            System.out.print(ex.getMessage());
        }

        JOptionPane.showMessageDialog(null,"Este monitor ha terminado su funcion...");
    }
    
}
