/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarmi.servidor;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author waflessnet
 */
public interface InterfazRemota extends Remote {
  public String[]  obtenerArrString() throws RemoteException;
  public String  obtenerHash() throws RemoteException;
  public void  resultado(String r) throws RemoteException;
  public boolean recorrer() throws RemoteException;
}
