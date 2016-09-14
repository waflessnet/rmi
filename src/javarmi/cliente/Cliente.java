/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarmi.cliente;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author waflessnet
 */
public class Cliente  {
    public static void main(String []args) {
        
        System.out.println ("*** Iniciando Cliente ***");
        try {
            /* el primer argumento debe ser  la ip a la cual nos vamos a conectar */
            javarmi.servidor.InterfazRemota irm = (javarmi.servidor.InterfazRemota) Naming.lookup("rmi://"+args[0]+":1099/Server");
            //obtenemos de manera remota los strings a procesar.
            String hash = irm.obtenerHash();
            System.out.println("buscamos : "+hash);
            boolean recorrer  = irm.recorrer();
            String resultado  = "";
            String[] arrLineas;
            while(recorrer){
                System.out.println("*** Solicitando un bloque ***");
                arrLineas =  irm.obtenerArrString();
                if(arrLineas.length == 0){
                    System.out.println("*** Ha terminado el recorrido ***");
                    break;
                }
                resultado = procesar(arrLineas,hash);
                if(!"".equals(resultado)){
                     irm.resultado(resultado);
                }
                recorrer  = irm.recorrer();
                
            }
            
            //System.out.println(Arrays.toString(arrLineas));
            
        } catch (NotBoundException | MalformedURLException | RemoteException e) {

            e.printStackTrace();

        }
    }
        /**
        * 
        * @param s  arreglo que contiene los strings a comparar mediante sha256.
        * @param hash el hash que se desea obtener
        * @return  devuelve  el valor decifrado.
        */
     public static String procesar(String[] s,String hash) {
        String resultado = "";
        String encrypt   = ""; 
        SHAHashing sha = new SHAHashing();
        hash  = hash.toLowerCase();
        for (String item : s) {
            try {
                encrypt = sha.convertirASHA256(item);
                System.out.println(encrypt);
                if (encrypt.equals(hash)) {
                    resultado = item;
                    System.out.print("'Has encontrado el significado del hash: '" + item + "' \n");
                    return resultado;
                }
            }catch(Exception e){
                //exception.
            }
        }
        return resultado;
        
    }
}
