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
    
     public String[]  obtenerArrString(String ruta) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(ruta));
        String str;
        List<String> output = new LinkedList<>();

        while((str = in.readLine()) != null){
            output.add(str);
        }
        String[] arr = output.toArray(new String[output.size()]);
        return arr;
    }
}
