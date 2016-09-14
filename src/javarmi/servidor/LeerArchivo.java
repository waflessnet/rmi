/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarmi.servidor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author waflessnet
 */
public class LeerArchivo {
    
     public String[]  obtenerArrString() throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader("/home/waflessnet/proyectos/UMayor/computacion_distribuida/claves/cain.txt"));
        String str;
        List<String> output = new LinkedList<String>();

        while((str = in.readLine()) != null){
            output.add(str);
        }
        String[] arr = output.toArray(new String[output.size()]);
        return arr;
    }
}
