package pl.kedarch.mazedea;

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
     */
    void start(final String[] args) {
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
     */
    void startCLI() {
        Scanner scanner = new Scanner(System.in);
        String in;
        String handleOut;
        String info = "Available options: wsad - move up,down,left,right; r - restart; l - load map; m - reload external maps; q - quit";
        try {
            System.out.println(info);
            while ((in = scanner.nextLine()) != null) {
                handleOut = this.handleInput(in, false);
                if (handleOut == null) {
                    System.out.println("Bye!");
                    return;
                } else if (handleOut != "")
                    System.out.println(handleOut);
                System.out.println(info);
            }
        } catch (NoSuchElementException e) {
            scanner.close();
            return;
        } finally {
            scanner.close();
        }
    }

    /**
     * Starts GUI version
     */
    void startGUI() {

    }

    String handleInput(String in, boolean isGUI) {
        String arg;
        String split[];
        Map map;
        if (in.startsWith("l ")) {
            split = in.split(" ");
            arg = split[1];
            map = this.returnMap(arg);
            if (map != null) {
                this.setMap(map);
                return "New map set";
            }
            return "Invalid map name";
        }
        for (String key : in.split("")) {
            switch (key) {
                case "w":
                    if (!this.moveLogic("w"))
                        return "Invalid move";
                    return "";
                    break;
                case "s":
                    if (!this.moveLogic("s"))
                        return "Invalid move";
                    return "";
                    break;
                case "a":
                    if (!this.moveLogic("a"))
                        return "Invalid move";
                    return "";
                    break;
                case "d":
                    if (!this.moveLogic("d"))
                        return "Invalid move";
                    return "";
                    break;
                case "r":
                    map = this.getMap();
                    this.setMap(this.returnMap(map.getName()));
                    return "Map reloaded";
                    break;
                case "m":
                    this.reloadMaps();
                    return "External maps reloaded";
                    break;
                case "q":
                    scanner.close();
                    return null;
                default:
                    return "Unknown option";
            }
        }
    }

    boolean moveLogic(String direction) {
        Map map = this.getMap();
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
        return true;
    }
}
