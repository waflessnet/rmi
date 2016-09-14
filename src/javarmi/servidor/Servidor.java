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
                //String[] arrFile = obtenerString();
                System.out.println ("*** Iniciando Servidor ***");
                Registry registry = LocateRegistry.createRegistry(1099);
                LeerArchivo leer  = new LeerArchivo();
                String[] lineas   = leer.obtenerArrString(""+args[0]);
                //pasamos al constructor las lineas leídas.
                registry.rebind("Server", new ObjetoRemoto(lineas));
                System.out.println ("*** Servidor Iniciado ***");
            }catch(Exception e){
                e.printStackTrace();
            }
        
        }
      
}
