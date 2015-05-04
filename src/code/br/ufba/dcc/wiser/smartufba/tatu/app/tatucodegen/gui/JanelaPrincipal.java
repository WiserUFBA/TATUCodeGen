package br.ufba.dcc.wiser.smartufba.tatu.app.tatucodegen.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import net.miginfocom.swing.MigLayout;

/**
 * This is the default Frame for the demo application
 * @author jeferson
 */
public class JanelaPrincipal extends JFrame {
    private final String default_path_img = "/br/ufba/dcc/wiser/smartufba/tatu/app/tatucodegen/img/";
    private final JPanel contentPane;
    private final JTable colorTable;
    private final AboutDialog about_dialog;
    private JMenuBar menuBar;
    public static Color[] digital_pin_color = new Color[14];
    public static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
   
    private static class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {
        public SimpleHeaderRenderer() {
            setFont(new Font("Consolas", Font.BOLD, 14));
            setForeground(Color.BLACK);
            setBorder(BorderFactory.createEtchedBorder());
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            return this;
        }
    }
    
    private static class RowHeaderRenderer extends DefaultTableCellRenderer {
        public RowHeaderRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            if (table != null) {
                JTableHeader header = table.getTableHeader();

                if (header != null) {
                    setForeground(header.getForeground());
                    setBackground(header.getBackground());
                    setFont(header.getFont());
                }
            }

            if (isSelected) {
                setFont(getFont().deriveFont(Font.BOLD));
            }

            setValue(value);
            return this;
        }
    }
    
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
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                about_dialog.setVisible(true);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);
    }
    
    public JanelaPrincipal(){
        // General Options
        this.setTitle("TATU Code Generator App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(getClass().getResource( default_path_img +
                                                                "icon.gif")).getImage());
        this.setBounds(((screen.width-750)/2), ((screen.height-600)/2), 750, 600);
        this.setResizable(false);
        
        // Initialize some vars
        for(int i = 0; i < 14; i++) digital_pin_color[i] = Color.WHITE;
        about_dialog = new AboutDialog(this, "TATU Code Gen V-1.0");
        
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
        DefaultTableModel colorTableModel = new DefaultTableModel(new Object [][]
            {{null, null, null, null, null, null, null, null, null, null, null, null, null, null}},
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
        
        // Pin Config
        JScrollPane configScroll  = new JScrollPane();
        JTable configTable = new JTable();
        configTable.setModel(new DefaultTableModel(
            new Object [][] {
                { 0, null, null, null},
                { 1, null, null, null},
                { 2, null, null, null},
                { 3, null, null, null},
                { 4, null, null, null},
                { 5, null, null, null},
                { 6, null, null, null},
                { 7, null, null, null},
                { 8, null, null, null},
                { 9, null, null, null},
                { 10, null, null, null},
                { 11, null, null, null},
                { 12, null, null, null},
                { 13, null, null, null}
            },
            new String [] { "Pin", "Cor", "Name", "Valor" }){
                Class[] types = new Class [] { Integer.class, String.class,
                                                String.class, String.class };
                boolean[] canEdit = new boolean[] { false, true, true, true };

            @Override
            public Class getColumnClass(int columnIndex) { return types [columnIndex]; }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        configScroll.setViewportView(configTable);
        configTable.getColumnModel().getColumn(0).setCellRenderer(new RowHeaderRenderer());
        configTable.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        configTable.getTableHeader().setEnabled(false);
        configTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        configTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        configTable.getColumnModel().getColumn(1).setPreferredWidth(55);
        configTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        configTable.getColumnModel().getColumn(3).setPreferredWidth(51);
        JComboBox colorBox = new JComboBox(new String[]{"Branco", "Preto", "Amarelo", "Vermelho",
                                            "Verde", "Azul", "Laranja", "Rosa", "Cinza"});
        configTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(colorBox));
        simulationPanel.add(configScroll, "h 275px");
        
        
        // Create the simulation control
        DefaultListModel<String> consoleList = new DefaultListModel<>();
        JPanel controlPanel = new JPanel(new MigLayout());
        JScrollPane consoleArea = new JScrollPane();
        JList<String> consoleLog = new JList<>(consoleList);
        final JButton startButton, stopButton;
        
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
                /* <testing> */
                digital_pin_color[2] = Color.GREEN;
                digital_pin_color[1] = Color.BLUE;
                colorTable.repaint();
                /* </testing> */
            }
        });
        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                /* <testing> */
                digital_pin_color[3] = Color.GRAY;
                digital_pin_color[4] = Color.ORANGE;
                colorTable.repaint();
                stopButton.setText("TEST");
                /* </testing> */
            }
        });
        controlPanel.add(startButton,buttonConfig);
        controlPanel.add(stopButton,"span, gap 0px 10px 5px 5px, h 50px, w 150px");
        
        // Populate all the window
        contentPane.add(simulationPanel, "north, gap 10px 10px 10px 10px, h 400px, w 726px");
        contentPane.add(controlPanel, "south, h 150px, w 680px");
    }
}
