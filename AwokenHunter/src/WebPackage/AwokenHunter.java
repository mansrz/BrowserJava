package WebPackage;

import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jake
 */
public class AwokenHunter {
    AwokenSight f;
    AwokenHunter() throws IOException{
        f=new AwokenSight();
        try {
            UIManager.setLookAndFeel("WindowsLookAndFee");
            SwingUtilities.updateComponentTreeUI(f);
            f.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
        
    }
    public static void main(String args[]) throws IOException{
        AwokenHunter d=new AwokenHunter();
    }
    
}
