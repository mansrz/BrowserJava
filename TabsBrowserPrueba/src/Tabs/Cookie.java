/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Manuel
 */
public class Cookie {
    String cookie,nombre1;
    public String getnombre(String nombre){
         int c1=0,c2;
       c1=nombre.indexOf(".");
       c2=nombre.indexOf(".",c1+1);
        
       if(c2==-1){
       return nombre.substring(1,c1);
       }else{
       return nombre.substring(c1+1,c2);    
       }
    }
    public Cookie(String cook, String nombre){
        System.out.println(nombre);
      nombre1=getnombre(nombre);
        if(cook.contains("Set-Cookie")){
            
       File f;
       int cont=0,co=0;
       
       String cookie=new String();
       while(cont!=-1){
       cont=cook.indexOf("Set-Cookie:",cont+1);
       if(cont>0){
       co=cook.indexOf("\n",cont);
       cookie=cookie+cook.substring(cont+11, co);
       }
       }
        
       f = new File("Awoke@"+nombre1+".txt");
       //Escritura
       try{
       FileWriter w = new FileWriter(f);
       BufferedWriter bw = new BufferedWriter(w);
       PrintWriter wr = new PrintWriter(bw);	
       wr.write(cookie);
       wr.close();
       bw.close();
       }catch(IOException e){};
      }
    }

           
    
}
