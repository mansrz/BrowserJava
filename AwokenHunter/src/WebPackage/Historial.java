/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WebPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author Manuel
 */
public class Historial extends JFrame {
    private int alto=300;
    private ArrayList<String> historial;
    private FileReader archivo;
    private File file;
    private FileWriter escritor;
    private PrintWriter pw;
    private JPanel panel;
    private JList lista;
    private DefaultListModel<String> model=new DefaultListModel<String>();
    public Historial() throws FileNotFoundException, IOException{
        file=new File("history.aw");
        panel=new JPanel();
        lista=new JList();
    historial=new ArrayList<String>();
    file.createNewFile();
    try{
    archivo=new FileReader("history.aw");
    }catch(IOException c){
        
    }
    setResizable(false);
    
    escritor=new FileWriter("history.aw",true);
    pw=new PrintWriter(escritor);
    
    setSize(200,alto);
    iniciarComponentes();
    lista.setModel(model);
    iniciar();
    setDefaultCloseOperation(HIDE_ON_CLOSE);
    archivo.close();
    
    }
    public void addpagina(String nombre) throws IOException{
    escritor=new FileWriter("history.aw",true);
    pw=new PrintWriter(escritor);
    
        if(!YaEsta(nombre))    {
     historial.add(nombre);   
     pw.println(nombre);
     model.addElement(nombre);
     resize();
     
    pw.close();
    }
    
    }
    private void resize(){
    setSize(200,alto);
    }
    public boolean YaEsta(String nombre){
    if(historial.contains(nombre))return true;
        return false;
    }
    public void cerrar() throws IOException{
        archivo.close();
        escritor.close();
        pw.close();
    }
    
    private void iniciar() throws FileNotFoundException, IOException{
        
        String linea;
        
            BufferedReader bf=new BufferedReader(archivo);
            while((linea=bf.readLine())!=null){
                
                historial.add(linea);
                model.addElement(linea);
                
                resize();
            }
        }

        
        
    public static void main(String [] args) throws FileNotFoundException{
        try {
            Historial hi=new Historial();
            
            hi.addpagina("aerdrdfg34dfsr.csad");
            hi.addpagina("aerdrddfsr.csad");
            hi.addpagina("aerdrdfg3434dfsr.csad");
            hi.cerrar();
            hi.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void iniciarComponentes() {
     panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(new JLabel("Historial"));
     
     for(String c:historial){
         System.out.println(c);
         
         model.addElement(c);
     }
     panel.add(lista);
     panel.setBounds(0, 0, 200, alto);
       this.add(panel);
    }
    
    
}
