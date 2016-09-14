/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarmi.cliente;
import java.rmi.Naming;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import javarmi.cliente.SHAHashing;
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
            
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
        /**
        * 
        * @param s  arreglo que contiene los strings a convertir a md5.
        */
     public static String procesar(String[] s,String hash) {
        String resultado = "";
        String encrypt   = ""; 
        SHAHashing sha = new SHAHashing();
        hash  = hash.toLowerCase();
        for (int i = 0; i < s.length; i++) {
            try {
            encrypt  = sha.convertirASHA256(s[i]);
            //System.out.println(encrypt + " -   "+hash);
            if(encrypt.equals(hash)){
                resultado  = s[i];
                System.out.print("'Has encontrado el sring  : '"+s[i]+ "' \n");
                return resultado;
            }
            }catch(Exception e){
                //exception.
            }
        }
        return resultado;
        
    }
}
