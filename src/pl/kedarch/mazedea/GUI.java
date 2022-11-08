package pl.kedarch.mazedea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


class MouseInput extends MouseAdapter {
    MapController control;
    GUI gui;
    MouseInput(MapController control, GUI gui) {
        this.control = control;
        this.gui = gui;
    }
    public void mouseClicked(MouseEvent e) {
        int x = (int)Math.ceil(e.getX() / 64);
        int y = (int)Math.ceil(e.getY() / 64);
        if (x < 16) {
            Map map = this.control.getMap();
            Integer coords[] = map.getPlayer().getCoords();
            int dx = x - coords[0];
            int dy = y - coords[1];
            String out = "";
            try {
                if (dx == 0 && dy == -1) {
                    out = this.control.handleInput("w");
                } else if (dx == 0 && dy == 1) {
                    out = this.control.handleInput("s");
                } else if (dx == -1 && dy == 0) {
                    out = this.control.handleInput("a");
                } else if (dx == 1 && dy == 0) {
                    out = this.control.handleInput("d");
                } else {
                    out = this.control.handleInput(out);
                }
                this.gui.repaint();
            } catch (Exception ex) {
            }
        }
    }
}

class GUI extends JFrame {
    MapController control;
    Map map;
    ArrayList<BufferedImage> images;
    ArrayList<BufferedImage> imagesToggled;

    GUI (MapController control) throws Exception {
        //ClassLoader loader = new Main().getClass().getClassLoader();
        this.setSize(new Dimension(1280,1024));
        this.control = control;
        this.control.handleInput("m tutorial");
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
        this.imagesToggled.add(ImageIO.read(new URL(resDirImage+new Door().getImage())));
        this.images.add(ImageIO.read(new URL(resDirImage+new Key().getImage())));
        this.images.add(ImageIO.read(new URL(resDirImage+new Exit().getImage())));
        this.images.add(ImageIO.read(new URL(resDirImage+new Player().getImage())));
        this.addMouseListener(new MouseInput(this.control, this));
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 1280, 1024);
        map = this.control.getMap();
        ArrayList<ArrayList<MapElement>> elements = map.getElemTypes();
        MapElement element;
        for (int i = 0; i < elements.size(); i++) {
            for (int j = 0; j < elements.get(0).size(); j++) {
                element = elements.get(i).get(j);
                if (new Floor().getClass().isInstance(element)) {
                    g2.drawImage(this.images.get(0), j*64, i*64, null);
                } else if (new Wall().getClass().isInstance(element)) {
                    g2.drawImage(this.images.get(1), j*64, i*64, null);
                } else if (new Gate().getClass().isInstance(element)) {
                    if (element.isToggled()) {
                        g2.drawImage(this.images.get(2), j*64, i*64, null);
                    } else {
                        g2.drawImage(this.imagesToggled.get(0), j*64, i*64, null);
                    }
                } else if (new Level().getClass().isInstance(element)) {
                    if (element.isToggled()) {
                        g2.drawImage(this.images.get(3), j*64, i*64, null);
                    } else {
                        g2.drawImage(this.imagesToggled.get(1), j*64, i*64, null);
                    }
                } else if (new Door().getClass().isInstance(element)) {
                    if (element.isToggled()) {
                        g2.drawImage(this.images.get(4), j*64, i*64, null);
                    } else {
                        g2.drawImage(this.imagesToggled.get(2), j*64, i*64, null);
                    }
                } else if (new Key().getClass().isInstance(element)) {
                    g2.drawImage(this.images.get(5), j*64, i*64, null);
                } else if (new Exit().getClass().isInstance(element)) {
                    g2.drawImage(this.images.get(6), j*64, i*64, null);
                }
                if (map.getPlayer().getCoords()[0] == j && map.getPlayer().getCoords()[1] == i) {
                    g2.drawImage(this.images.get(7), j*64, i*64, null);
                }
            }
        }
        g2.setColor(Color.BLACK);
        g2.fillRect(1024,0,2,1024);
    }
}
