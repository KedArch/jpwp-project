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
     * Holds info about user moves count
     */
    private int moves;

    /**
     * Create MapLoader object
     * @throws Exception if programmer forgot something
     */
    MapController() throws Exception {
        this.maps = new MapLoader();
    }

    /**
     * Start map functionality
     * @param args arguments from command line
     * @throws Exception if map is invalid or programmer forgot something
     */
    void start(final String[] args) throws Exception {
        if (args.length > 0) {
            if (args[0].equals("-h")) {
                System.out.println("Program checks only first argument\nAvailable arguments\n-h prints this message\n-t start in command line mode\n-g start in GUI mode (default)");
            } else if (args[0].equals("-t")) {
                this.startCLI();
            } else if (args[0].equals("-g")) {
                this.startGUI();
            } else {
                System.err.println("Unknown argument");
            }
        } else {
            this.startGUI();
        }
    }

    /**
     * Reload maps from $appPath$/maps
     * @throws Exception if map is invalid or programmer forgot something
     */
    void reloadMaps() throws Exception {
        this.maps.reload();
    }

    /**
     * Return new Map object from name
     * @param name string
     * @return map Map
     * @throws MapException if map file is invalid
     * @throws Exception if map is invalid or programmer forgot something
     */
    Map returnMap(String name) throws Exception {
        return this.maps.returnMap(name);
    }

    /**
     * Gets existing map
     * @return map Map
     */
    Map getMap() {
        return this.map;
    }

     /**
     * Sets map
     * @param map Map
     */
    void setMap(Map map) {
        this.map = map;
    }

    /**
     * Gets information about victory
     * @return victory boolean
     */
    boolean getVictory() {
        return this.victory;
    }


    /**
     * Gets information about user move count
     * @return moves int
     */
    int getMoves() {
        return this.moves;
    }

    /**
     * @return mapList ArrayList
     * @see pl.kedarch.mazedea.MapLoader#getMapNames()
     */
    MapLoader getMapLoader() {
        return this.maps;
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
                handleOut = this.handleInput(in);
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
        if (this.map != null) {
            ArrayList<ArrayList<MapElement>> elements = this.map.getElemTypes();
            ArrayList<MapElement> elementsLine;
            MapElement element;
            for (int i = 0; i < elements.size()+2; i++) {
                System.out.print("+");
            }
            System.out.println();
            for (int i = 0; i < elements.size(); i++) {
                elementsLine = elements.get(i);
                System.out.print("+");
                for (int j = 0; j < elementsLine.size(); j++) {
                    element = elementsLine.get(j);
                    if (this.map.getPlayer().getCoords()[0] == j && this.map.getPlayer().getCoords()[1] == i) {
                        System.out.print(new Player().getTermRepresentation().toUpperCase());
                        continue;
                    }
                    if (element.isToggled()) {
                        System.out.print(element.getTermRepresentation().toLowerCase());
                    } else {
                        System.out.print(element.getTermRepresentation().toUpperCase());
                    }
                }
                System.out.print("+");
                System.out.println();
            }
            for (int i = 0; i < elements.size()+2; i++) {
                System.out.print("+");
            }
            System.out.println();
        }
        String info = "";
        if (this.map != null && this.map.getPlayer().getKeys().size() > 0) {
            info = "You have "+this.map.getPlayer().getKeys().size()+" unknown key";
            if (this.map.getPlayer().getKeys().size() > 1)
                info += "s.\n";
            else
                info += ".\n";
        }
        if (this.victory)
            info += "Victory!\n";
        if (this.map != null)
            info += "Map name: "+this.map.getName()+"\n";
        info += "Available options: ";
        if (this.map != null && !this.victory)
            info += "wsad - move up,down,left,right; ";
        if (this.map != null)
            info += "r - restart; ";
        info += "l - list available maps; m - load map; e - reload maps; q - quit\n> ";
        System.out.print(info);
    }

    /**
     * Starts GUI version
     * @throws Exception if map is invalid or programmer forgot something
     */
    void startGUI() throws Exception {
        new GUI("Mazedea", this);
    }

    /**
     * Handles available input
     * @param in input string
     * @return message
     * @throws Exception if map is invalid or programmer forgot something
     */
    String handleInput(String in) throws Exception {
        String arg;
        String split[];
        if (this.map != null && !this.victory) {
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
        }
        if (in.equals("r") && this.map != null) {
            try {
                this.setMap(this.returnMap(this.map.getName()));
                this.victory = false;
                this.moves = 0;
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
                this.victory = false;
                this.moves = 0;
                return "New map set";
            }
            return "Invalid map name";
        } else if (in.equals("e")) {
            this.reloadMaps();
            return "Maps reloaded";
        } else if (in.equals("q")) {
            return null;
        }
        return "Unknown option";
    }

    /**
     * Check if move is possible
     * @param direction array of 2 integers
     * @return success boolean
     * @throws Exception if map is invalid or programmer forgot something
     */
    boolean moveLogic(String direction) throws Exception {
        Map map = this.map;
        // variables below are for setting explicitly
        ArrayList<ArrayList<MapElement>> elements = map.getElemTypes();
        ArrayList<MapElement> elementsLine;
        ArrayList<MapElement> toggledElementLine;
        MapElement element;
        MapElement toggledElement;
        Player player = map.getPlayer();
        ArrayList<MapElement> keys = player.getKeys();
        Integer coords[] = player.getCoords();
        Integer move[] = new Integer[2];
        boolean found = false;
        switch (direction) {
            case "w":
                move[0] = 0;
                move[1] = -1;
                break;
            case "s":
                move[0] = 0;
                move[1] = 1;
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
            this.victory = true;
            this.setMap(map);
            coords[0] += move[0];
            coords[1] += move[1];
        } else if (new Gate().getClass().isInstance(element)) {
            if (element.isToggled()) {
                coords[0] += move[0];
                coords[1] += move[1];
            } else {
                return false;
            }
        } else if (new Lever().getClass().isInstance(element)) {
            coords[0] += move[0];
            coords[1] += move[1];
            element.toggle();
            for (int i = 0; i < elements.size(); i++) {
                for (int j = 0; j < elementsLine.size(); j++) {
                    toggledElementLine = elements.get(i);
                    toggledElement = toggledElementLine.get(j);
                    if (new Gate().getClass().isInstance(toggledElement)) {
                        found = false;
                        for (Integer x : toggledElement.getLink()) {
                            for (Integer y : element.getLink()) {
                                if (x == y) {
                                    found = true;
                                    toggledElement.toggle();
                                    break;
                                }
                            }
                            if (found)
                                break;
                        }
                    }
                    toggledElementLine.set(j, toggledElement);
                    elements.set(i, toggledElementLine);
                }
            }
        } else if (new Door().getClass().isInstance(element)) {
            if (element.isToggled()) {
                coords[0] += move[0];
                coords[1] += move[1];
            } else if (element.getLink().size() > 0) {
                for (MapElement key : keys) {
                    found = false;
                    for (Integer x : key.getLink()) {
                        for (Integer y : element.getLink()) {
                            if (x == y) {
                                found = true;
                                if (!element.isToggled())
                                        element.toggle();
                                coords[0] += move[0];
                                coords[1] += move[1];
                                break;
                            }
                        }
                        if (found)
                            break;
                    }
                }
                if (!element.isToggled()) {
                    return false;
                }
            } else {
                return false;
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
        this.moves++;
        return true;
    }
}
