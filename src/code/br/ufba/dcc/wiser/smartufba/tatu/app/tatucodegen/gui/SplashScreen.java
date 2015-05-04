package br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * Splash Screen.
 * @author jeferson
 */
public class SplashScreen extends JWindow {
    private final String default_path_img = "/br/ufba/dcc/wiser/smartufba/tatu/app/tatucodegen/img/";
    private final int width = 450;
    private final int height = 300;
    
    public SplashScreen() {
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);
        this.setAlwaysOnTop(true);
        
        // Position and size
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);
        
        // Construct the splash screen
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon( getClass().getResource(default_path_img +
                                                            "tatu-logo.png")));
        
        content.add(label, BorderLayout.CENTER);
        setVisible(true);
        try{
            Thread.sleep(2000);
        }
        catch(Exception e){}
    }
    
}
