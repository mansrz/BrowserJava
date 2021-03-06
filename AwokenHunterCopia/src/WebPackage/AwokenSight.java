/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WebPackage;

import java.awt.event.*;
import java.net.*;
import javax.net.ssl.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.*;
/**
 *Clase que contiene la ventana principal, el componente que muestra e interpreta el Html y menus. 
 * El websocket y los metodos que implementa para poder obtener la informacion. 
 * @author Alvaro Ortiz, Manuel Suarez, Denisse Pintado
 */
public class AwokenSight extends javax.swing.JFrame implements KeyListener{

    /**
     * Creates new form AwokenSight
     */
    
    
    WebProtocol protocol;
    Socket webSocket;
    String currentHostName;
    ArrayList<String> navigationArray;
    int navigationArrayIndex;
    private Historial historial;
      /** Constructor Awoken Sight 
       * Inicializa las variables de la ventana principal, agrega eventos. 
      * @throws IOException  
      */ 
    public AwokenSight() throws IOException {
        initComponents();
        
        
        
        webDisplay.addKeyListener(this);
            ini.setEnabled(false);
        setSize(1000, 500);
        historial=new Historial();
        webSocket = new Socket();
        currentHostName = "";
        navigationArray = new ArrayList<String>();
        navigationArrayIndex = -1;
        backButton.setEnabled(false);
        foreButton.setEnabled(false);
        refreshButton.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addressBar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                requestHTML(addressBar.getText(), WebNavigationType.ADDRESS);
                
            }
        });
        
        
        webDisplay.setContentType("text/html");
        webDisplay.setEditable(false);
        webDisplay.addHyperlinkListener(new HyperlinkListener()
        {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent event)
            {
                URL urlAddress = event.getURL();
                if (event.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED))
                {
                    if (urlAddress == null)
                    {
                        String urlDescription = event.getDescription();
                        if (urlDescription.charAt(0) != '/')
                        {
                            urlDescription = "/" + urlDescription;
                        }
                        
                        requestHTML(currentHostName + urlDescription, WebNavigationType.HYPERLINNK);
                    }
                    else
                    {
                        requestHTML(urlAddress.toExternalForm(), WebNavigationType.HYPERLINNK);
                    }
                }
            }
        });
        
        webDisplay.setText("<html>\n" +
"<head>\n" +
"<title>AwokenHunterBrowser</title>\n" +
"</head>\n" +
"<body>\n" +
"<b><p style=\"font:30pt Georgia;color:#ff0000;\">Welcome</p></b>\n" +
"<p>This is the intro page of your new browser.</p>\n" +"<marque>Awoken Hunter</marque>"+
"</body>\n" +
"</html>");
        
        
        
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
       if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S){
            int d=JOptionPane.showConfirmDialog(rootPane, "Desea salir del navegador?");
            if(d==JOptionPane.YES_OPTION){
                dispose();
            }
            
            
        }
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
          if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_H){
                historial.setVisible(true);
        }
      
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.isControlDown()){
  //          System.out.println("control3");
        }
    }
    /** WebProtocol
       * Enunm para protocoles de web.
      */ 
    public enum WebProtocol
    {
        /**
         *
         */
        HTTP,
        /**
         *
         */
        HTTPS,
        /**
         *
         */
        UNKNOWN
    }
    
    /** WebnavigationType
       * Enunm para los tipos de navegacion web
      */ 
    public enum WebNavigationType
    {
        /**
         *
         */
        BACK,
        /**
         *
         */
        FORE,
        /**
         *
         */
        REFRESH,
        /**
         *
         */
        ADDRESS,
        /**
         *
         */
        HYPERLINNK,
        /**
         *
         */
        MOVE
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addressBar = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        foreButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        ini = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        webDisplay = new javax.swing.JEditorPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(844, 30));

        backButton.setText("back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        foreButton.setText("fore");
        foreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foreButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("refresh");
        refreshButton.setToolTipText("");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        ini.setText("Inicio");
        ini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniActionPerformed(evt);
            }
        });

        jButton1.setText("IR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(backButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(foreButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(refreshButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(ini, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(34, 34, 34)
                .add(addressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 625, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .add(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, backButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(addressBar)
                        .add(refreshButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(foreButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton1)
                        .add(ini, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .add(15, 15, 15))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane2.setViewportView(webDisplay);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Awoken");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Cerrar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Historial");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenu2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jMenu2ComponentShown(evt);
            }
        });
        jMenu2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenu2KeyPressed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        if (navigationArrayIndex > 0)
        {
            requestHTML(navigationArray.get(navigationArrayIndex - 1), WebNavigationType.BACK);
        }
    }//GEN-LAST:event_backButtonActionPerformed

    private void foreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foreButtonActionPerformed
        if (navigationArrayIndex < navigationArray.size() - 1)
        {
            requestHTML(navigationArray.get(navigationArrayIndex + 1), WebNavigationType.FORE);
           
            
        }
    }//GEN-LAST:event_foreButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        requestHTML(navigationArray.get(navigationArrayIndex), WebNavigationType.REFRESH);
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void jMenu2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jMenu2ComponentShown
        // TODO add your handling code here:
        
        historial.setVisible(true);
    }//GEN-LAST:event_jMenu2ComponentShown

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked

        historial.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenu2KeyPressed
            // TODO add your handling code here:
       
    }//GEN-LAST:event_jMenu2KeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int d=JOptionPane.showConfirmDialog(rootPane, "Desea salir del navegador?");
            if(d==JOptionPane.YES_OPTION){
                dispose();
            }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void iniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniActionPerformed
        webDisplay.setText("<html>\n" +
"<head>\n" +
"<title>AwokenHunterBrowser</title>\n" +
"</head>\n" +
"<body>\n" +
"<b><p style=\"font:30pt Georgia;color:#ff0000;\">Welcome</p></b>\n" +
"<p>This is the intro page of your new browser.</p>\n" +"<marque>Awoken Hunter</marque>"+
"</body>\n" +
"</html>");
        
    }//GEN-LAST:event_iniActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(addressBar.getText()!=null){
        requestHTML(addressBar.getText(), WebNavigationType.ADDRESS);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /** Funcion RequestHTML
       * Funcion que hace el requerimiento mediante el websocket y si 
       * hay exito devuelve el string del html.
      * @param String.
      *@param WebnavigationType
      * @return String.
      */ 
    private String requestHTML(String webAddress, WebNavigationType navigationType)
    {
        
        String hostAddress;
        String fileAddress;
        
        if (webAddress.toUpperCase().contains("HTTP://"))
        {
            this.protocol = WebProtocol.HTTP;
            webAddress = webAddress.replaceFirst("(?i)http://", "");
        }
        else if (webAddress.toUpperCase().contains("HTTPS://"))
        {
            this.protocol = WebProtocol.HTTPS;
            webAddress = webAddress.replaceFirst("(?i)https://", "");
        }
        else
        {
            this.protocol = WebProtocol.HTTP;
        }
        
        if (!webAddress.contains("/"))
        {
            webAddress = webAddress + "/";
        }
        
        
        
        hostAddress = webAddress.substring(0, webAddress.indexOf("/"));
        fileAddress = webAddress.substring(webAddress.indexOf("/"));
        
        try
        {
            String request = "";
            
            if (this.protocol == WebProtocol.HTTP)
            {
                this.webSocket = new Socket(hostAddress, 80);
            }
            else if (this.protocol == WebProtocol.HTTPS)
            {
                this.webSocket = SSLSocketFactory.getDefault().createSocket(hostAddress, 443);
            }
            
            request = request + "GET " + fileAddress + " HTTP/1.1\n";
            request = request + "Host: " + hostAddress + "\n";
            request = request + "User-Agent: Mozilla/5.0 (Macintosh)\n";
            request = request + "Connection: close\n\n";
            
            PrintWriter outStream = new PrintWriter(webSocket.getOutputStream());
            outStream.print(request);
            outStream.flush();
            
            String reply = "";
            
            BufferedReader inStream = new BufferedReader(new InputStreamReader(webSocket.getInputStream()));
            
            while (true)
            {
                String line = inStream.readLine();
                if (line == null)
                {
                    break;
                }
                reply = reply + line + "\n";
            }
           new Cookie(reply,webAddress);
           
            
            int code = Integer.parseInt(reply.substring(9, 12));
            
            int newLocation = reply.indexOf("Location: ")+ "Location: ".length();
            String postLocation = reply.substring(newLocation);
            
            switch (code)
            {
                case 301:
                    requestHTML(postLocation.substring(0, postLocation.indexOf("\n")), WebNavigationType.MOVE);
                    //////AddressBar
                break;
                case 302:
                    requestHTML(postLocation.substring(0, postLocation.indexOf("\n")), WebNavigationType.MOVE);
                break;
                default:
                    String htmlString=reply.substring(reply.indexOf("<"));
                    
                    htmlString=htmlString.replaceAll("<frame", "<ssss");
                    htmlString=htmlString.replaceAll("<meta", "<ssss");
                    
                    
                    
                    addressBar.setText(webAddress);
                    try {
                    historial.addpagina(addressBar.getText());
                    
                } catch (IOException ex) {
                    Logger.getLogger(AwokenSight.class.getName()).log(Level.SEVERE, null, ex);
                }
                    webDisplay.setText(htmlString);
                    ini.setEnabled(true);
                    currentHostName = hostAddress;
                    
                    
                    switch (navigationType)
                    {
                        case BACK:
                            navigationArrayIndex--;
                        break;
                        case FORE:
                            navigationArrayIndex++;
                        break;
                        case REFRESH:
                            
                        break;
                        default:
                            
                            if (navigationArrayIndex < navigationArray.size() - 1)
                            {
                                for (int i = navigationArrayIndex + 1; i < navigationArray.size(); i++)
                                {
                                    navigationArray.remove(i);
                                }
                            }
                            
                            navigationArray.add(webAddress);
                            navigationArrayIndex++;
                        break;
                    }
                    
                    if (navigationArrayIndex <= 0)
                    {
                        backButton.setEnabled(false);
                    }
                    else
                    {
                        backButton.setEnabled(true);
                    }
                    if (navigationArrayIndex >= navigationArray.size() - 1)
                    {
                        foreButton.setEnabled(false);
                    }
                    else
                    {
                        foreButton.setEnabled(true);
                    }
                    if (navigationArray.size() == 0)
                    {
                        refreshButton.setEnabled(false);
                    }
                    else
                    {
                        refreshButton.setEnabled(true);
                    }
                    
                break;
            }
            
            webSocket.close();
            
        }
        catch (UnknownHostException ex)
        {
            JOptionPane.showMessageDialog(rootPane, "Error\nPosiles Fallas:\n\t1.Escriba correctamente la dirección\n\t2.Compruebe su conexión a Internet","Alerta",2);
            System.out.println("Exception");
        }
        catch (IOException ex)
        {
            System.out.println("Exception");
        }
        
        
        return "";
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AwokenSight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AwokenSight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AwokenSight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AwokenSight.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AwokenSight ve = null;
                try {
                    ve = new AwokenSight();
                } catch (IOException ex) {
                    Logger.getLogger(AwokenSight.class.getName()).log(Level.SEVERE, null, ex);
                }
             ve.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressBar;
    private javax.swing.JButton backButton;
    private javax.swing.JButton foreButton;
    private javax.swing.JButton ini;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refreshButton;
    private javax.swing.JEditorPane webDisplay;
    // End of variables declaration//GEN-END:variables
}
