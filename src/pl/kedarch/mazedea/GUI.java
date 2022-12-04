package pl.kedarch.mazedea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Adapter for click events on painted panel
 */
class MouseInput extends MouseAdapter {
    /**
     * @see pl.kedarch.mazedea.GUI
     */
    GUI gui;
    /**
     * @param gui to control
     */
    MouseInput(GUI gui) {
        this.gui = gui;
    }
    /* (non-Javadoc)
     * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
     */
    public void mouseClicked(MouseEvent e) {
        int x = Double.valueOf(Math.ceil(e.getX() / 64)).intValue();
        int y = Double.valueOf(Math.ceil(e.getY() / 64)).intValue();
        if (x < 16) {
            Map map = this.gui.control.getMap();
            if (map != null) {
                Integer coords[] = map.getPlayer().getCoords();
                int dx = x - coords[0];
                int dy = y - coords[1];
                String out = "";
                try {
                    if (dx == 0 && dy == -1) {
                        out = this.gui.control.handleInput("w");
                    } else if (dx == 0 && dy == 1) {
                        out = this.gui.control.handleInput("s");
                    } else if (dx == -1 && dy == 0) {
                        out = this.gui.control.handleInput("a");
                    } else if (dx == 1 && dy == 0) {
                        out = this.gui.control.handleInput("d");
                    } else {
                        out = this.gui.control.handleInput(out);
                    }
                    this.gui.paintPanel.repaint();
                } catch (Exception ex) {
                }
            }
        this.gui.paintPanel.requestFocusInWindow();
        }
    }
}

/**
 * Adapter for key events on painted panel
 */
class KeyInput extends KeyAdapter {
     /**
     * @see pl.kedarch.mazedea.GUI
     */
    GUI gui;
    /**
     * @param gui to control
     */
    KeyInput(GUI gui) {
        this.gui = gui;
    }
    /* (non-Javadoc)
     * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
     */
    public void keyTyped(KeyEvent e) {
        String out = "";
        try {
            if (e.getKeyChar() == 'w') {
                out = this.gui.control.handleInput("w");
            } else if (e.getKeyChar() == 's') {
                out = this.gui.control.handleInput("s");
            } else if (e.getKeyChar() == 'a') {
                out = this.gui.control.handleInput("a");
            } else if (e.getKeyChar() == 'd') {
                out = this.gui.control.handleInput("d");
            } else {
                out = this.gui.control.handleInput(out);
            }
            this.gui.updateGameInfo();
            this.gui.paintPanel.repaint();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        this.gui.paintPanel.requestFocusInWindow();
    }
}

/**
 * Listener for select button
 */
class BtnSelect implements ActionListener {
     /**
     * @see pl.kedarch.mazedea.GUI
     */
    GUI gui;
    /**
     * @param gui to control
     */
    BtnSelect(GUI gui) {
        this.gui = gui;
    }
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        String mapName = String.valueOf(this.gui.mapSelection.getSelectedItem());
        try {
            this.gui.control.handleInput("m "+mapName);
            this.gui.updateGameInfo();
            this.gui.paintPanel.repaint();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        this.gui.paintPanel.requestFocusInWindow();
    }
}

/**
 * Listener for restart button
 */
class BtnRestart implements ActionListener {
     /**
     * @see pl.kedarch.mazedea.GUI
     */
    GUI gui;
    /**
     * @param gui to control
     */
    BtnRestart(GUI gui) {
        this.gui = gui;
    }
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        try {
            this.gui.control.handleInput("r");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        this.gui.updateGameInfo();
        this.gui.paintPanel.repaint();
        this.gui.paintPanel.requestFocusInWindow();
    }
}

/**
 * Listener for reload maps button
 */
class BtnReloadMaps implements ActionListener {
     /**
     * @see pl.kedarch.mazedea.GUI
     */
    GUI gui;
    /**
     * @param gui to control
     */
    BtnReloadMaps(GUI gui) {
        this.gui = gui;
    }
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        try {
            this.gui.control.handleInput("e");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        this.gui.mapSelection.removeAllItems();
        this.gui.mapSelection.setModel(new DefaultComboBoxModel(this.gui.control.getMapLoader().getMapNames().toArray()));
        this.gui.paintPanel.requestFocusInWindow();
    }
}

/**
 * Listener for quit button
 */
class BtnQuit implements ActionListener {
     /**
     * @see pl.kedarch.mazedea.GUI
     */
    GUI gui;
    /**
     * @param gui to control
     */
    BtnQuit(GUI gui) {
        this.gui = gui;
    }
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}

/**
 * Class for all app buttons
 */
class AppJButton extends JButton {
    /**
     * @param text String to display
     */
    AppJButton(String text) {
        super(text);
        this.setFocusable(false);
    }
}

/**
 * Game's paintable panel
 */
class PaintPanel extends JPanel {
     /**
     * @see pl.kedarch.mazedea.GUI
     */
    GUI gui;
    /**
     * @param gui to control
     */
    PaintPanel(GUI gui) {
        this.gui = gui;
    }
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        Map map = this.gui.control.getMap();
        if (map != null) {
            ArrayList<ArrayList<MapElement>> elements = map.getElemTypes();
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, 1152, 1024);
            MapElement element;
            for (int i = 0; i < elements.size(); i++) {
                for (int j = 0; j < elements.get(0).size(); j++) {
                    element = elements.get(i).get(j);
                    if (new Floor().getClass().isInstance(element)) {
                        g2.drawImage(this.gui.images.get(0), j*64, i*64, null);
                    } else if (new Wall().getClass().isInstance(element)) {
                        g2.drawImage(this.gui.images.get(1), j*64, i*64, null);
                    } else if (new Gate().getClass().isInstance(element)) {
                        if (element.isToggled()) {
                            g2.drawImage(this.gui.imagesToggled.get(0), j*64, i*64, null);
                        } else {
                            g2.drawImage(this.gui.images.get(2), j*64, i*64, null);
                        }
                    } else if (new Lever().getClass().isInstance(element)) {
                        if (element.isToggled()) {
                            g2.drawImage(this.gui.imagesToggled.get(1), j*64, i*64, null);
                        } else {
                            g2.drawImage(this.gui.images.get(3), j*64, i*64, null);
                        }
                    } else if (new Door().getClass().isInstance(element)) {
                        if (element.isToggled()) {
                            g2.drawImage(this.gui.imagesToggled.get(2), j*64, i*64, null);
                        } else {
                            g2.drawImage(this.gui.images.get(4), j*64, i*64, null);
                        }
                    } else if (new Key().getClass().isInstance(element)) {
                        g2.drawImage(this.gui.images.get(5), j*64, i*64, null);
                    } else if (new Exit().getClass().isInstance(element)) {
                        g2.drawImage(this.gui.images.get(6), j*64, i*64, null);
                    }
                    if (map.getPlayer().getCoords()[0] == j && map.getPlayer().getCoords()[1] == i) {
                        g2.drawImage(this.gui.images.get(7), j*64, i*64, null);
                    }
                }
            }
        }
        g2.setColor(new Color(120,120,120));
        g2.fillRect(1152,0,2,1024);
    }

}

