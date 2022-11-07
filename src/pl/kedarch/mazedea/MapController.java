package pl.kedarch.mazedea;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class controlling map functionality
 */
class MapController {
    /**
     * Object containing loaded maps
     */
    private MapLoader maps;
    /**
     * Loaded map object
     */
    private Map map;
    /**
     * Holds info about victory
     */
    private boolean victory;

    /**
     * Create MapLoader object
     * @throws Exception if programmer forgot something
     */
    MapController() throws Exception {
        this.maps = new MapLoader();
    }

    /**
     * Start map functionality
     * @throws Exception if map is invalid or programmer forgot something
     */
    void start(final String[] args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        if (args.length > 1) {
            if (args[0] == "-h") {
                System.out.println("Program checks only first argument\nAvailable arguments\n-h prints this message\n-t start in command line mode\n-g start in GUI mode (default)");
            } else if (args[0] == "-t") {
                System.out.println("CLI");
                this.startCLI();
            } else if (args[0] == "-g") {
                this.startGUI();
            } else {
                System.err.println("Unknown argument");
            }
        } else {
            this.startCLI();
        }
    }

    /**
     * Reload external maps from $appPath$/maps
     * @throws Exception if map is invalid or programmer forgot something
     */
    void reloadMaps() throws Exception {
        this.maps.reload();
    }

    /**
     * Return new Map object from name
     * @return map
     * @throws MapException if map file is invalid
     * @throws Exception if map is invalid or programmer forgot something
     */
    Map returnMap(String name) throws Exception {
        return this.maps.returnMap(name);
    }

    /**
     * Gets exising map
     * @return map
     */
    Map getMap() {
        return this.map;
    }

     /**
     * Sets map
     * @return map
     */
    void setMap(Map map) {
        this.map = map;
    }

    /**
     * Starts CLI version
     * @throws Exception if map is invalid or programmer forgot something
     */
    void startCLI() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String in;
        String handleOut;
        try {
            this.printCLI();
            while ((in = scanner.nextLine()) != null) {
                handleOut = this.handleInput(in, false);
                if (handleOut == null) {
                    System.out.println("Bye!");
                    break;
                } else if (handleOut != "")
                    System.out.println(handleOut);
                this.printCLI();
            }
        } catch (NoSuchElementException e) {
            scanner.close();
            return;
        } finally {
            scanner.close();
        }
    }

    /**
     * Prints CLI prompt string
     */
    void printCLI() {
        String info = "";
        if (this.getMap() != null) 
            info += "Map name: "+this.getMap().getName()+"\n";
        info += "Available options: ";
        if (this.getMap() != null) 
            info += "wsad - move up,down,left,right; r - restart; ";
        info += "l - list available maps; m - load map; e - reload external maps; q - quit\n> ";
        System.out.print(info);
    }

    /**
     * Starts GUI version
     * @throws Exception if map is invalid or programmer forgot something
     */
    void startGUI() throws Exception {

    }

    /**
     * Handles available input
     * @param in input string
     * @param isGUI
     * @return message
     * @throws Exception if map is invalid or programmer forgot something
     */
    String handleInput(String in, boolean isGUI) throws Exception {
        String arg;
        String split[];
        Map map;
        if (this.getMap() != null && !this.victory) {
            if (in.equals("w")) {
                if (!this.moveLogic("w"))
                    return "Invalid move";
                return "";
            } else if (in.equals("s")) {
                if (!this.moveLogic("s"))
                    return "Invalid move";
                return "";
            } else if (in.equals("a")) {
                if (!this.moveLogic("a"))
                    return "Invalid move";
                return "";
            } else if (in.equals("d")) {
                if (!this.moveLogic("d"))
                    return "Invalid move";
                return "";
            }
        } else if (in.equals("r") && this.getMap() != null) {
            try {
                map = this.getMap();
                this.setMap(this.returnMap(map.getName()));
                return "Map reloaded";
            } catch (MapException e) {
                return e.toString();
            }
        } else if (in.equals("l")) {
            String mapList = "Map list:\n";
            for (String mapName : this.maps.getMapNames()) {
                mapList += mapName + "\n";
            }
            return mapList.substring(0, mapList.length()-1);
        } else if (in.startsWith("m ")) {
            split = in.split(" ");
            arg = split[1];
            try {
                map = this.returnMap(arg);
            } catch (MapException e) {
                return e.toString();
            }
            if (map != null) {
                this.setMap(map);
                return "New map set";
            }
            return "Invalid map name";
        } else if (in.equals("e")) {
            this.reloadMaps();
            return "External maps reloaded";
        } else if (in.equals("q")) {
            return null;
        }
        return "Unknown option";
    }

    /**
     * Check if move is possible
     * @param direction
     * @return success
     * @throws Exception if map is invalid or programmer forgot something
     */
    boolean moveLogic(String direction) throws Exception {
        Map map = this.getMap();
        // variables below are for setting explicitly
        ArrayList<ArrayList<MapElement>> elements = map.getElemTypes();
        ArrayList<MapElement> elementsLine;
        ArrayList<MapElement> toggledElementLine;
        ArrayList<Key> keys;
        MapElement element;
        MapElement toggledElement;
        Player player = map.getPlayer();
        Integer coords[] = player.getCoords();
        Integer move[] = new Integer[2];
        switch (direction) {
            case "w":
                move[0] = 0;
                move[1] = 1;
                break;
            case "s":
                move[0] = 0;
                move[1] = -1;
                break;
            case "a":
                move[0] = -1;
                move[1] = 0;
                break;
            case "d":
                move[0] = 1;
                move[1] = 0;
                break;
            default:
                return false;
        }
        elementsLine = elements.get(coords[1]+move[1]);
        element = elementsLine.get(coords[0]+move[0]);
        if (new Floor().getClass().isInstance(element)) {
            coords[0] += move[0];
            coords[1] += move[1];
        } else if (new Wall().getClass().isInstance(element)) {
            return false;
        } else if (new Exit().getClass().isInstance(element)) {
            map.victory = true;
            this.setMap(map);
        } else if (new Gate().getClass().isInstance(element) || new Door().getClass().isInstance(element)) {
            if (elements.get(coords[1].get(coords[0]).isOpened())) {
                coords[0] += move[0];
                coords[1] += move[1];
            } else {
                return false;
            }
        } else if (new Level().getClass().isInstance(element)) {
            coords[0] += move[0];
            coords[1] += move[1];
            element.toggle();
            for (int i = 0; i < elements.size(); i++) {
                for (int j = 0; j < elementsLine.size(); j++) {
                    toggledElementLine = elements.get(i);
                    toggledElement = toggledElementLine.get(j);
                    if (new Gate().getClass().isInstance(toggledElement)) {
                        toggledElement.toggle();
                    }
                    toggledElementLine.set(j, toggledElement);
                    elements.set(i, toggledElementLine);
                }
            }
        } else if (new Key().getClass().isInstance(element)) {
            coords[0] += move[0];
            coords[1] += move[1];
            keys.add(element);
            element = new Floor();
        }
        elementsLine.set(coords[0], element);
        elements.set(coords[1], elementsLine);
        player.setCoords(coords);
        map.setElemTypes(elements);
        map.setPlayer(player);
        this.setMap(map);
        return true;
    }
}
