/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarmi.cliente;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author waflessnet
 */
public class SHAHashing {
    public String convertirASHA256(String s) throws NoSuchAlgorithmException{
        String password = s;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        //System.out.println(""+sb.toString());
         return sb.toString();
    }
}
