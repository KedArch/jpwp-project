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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

class MouseInput extends MouseAdapter {
    GUI gui;
    MouseInput(GUI gui) {
        this.gui = gui;
    }
    public void mouseClicked(MouseEvent e) {
        int x = (int)Math.ceil(e.getX() / 64);
        int y = (int)Math.ceil(e.getY() / 64);
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
                    this.gui.rePaint();
                } catch (Exception ex) {
                }
            }
        this.gui.paintPanel.requestFocusInWindow();
        }
    }
}

class KeyInput extends KeyAdapter {
    GUI gui;
    KeyInput(GUI gui) {
        this.gui = gui;
    }
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
            this.gui.rePaint();
        } catch (Exception ex) {
        }
        this.gui.paintPanel.requestFocusInWindow();
    }
}

class BtnSelect implements ActionListener {
    GUI gui;
    BtnSelect(GUI gui) {
        this.gui = gui;
    }
    public void actionPerformed(ActionEvent e) {
        String mapName = String.valueOf(this.gui.mapSelection.getSelectedItem());
        try {
            this.gui.control.handleInput("m "+mapName);
            this.gui.updateGameInfo();
            this.gui.rePaint();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}

class AppJButton extends JButton {
    AppJButton(String text) {
        super(text);
        this.setFocusable(false);
    }
}

class PaintPanel extends JPanel {
    GUI gui;
    PaintPanel(GUI gui) {
        this.gui = gui;
    }
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
                            g2.drawImage(this.gui.images.get(2), j*64, i*64, null);
                        } else {
                            g2.drawImage(this.gui.imagesToggled.get(0), j*64, i*64, null);
                        }
                    } else if (new Level().getClass().isInstance(element)) {
                        if (element.isToggled()) {
                            g2.drawImage(this.gui.images.get(3), j*64, i*64, null);
                        } else {
                            g2.drawImage(this.gui.imagesToggled.get(1), j*64, i*64, null);
                        }
                    } else if (new Door().getClass().isInstance(element)) {
                        if (element.isToggled()) {
                            g2.drawImage(this.gui.images.get(4), j*64, i*64, null);
                        } else {
                            g2.drawImage(this.gui.imagesToggled.get(2), j*64, i*64, null);
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

class GUI extends JFrame {
    MapController control;
    ArrayList<BufferedImage> images;
    ArrayList<BufferedImage> imagesToggled;
    JPanel panel;
    JPanel paintPanel;
    JTextArea infoArea;
    JComboBox mapSelection;
    AppJButton mapSelectionButton;
    AppJButton brestart;
    AppJButton breloadem;
    AppJButton bquit;

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
        this.images.add(ImageIO.read(new URL(resDirImage+new Level().getImage())));
        this.imagesToggled.add(ImageIO.read(new URL(resDirImage+new Level().getImageToggled())));
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
        infoArea.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        infoArea.setBounds(0, 0, 126, 128);
        infoArea.setForeground(Color.darkGray);
        infoArea.setBackground(new Color(120,160,60));
        infoArea.setOpaque(true);
        infoArea.setFocusable(false);
        mapSelection = new JComboBox(this.control.getMapLoader().getMapNames().toArray());
        mapSelection.setBounds(0, 128, 128, 30);
        mapSelection.setSelectedIndex(0);
        mapSelection.setFocusable(true);
        mapSelectionButton = new AppJButton("<html>Load map<br>selected above");
        mapSelectionButton.setBounds(0, 158, 126, 40);
        mapSelectionButton.addActionListener(new BtnSelect(this));
        brestart = new AppJButton("Restart map");
        brestart.setBounds(0, 198, 126, 30);
        breloadem = new AppJButton("<html>Reload<br>external maps");
        breloadem.setBounds(0, 228, 126, 40);
        bquit = new AppJButton("Quit");
        bquit.setBounds(0, 268, 126, 30);
        panel.add(infoArea);
        panel.add(mapSelectionButton);
        panel.add(brestart);
        panel.add(breloadem);
        panel.add(bquit);
        panel.add(mapSelection);
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
    }

    void rePaint() {
        super.repaint();
        this.panel.requestFocus();
    }

    void updateGameInfo() {
        String text = "Current map:\n"+this.control.getMap().getName()+"\n";
        int keyCount = this.control.getMap().getPlayer().getKeys().size();
        if (this.control.getMap() != null && keyCount > 0) {
            text += "You have "+keyCount+" unknown key";
            if (keyCount > 1)
                text += "s.\n";
            else
                text += ".\n";
        }
        if (this.control.getVictory())
            text += "\nVictory!";
        this.infoArea.setText(text);
    }
}
