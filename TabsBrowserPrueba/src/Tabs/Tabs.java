package Tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.ImageIcon;
 
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Tabs extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
 
    public static final short _WIDTH =1200;
    public static final short _HEIGTH=500; 
     
    protected JTabbedPane  tabbedPane=null;
    private JButton        button   =null;
     JPanel panelBoton= new JPanel();
        JButton  backButton= new JButton("Back");
        JButton  foreButton= new JButton("Fore");
        JButton  refreshButton= new JButton("Refresh");   
        JTextField addressBar= new JTextField(50);
        JEditorPane webDisplay= new JEditorPane();
    //bROWSER
    WebProtocol protocol;
    Socket webSocket;
    String currentHostName;
    ArrayList<String> navigationArray;
    int navigationArrayIndex;
    
    private class PANEL extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;
    
   // private Historial historial;
     
    private String  title  =null;
    private JButton button =null;
    private Tabs  tapPe  =null;
    public PANEL(String title, JButton tabButton, Tabs jTapPe){
        this.title =title;
        this.button=tabButton;
        this.tapPe =jTapPe;
        button.addActionListener(this);
        }
    @Override
    public void actionPerformed(final ActionEvent e) {
        tapPe.removeTap(title);
        }
    }
    
    //adjuntando
    
    public enum WebProtocol
    {
        HTTP, HTTPS, UNKNOWN
    }
    
     public enum WebNavigationType
    {
        BACK, FORE, REFRESH, ADDRESS, HYPERLINNK, MOVE
    }
    
    
    
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
                    webDisplay.setText(htmlString);
                    
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
            System.out.println("Exception");
        }
        catch (IOException ex)
        {
            System.out.println("Exception");
        }
        
        
        return "";
    }
    
    
    
    public Tabs() {
        this.setSize(_WIDTH,_HEIGTH);
        tabbedPane=new JTabbedPane();   
        button=new JButton();
        JMenuBar menuBar= new JMenuBar();
        button.setIcon(new ImageIcon(getClass().getResource("/imagenes/tab2.png")));
        button.addActionListener(this);
        getContentPane().add(tabbedPane,BorderLayout.CENTER);
        getContentPane().add(button,BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {   
                new Tabs().setVisible(true);
            }
        });
    }
    public boolean removeTap(String title) {
        int i = tabbedPane.getTabCount();
        for (int index = 0; index < i; index++) {
            String temp = tabbedPane.getTitleAt(index);
            if (temp.equals(title)) {
                tabbedPane.removeTabAt(index);
                return true;
            }
        }
        return false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
          int x=0;
          JButton tabButton = new JButton();
          // cerrar.setIcon(new ImageIcon(getClass().getResource("/imagenes/cerrar.png")));
          tabButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/cerrar.png")));
          String title=String.valueOf(x);
          
          JPanel contenedor = new JPanel ();
          contenedor.setLayout(new BorderLayout());
          PANEL p=new PANEL(title,tabButton,this);
          
          p.add(backButton);
          p.add(foreButton);
          p.add(refreshButton);
          p.add(addressBar);
          
          webDisplay.setEnabled(false);
          
          contenedor.add(p, BorderLayout.NORTH);
          contenedor.add(webDisplay, BorderLayout.CENTER);
          
          tabbedPane.addTab(title,null, contenedor);
          tabButton.setPreferredSize(new Dimension(120,20));
          tabButton.setContentAreaFilled(false);
          JPanel pnl = new JPanel();
          pnl.setOpaque(false);
         
          
          JLabel label=new JLabel(title);
          //pnl.add(label);
          pnl.add(tabButton);
          
          tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, pnl);
          tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
          
    }
}