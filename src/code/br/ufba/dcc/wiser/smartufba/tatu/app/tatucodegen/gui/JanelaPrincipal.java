package br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen.gui;

import br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen.TATUCodeGen;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

/**
 * This is the default Frame for the demo application
 * @author jeferson
 */
public class JanelaPrincipal extends JFrame {
    private final String default_path_img = "/br/ufba/dcc/wiser/smartufba/tatu/app/tatucodegen/img/"; 
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final JPanel contentPane;
    private static JTable colorTable;
    private JMenuBar menuBar;
    
    private void populateMenuBar(){
        menuBar = new JMenuBar();
        
        JMenu editMenu = new JMenu();
        JMenu fileMenu = new JMenu();
        JMenu helpMenu = new JMenu();
        
        JMenuItem openMenuItem = new JMenuItem();
        JMenuItem saveMenuItem = new JMenuItem();
        JMenuItem saveAsMenuItem = new JMenuItem();
        JMenuItem exitMenuItem = new JMenuItem();
        
        JMenuItem preferencesMenuItem = new JMenuItem();
        
        JMenuItem aboutMenuItem = new JMenuItem();
        
        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        preferencesMenuItem.setMnemonic('p');
        preferencesMenuItem.setText("Preferences");
        editMenu.add(preferencesMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);
    }
    
    public JanelaPrincipal(){
        // General Options
        this.setTitle("TATU Code Generator App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource( default_path_img +
                                                                "icon.gif")).getImage());
        this.setBounds(((screen.width-700)/2), ((screen.height-600)/2), 700, 600);
        this.setResizable(false);
        
        // Define the complete layout
        contentPane = new JPanel(new MigLayout());
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        this.setContentPane(contentPane);
        
        // Create the menu bar
        this.populateMenuBar();
        this.setJMenuBar(menuBar);
        
        // Create the simulation panel area
        JPanel simulationPanel = new JPanel(new MigLayout());
        simulationPanel.setBackground(new Color(255,255,255));
        simulationPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // Viewport to the color table
        JScrollPane colorScroll = new JScrollPane();
        colorScroll.setBackground(new java.awt.Color(255, 255, 255));
        colorScroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        colorScroll.setMinimumSize(new java.awt.Dimension(20, 20));
        colorScroll.setColumnHeaderView(null);
        colorScroll.getColumnHeader().setVisible(false);
        
        // Create the color table
        colorTable = new JTable();
        DefaultTableModel colorTableModel = new DefaultTableModel(new Object [][] {{null, null, null,
            null, null, null, null, null, null, null, null, null, null, null}},
            new String [] {"", "", "", "", "", "", "", "", "", "", "", "", "", ""}) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, String.class,
                String.class, String.class, String.class, String.class, String.class,
                String.class, String.class, String.class, String.class
            };

            @Override
            public Class getColumnClass(int columnIndex){return types [columnIndex];}
        };
        colorTable.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        colorTable.setModel(colorTableModel);
        colorTable.setAutoscrolls(false);
        colorTable.setCellSelectionEnabled(false);
        colorTable.setShowHorizontalLines(false);
        colorTable.setShowVerticalLines(false);
        colorTable.setShowGrid(false);
        colorTable.setEnabled(false);
        colorScroll.setViewportView(colorTable);
        simulationPanel.add(colorScroll,"span, w 260px, gapleft 235px");
        
        // Enable the color colum edition
        colorTable.setDefaultRenderer(Object.class, new ColorTableRenderer());
        
        // Viewport to the control table
        JScrollPane controlScroll = new JScrollPane();
        colorScroll.setBackground(new java.awt.Color(255, 255, 255));
        colorScroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        colorScroll.setMinimumSize(new java.awt.Dimension(20, 20));
        
        // Create the control table
        JTable controlTable = new JTable();
        controlTable.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        
        // Populate the simulation Area
        JLabel arduinoImg = new JLabel();
        arduinoImg.setIcon(new ImageIcon(getClass().getResource(default_path_img +
                                                                "arduino_res_bg.jpg")));
        arduinoImg.setText("");
        simulationPanel.add(arduinoImg, "h 360px, w 510px");
        
        // Create the simulation control
        DefaultListModel<String> consoleList = new DefaultListModel<>();
        JPanel controlPanel = new JPanel(new MigLayout());
        JScrollPane consoleArea = new JScrollPane();
        JList<String> consoleLog = new JList<>(consoleList);
        JButton startButton, stopButton;
        
        // Setting the Console Log
        consoleLog.setBackground(new Color(0,0,0));
        consoleLog.setForeground(new Color(0, 255, 0));
        consoleArea.setBorder(BorderFactory.createTitledBorder("Console Log"));
        consoleArea.setViewportView(consoleLog);
        controlPanel.add(consoleArea, "west, gap 10px 10px 0px 10px, h 220px, w 550px");
        
        // Console TEST
        for(int i = 0; i < 10; i++)
            consoleList.addElement("teste" + i);
        
        // Put the buttons on the control Area
        String buttonConfig = "span, gap 0px 10px 5px 5px, h 100px, w 150px";
        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                TATUCodeGen.digital_pin_color[2] = Color.GREEN;
                TATUCodeGen.digital_pin_color[1] = Color.BLUE;
                JanelaPrincipal.colorTable.repaint();
            }
        });
        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                TATUCodeGen.digital_pin_color[3] = Color.YELLOW;
                TATUCodeGen.digital_pin_color[4] = Color.ORANGE;
                JanelaPrincipal.colorTable.repaint();
            }
        });
        controlPanel.add(startButton,buttonConfig);
        controlPanel.add(stopButton,"span, gap 0px 10px 5px 5px, h 50px, w 150px");
        
        // Populate all the window
        contentPane.add(simulationPanel, "north, gap 10px 10px 10px 10px, h 400px, w 676px");
        contentPane.add(controlPanel, "south, h 150px, w 680px");
    }
}
