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
        /**
         * @param args  arreglo  de largo 3 el cual debe tener  la ip,ruta,hash
         *  java -jar Servidor.jar <host> <path> <hash>
         */
        public static void main(String[] args){
            try{
                System.out.println ("*** Iniciando Servidor ***");
                System.setProperty("java.rmi.server.hostname", args[0]); 
                Registry registry = LocateRegistry.createRegistry(1099);
                LeerArchivo leer  = new LeerArchivo();
                String[] lineas   = leer.obtenerArrString(""+args[1]);
                //pasamos al constructor las lineas leídas.
                registry.rebind("Server", new ObjetoRemoto(lineas,args[1]));
                System.out.println ("*** Servidor Iniciado ***");
            }catch(Exception e){
                e.printStackTrace();
                System.out.println(" java -jar Servidor.jar <host> <path> <hash> \n");
            }
        
        }
      
}