/**
 * Main window
 */
class GUI extends JFrame {
    /**
     * @see pl.kedarch.mazedea.MapController
     */
    MapController control;
    /**
     * Images list
     */
    ArrayList<BufferedImage> images;
    /**
     * Images of toggled state for supported elements
     */
    ArrayList<BufferedImage> imagesToggled;
    /**
     * Panel containing all JObjects
     */
    JPanel panel;
    /**
     * Main paintable panel
     */
    JPanel paintPanel;
    /**
     * Information area
     */
    JTextArea infoArea;
    /**
     * Map list
     */
    JComboBox mapSelection;
    /**
     * Map selection button
     */
    AppJButton mapSelectionButton;
    /**
     * Restart button
     */
    AppJButton restartButton;
    /**
     * Reload maps button
     */
    AppJButton reloadMapsButton;
    /**
     * Quit button
     */
    AppJButton quitButton;
    /**
     * Contains info about controls
     */
    JTextArea controlsInfoArea;

    /**
     * Initializes GUI
     * @param name for window
     * @param control MapController object
     * @throws Exception if programmer forgot something
     */
    GUI (String name, MapController control) throws Exception {
        super(name);
        this.setSize(new Dimension(1280,1024));
        this.control = control;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String resDirImage = this.control.getMapLoader().getResDir()+"images/";
        this.images = new ArrayList<BufferedImage>();
        this.imagesToggled = new ArrayList<BufferedImage>();
        this.images.add(ImageIO.read(new URL(resDirImage+new Floor().getImage())));
        this.images.add(ImageIO.read(new URL(resDirImage+new Wall().getImage())));
        this.images.add(ImageIO.read(new URL(resDirImage+new Gate().getImage())));
        this.imagesToggled.add(ImageIO.read(new URL(resDirImage+new Gate().getImageToggled())));
        this.images.add(ImageIO.read(new URL(resDirImage+new Lever().getImage())));
        this.imagesToggled.add(ImageIO.read(new URL(resDirImage+new Lever().getImageToggled())));
        this.images.add(ImageIO.read(new URL(resDirImage+new Door().getImage())));
        this.imagesToggled.add(ImageIO.read(new URL(resDirImage+new Door().getImageToggled())));
        this.images.add(ImageIO.read(new URL(resDirImage+new Key().getImage())));
        this.images.add(ImageIO.read(new URL(resDirImage+new Exit().getImage())));
        this.images.add(ImageIO.read(new URL(resDirImage+new Player().getImage())));
        this.setResizable(false);
        this.setLayout(null);
        panel = new JPanel();
        panel.setSize(254, 1024);
        panel.setLocation(1154,0);
        panel.setLayout(null);
        infoArea = new JTextArea();
        infoArea.setText("Current map:\nNone");
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);
        infoArea.setBounds(0, 0, 126, 128);
        infoArea.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        infoArea.setForeground(Color.darkGray);
        infoArea.setBackground(new Color(120,180,60));
        infoArea.setBorder(BorderFactory.createLineBorder(new Color(0,100,0),1));
        infoArea.setOpaque(true);
        infoArea.setFocusable(false);
        mapSelection = new JComboBox(this.control.getMapLoader().getMapNames().toArray());
        mapSelection.setBounds(0, 128, 126, 30);
        mapSelection.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        mapSelection.setForeground(Color.darkGray);
        mapSelection.setBackground(new Color(120,200,60));
        mapSelection.setBorder(BorderFactory.createLineBorder(new Color(0,100,0),1));
        mapSelection.setSelectedIndex(0);
        mapSelection.setFocusable(false);
        mapSelectionButton = new AppJButton("<html>Load map<br>selected above");
        mapSelectionButton.setBounds(0, 158, 126, 50);
        mapSelectionButton.setFont(new Font("Comic Sans", Font.PLAIN, 13));
        mapSelectionButton.setForeground(Color.darkGray);
        mapSelectionButton.setBackground(new Color(120,200,60));
        mapSelectionButton.setBorder(BorderFactory.createLineBorder(new Color(0,100,0),1));
        mapSelectionButton.addActionListener(new BtnSelect(this));
        restartButton = new AppJButton("Restart map");
        restartButton.setBounds(0, 208, 126, 30);
        restartButton.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        restartButton.setForeground(Color.darkGray);
        restartButton.setBackground(new Color(120,200,60));
        restartButton.setBorder(BorderFactory.createLineBorder(new Color(0,100,0),1));
        restartButton.addActionListener(new BtnRestart(this));
        reloadMapsButton = new AppJButton("Reload maps");
        reloadMapsButton.setBounds(0, 238, 126, 30);
        reloadMapsButton.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        reloadMapsButton.setForeground(Color.darkGray);
        reloadMapsButton.setBackground(new Color(120,200,60));
        reloadMapsButton.setBorder(BorderFactory.createLineBorder(new Color(0,100,0),1));
        reloadMapsButton.addActionListener(new BtnReloadMaps(this));
        quitButton = new AppJButton("Quit");
        quitButton.setBounds(0, 268, 126, 30);
        quitButton.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        quitButton.setForeground(Color.darkGray);
        quitButton.setBackground(new Color(120,200,60));
        quitButton.setBorder(BorderFactory.createLineBorder(new Color(0,100,0),1));
        quitButton.addActionListener(new BtnQuit(this));
        controlsInfoArea = new JTextArea();
        String controlsText = "Welcome to Mazedea!\n\nYour objective is to reach green square.\n\nOn the top right side game status is displayed.\n\nOn the map there are Keys, which can open Doors and Levers which toggle Gates (sometimes more than one of them).\n\nTo move character you can click on adjacent squares or use:\nw - to go up\ns - to go down\na - to go left\nd - to go right\nGood luck!\n\nCreated by Radosław Woźniak";
        controlsInfoArea.setText(controlsText);
        controlsInfoArea.setLineWrap(true);
        controlsInfoArea.setWrapStyleWord(true);
        controlsInfoArea.setEditable(false);
        controlsInfoArea.setBounds(0, 298, 126, 726);
        controlsInfoArea.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        controlsInfoArea.setForeground(Color.darkGray);
        controlsInfoArea.setBackground(new Color(120,180,60));
        controlsInfoArea.setBorder(BorderFactory.createLineBorder(new Color(0,100,0),1));
        controlsInfoArea.setOpaque(true);
        controlsInfoArea.setFocusable(false);
        panel.add(infoArea);
        panel.add(mapSelectionButton);
        panel.add(restartButton);
        panel.add(reloadMapsButton);
        panel.add(quitButton);
        panel.add(mapSelection);
        panel.add(controlsInfoArea);
        paintPanel = new PaintPanel(this);
        paintPanel.setSize(1154, 1024);
        paintPanel.setLocation(0,0);
        paintPanel.setFocusable(true);
        paintPanel.addMouseListener(new MouseInput(this));
        paintPanel.addKeyListener(new KeyInput(this));
        paintPanel.setRequestFocusEnabled(true);
        this.add(panel);
        this.add(paintPanel);
        this.setVisible(true);
        System.err.println(this.control.handleInput("m tutorial"));
        this.updateGameInfo();
        this.paintPanel.repaint();
        this.paintPanel.requestFocusInWindow();
    }

    /**
     * Updates infoArea
     */
    void updateGameInfo() {
        if (this.control.getMap() == null)
            return;
        String text = "Current map:\n"+this.control.getMap().getName()+"\n";
        String title = "Mazedea - "+this.control.getMap().getName();
        int keyCount = this.control.getMap().getPlayer().getKeys().size();
        text += "Moves: "+this.control.getMoves()+"\n";
        if (this.control.getMap() != null && keyCount > 0) {
            text += "You have "+keyCount+" unknown key";
            if (keyCount > 1)
                text += "s";
            text += ".\n";
        }
        if (this.control.getVictory()) {
            text += "Victory!";
            title += " - Victory!";
        }
        this.infoArea.setText(text);
        this.setTitle(title);
    }
}
