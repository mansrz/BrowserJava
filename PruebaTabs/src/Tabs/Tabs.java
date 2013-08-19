package Tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class Tabs extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
 
    public static final short _WIDTH =1200;
    public static final short _HEIGTH=500; 
     
    protected JTabbedPane  tabbedPane=null;
    private JButton        button   =null;
    private class PANEL extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;
     
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
    public Tabs() {
        this.setSize(_WIDTH,_HEIGTH);
        tabbedPane=new JTabbedPane();   
        button=new JButton();
        
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
          PANEL p=new PANEL(title,tabButton,this);
          tabbedPane.addTab(title,null, p);
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