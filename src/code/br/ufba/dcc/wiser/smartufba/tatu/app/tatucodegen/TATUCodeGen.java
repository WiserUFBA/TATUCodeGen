package br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen;

import java.awt.EventQueue;
import javax.swing.UIManager;
import br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen.gui.JanelaPrincipal;
import br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen.gui.SplashScreen;
import java.awt.Color;
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
    public static Color[] digital_pin_color = new Color[14];
    
    /**
     * @param args This program don't receive any args from the terminal
     */
    public static void main(String[] args) {
        SplashScreen splash = new SplashScreen(5000);
        splash.showSplashAndExit();
        for(int i = 0; i < 14; i++) TATUCodeGen.digital_pin_color[i] = Color.WHITE;
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    JanelaPrincipal tatu = new JanelaPrincipal();

                    /* Display the form */
                    tatu.setVisible(true);
                    
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
