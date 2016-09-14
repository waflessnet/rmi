/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarmi.servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author waflessnet
 */
public class Servidor {
   
        public static void main(String[] args){
            try{
                // el primer argumento es la ip
                // segundo argumento debe ser la ruta donde esta el archivo txt a leer
                System.out.println ("*** Iniciando Servidor ***");
                System.setProperty("java.rmi.server.hostname", args[0]); 
                Registry registry = LocateRegistry.createRegistry(1099);
                LeerArchivo leer  = new LeerArchivo();
                String[] lineas   = leer.obtenerArrString(""+args[1]);
                //pasamos al constructor las lineas leídas.
                registry.rebind("Server", new ObjetoRemoto(lineas));
                System.out.println ("*** Servidor Iniciado ***");
            }catch(Exception e){
                e.printStackTrace();
            }
        
        }
      
}
