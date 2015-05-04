package br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

/**
 * @author jeferson
 */
public class AboutDialog extends JDialog{
      
    public AboutDialog(JFrame parent, String title) {
        super(parent, title);
        // Window config
        this.setLocationByPlatform(true);
        this.setSize(300, 230);
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
        
        // Create a message
        JPanel messagePane = new JPanel(new GridLayout(2, 1));
        JLabel about = new JLabel( "<html><center><font size=+2>"
                                +   "<p> TATU Code - Generator </p>"
                                +   "<p> Service - Tester </p> </font> <br/>"
                                +   "<p> <i> Version-1.0 </i> </p>"
                                +   "<p> Developed by: </p></center></html>");
        about.setBorder(new EmptyBorder(20,20,20,1));
        messagePane.add(about);
        
        // Create the logo
        String default_path_img = "/br/ufba/dcc/wiser/smartufba/tatu/app/tatucodegen/img/";
        JLabel img = new JLabel();
        img.setIcon(new ImageIcon( getClass().getResource(default_path_img +
                                                            "wiser_logo.png")));
        img.setBorder(new EmptyBorder(0,20,0,20));
        
        // Construct everything
        messagePane.add(img);
        getContentPane().add(messagePane);

        // System options
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(false);
        this.setModal(true);
    }
    
    @Override
    public JRootPane createRootPane() {
        JRootPane pane_main = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        InputMap inputMap = pane_main.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "ESCAPE");
        pane_main.getActionMap().put("ESCAPE", (new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        }));
        return pane_main;
    }
    
}
