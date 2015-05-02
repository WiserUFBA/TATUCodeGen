package br.ufba.dcc.wiser.smartufba.tatu.app.tatudemo.gui;

import java.awt.Color;
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
import javax.swing.table.TableColumn;
import net.miginfocom.swing.MigLayout;

/**
 * This is the default Frame for the demo application
 * @author jeferson
 */
public class JanelaPrincipal extends JFrame {
    
    private final JPanel contentPane;
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
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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
        this.setTitle("TATU Demonstration Program");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(333, 84, 700, 600);
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
        JTable colorTable = new JTable();
        colorTable.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        colorTable.setModel(new DefaultTableModel(new Object [][] {{null, null, null,
            null, null, null, null, null, null, null, null, null, null, null}},
            new String [] {"", "", "", "", "", "", "", "", "", "", "", "", "", ""}) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, String.class,
                String.class, String.class, String.class, String.class, String.class,
                String.class, String.class, String.class, String.class
            };

            @Override
            public Class getColumnClass(int columnIndex){return types [columnIndex];}
        });
        colorTable.setAutoscrolls(false);
        colorTable.setCellSelectionEnabled(false);
        colorTable.setShowHorizontalLines(false);
        colorTable.setShowVerticalLines(false);
        colorTable.setShowGrid(false);
        colorTable.setEnabled(false);
        colorScroll.setViewportView(colorTable);
        simulationPanel.add(colorScroll,"span, w 260px, gapleft 235px");
        
        // Enable the color colum edition
        // ~> Oh dear this annoys me
        
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
        arduinoImg.setIcon(new ImageIcon(getClass().getResource("/com/tatu/app/tatudemo/img/arduino_res_bg.jpg"))); // NOI18N
        arduinoImg.setText("");
        simulationPanel.add(arduinoImg, "h 360px, w 510px");
        
        // Create the simulation control
        DefaultListModel<String> consoleList = new DefaultListModel<>();
        JPanel controlPanel = new JPanel(new MigLayout());
        JScrollPane consoleArea = new JScrollPane();
        JList<String> consoleLog = new JList<>(consoleList);
        JButton startButton, stopButton, changeButton;
        
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
        stopButton = new JButton("Stop");
        controlPanel.add(startButton,buttonConfig);
        controlPanel.add(stopButton,"span, gap 0px 10px 5px 5px, h 50px, w 150px");
        
        // Populate all the window
        contentPane.add(simulationPanel, "north, gap 10px 10px 10px 10px, h 400px, w 676px");
        contentPane.add(controlPanel, "south, h 150px, w 680px");
    }
}
