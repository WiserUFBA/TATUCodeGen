package br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen;

import java.awt.EventQueue;
import javax.swing.UIManager;
import br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen.gui.JanelaPrincipal;
import br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen.gui.SplashScreen;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *  This program help people to Generate code for devices or test the services
 * using the TATU.
 * @author jeferson
 */
public class TATUCodeGen {

    public static String mqtt_ip;
    public static int mqtt_port;
    public static String device_name;
    public static int device_id;
    public static int device_pan;
    
    /**
     * @param args This program don't receive any args from the terminal
     */
    public static void main(String[] args) {
        final SplashScreen splash = new SplashScreen();
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    /* Set the look and feel of the system */
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    /* Create the main screen */
                    JanelaPrincipal tatu = new JanelaPrincipal();
                    
                    /* Display the form and close the splash screen */
                    tatu.setVisible(true);
                    splash.setVisible(false);
                    splash.dispose();
                    
                } catch(ClassNotFoundException |
                        InstantiationException |
                        IllegalAccessException |
                        UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        });
    }
}
