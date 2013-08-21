/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WebPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *Clase que hereda de JFrame
 * Muestra el historial segun los sitios que se hayan visitado.
 * Se lee y guarda en un archivo llamado "histoy.aw" 
 * 
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
    private JButton borrar;
    private DefaultListModel<String> model=new DefaultListModel<String>();
      /** Metodo Constructor Historial
       * Se inicializa y llaman las clases para trabajar con los archivos.
       * Actualizar los componentes del Jframe.
      * @throws FileNotFoundException
      * @throws IOException  
      */ 
    public Historial() throws FileNotFoundException, IOException{
        file=new File("history.aw");
        panel=new JPanel();
        borrar=new JButton("borrar");
        lista=new JList();
        lista.setValueIsAdjusting(true);
        lista.setVisibleRowCount(2);
    historial=new ArrayList<String>();
    file.createNewFile();
    try{
    archivo=new FileReader("history.aw");
    }catch(IOException c){
        
    }
    borrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    if(e.getSource()==borrar){
                        int d=JOptionPane.showConfirmDialog(rootPane, "Desea borrar el historial?");
            if(d==JOptionPane.YES_OPTION){
                
                    if(file.exists()){
                    try {
                        file.delete();
                        cerrar();
                        file.delete();
                        
                        escritor=new FileWriter("history.aw");
                        pw=new PrintWriter(escritor);
                        
                            JOptionPane.showMessageDialog(rootPane, "Eliminadado correctamente");
                        historial.clear();
                        model.clear();
                        //repaint();
                    try {
                        file.createNewFile();
                       
                    } catch (IOException ex) {
                        Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
            }
                    }
            }
        });
   
    
    escritor=new FileWriter("history.aw",true);
    pw=new PrintWriter(escritor);
    
    setSize(200,alto);
    iniciarComponentes();
    //add(borrar);
    lista.setModel(model);
    iniciar();
        setResizable(false);
    setDefaultCloseOperation(HIDE_ON_CLOSE);
    archivo.close();
    
    }
    /** Metodo AddPagina
       * Agrega una direccion si es que no existe ya en el archivo o arraylist.
       * @param nombre 
       * @throws IOException  
      */ 
    public void addpagina(String nombre) throws IOException{
    escritor=new FileWriter("history.aw",true);
    
    pw=new PrintWriter(escritor);
    //iniciar();
    if(!nombre.contains(".")) {
            return;
        }
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
    
    /** Funcion Ya esta
       * busca en el arraylist si existe el string
       * @param nombre 
      * @return boolean.
      */ 
    public boolean YaEsta(String nombre){
    if(historial.contains(nombre))return true;
        return false;
    }
    
    /** Metodo Cerrar
       * Cierra los objetos modificadores de archivo.
      * @throws IOException  
      */ 
    public void cerrar() throws IOException{
        archivo.close();
        escritor.close();
        pw.close();
    }
    
    
    /** Metodo Iniciar
       * Lee el archivo si existe, y mete las direcciones a un AArraylist.
      * @param void
      * @return void.
      */ 
    private void iniciar() throws FileNotFoundException, IOException{
        
        String linea;
        
            BufferedReader bf=new BufferedReader(archivo);
            while((linea=bf.readLine())!=null){
                
                historial.add(linea);
                model.addElement(linea);
                
                resize();
            }
        }

        
        
    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
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
     //panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setLayout(null);
        JLabel l=new JLabel("Historial");
        l.setBounds(80,00,50,20);
        panel.add(l);
        
     for(String c:historial){
         System.out.println(c);
         
         model.addElement(c);
     }
     lista.setBounds(40,30, 120, 180);
     panel.add(lista);
     borrar.setBounds(60,220, 80, 20);
     panel.add(borrar);
     panel.setBounds(00, 0, 200, alto);
       this.add(panel);
    }
    
    
}
