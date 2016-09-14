/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarmi.servidor;
import java.rmi.*;
import java.rmi.server.*;
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
    
    public ObjetoRemoto (String[] arr,String hash) throws RemoteException
    {   
        super();
        this.arrLineas = arr;
        this.total     = this.arrLineas.length;
        this.bloque    = (int)(Math.round(this.total*porc));
        this.hash      = hash;
        
        
        this.recorrer  = true;
        
    }
    @Override
    public String obtenerHash() throws RemoteException{
        return this.hash;
    }
    @Override
    public String[] obtenerArrString() throws RemoteException {
        System.out.println("Solicitud de bloque \n");
        String[] salida;
        Integer fin = this.index + this.bloque;
        List<String> output = new LinkedList<>();
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
    @Override
    public void resultado(String r) throws RemoteException{
        this.recorrer = false;
        System.out.println("Un chacalito encontro el string : '"+r+"' \n");
    }
    public void setArreglo(String[] r) throws RemoteException{
        
    }
    @Override
    public boolean recorrer() throws RemoteException{
        return this.recorrer;
    }

    

}
