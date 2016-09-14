/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarmi.servidor;
import java.io.File;
import java.math.BigInteger;
import java.rmi.*;
import java.rmi.server.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author waflessnet
 */
public class ObjetoRemoto extends UnicastRemoteObject implements InterfazRemota {
    /**
     * Construye una instancia de ObjetoRemoto
     * @throws RemoteException
     */
    private  String[] arrLineas;
    private  Integer index = 0;
    private  Integer bloque = 1000;
    private  Integer total  = 0;
    private  final double  porc  = 0.1; 
    private  String hash ="";
    private   boolean recorrer = false;
    
    public ObjetoRemoto (String[] arr) throws RemoteException
    {   
        super();
        this.arrLineas = arr;
        this.total     = this.arrLineas.length;
        this.bloque    = (int)(Math.round(this.total*porc));
        //this.hash      = "7406e17d2e30b05b7220a800fad53a22";
        //this.hash      = "47b594f79af26bcef8a0a87e31f5575b";
        //this.hash      = "b80e8b333c5b9b7dc3bb1529941b848a";
        // los de face  
        //this.hash      = "4b02a2a941c9301e10486027c6277c7a";
        //this.hash      = "c7620f2946fa148451eb6405f377cb2e";
        //sha265 
        this.hash        = "1040C8B58846E831553A255B62C3E5B2FD76F8C6E61167255F0FA4593C873755";
        
        this.recorrer  = true;
        
    }
    public String obtenerHash() throws RemoteException{
        return this.hash;
    }
    public String[] obtenerArrString() throws RemoteException {
        System.out.println("Solicitud de bloque nuevo \n");
        String[] salida;
        Integer fin = this.index + this.bloque;
        List<String> output = new LinkedList<String>();
        while(this.index < fin && this.index < this.arrLineas.length ){
            output.add(this.arrLineas[this.index]);
            this.index++;
        }
        if(this.index >= this.arrLineas.length){
            this.recorrer = false;
        }
        salida = output.toArray(new String[output.size()]);
        return salida;
    }
    public void resultado(String r) throws RemoteException{
        this.recorrer = false;
        System.out.println("Se ha encontrado el string: '"+r+"' \n");
    }
    public void setArreglo(String[] r) throws RemoteException{
        
    }
    public boolean recorrer() throws RemoteException{
        return this.recorrer;
    }

    

}
