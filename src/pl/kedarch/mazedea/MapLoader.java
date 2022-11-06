package pl.kedarch.mazedea;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Class for loading maps in $resDir$/maps with info from $resDir$/maps/list.txt and external maps from $appPath$/maps
 */
class MapLoader {
    /**
     * Location of resource directory containing default maps and images
     */
    String resDir;
    /**
     * Location of root directory of the app
     */
    String appPath;
    /**
     * Default map number used to reload only external maps
     *
     */
    int defaultMapAmount;
    /**
     * List of names used when selecting maps
     */
    ArrayList<String> mapNames;

    /**
     * Loads default and external maps
     * @throws Exception if programmer forgot something
     */
    MapLoader() throws Exception {
        this.resDir = new Main().getClass().getResource("resources/").toString();
        this.appPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getPath();
        URL file = new URL(this.resDir+"maps/list.txt");
        BufferedReader list = new BufferedReader(new InputStreamReader(file.openStream()));
        String line;
        this.mapNames = new ArrayList<String>();
        while ((line = list.readLine()) != null) {
            this.defaultMapAmount++;
            this.mapNames.add(line);
        }
        this.reload();
    }

    /**
     * Reload external maps
     * @throws Exception if programmer forgot something
     */
    void reload() throws Exception {
        for (int i = this.mapNames.size(); i > this.defaultMapAmount; i--)
            this.mapNames.remove(i-1);
        if (!(new File(appPath).exists()) || !(new File(appPath).isDirectory()))
            return;
        File files[] = new File(appPath).listFiles();
        File file;
        String fileName;
        for (int i = 0; i < files.length; i++) {
            file = files[i];
            if (file.exists() && file.isFile() && file.getName().endsWith(".map")) {
                if (this.mapNames.contains(file.getName())) {
                    throw new Exception("'"+file.getName()+"' map name already exists in list");
                }
                fileName = file.getName();
                this.mapNames.add(fileName.substring(0, fileName.length()-4));
            }
        }
    }

    /**
     * Creates Map object from name if it exist in mapNames<br>
     * Scheme for creating maps<br>
     * 0,0;0,0<br>
     * 1,1;2,1<br>
     * Every line corresponds to row in game<br>
     * Strings separated by semicolon are related to columns<br>
     * Comma splits that string into 2 numbers, describing type and attribute<br>
     * Saved in map.elemAttribs, attributes are used to link gates, doors, keys and levels<br>
     * Saved in map.elemTypes, types describe entities present on map<br>
     * Type list:<br>
     * 0 - Floor (attribute not used)<br>
     * 1 - Wall (attribute not used)<br>
     * 2 - Gate<br>
     * 3 - Level<br>
     * 4 - Door<br>
     * 5 - Key<br>
     * 6 - Exit (attribute not used; only one)<br>
     * 7 - Player (attribute not used; only one)<br>
     * @param name map name<br>
     * @return map (Map) corresponding to name
     * @throws Exception if map is invalid or programmer forgot something
     */
    Map returnMap(String name) throws Exception {
        Map map;
        String path;
        if (!this.mapNames.contains(name))
            return null;
        if (this.mapNames.indexOf(name) < this.defaultMapAmount){
            path = resDir+"maps/"+name+".map";
        } else {
            path = appPath+"maps/"+name+".map";
        }
        URL file = new URL(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.openStream()));
        String element[];
        String tl[];
        String line;
        int index = 0;
        Integer t;
        Integer l;
        MapElement tempElem;
        map = new Map();
        ArrayList<ArrayList<MapElement>> elemTypes = map.getElemTypes();
        ArrayList<MapElement> tempTypes = new ArrayList<MapElement>();
        Player player = map.getPlayer();
        Integer playerCoords[] = new Integer[4];
        Integer forbiddenWithoutComma[] = {2,3,4,5};
        while ((line = reader.readLine()) != null) {
            tempTypes.clear();
            element = line.split(";");
            for (int i = 0; i < element.length; i++) {
                try {
                    if (element[i].contains(",")) {
                        tl = element[i].split(",");
                        t = Integer.parseInt(tl[0]);
                        l = Integer.parseInt(tl[1]);
                    } else {
                        t = Integer.parseInt(element[i]);
                        l = null;
                        for(Integer x : forbiddenWithoutComma){
                            if (t == x) {
                                throw new Exception("Found linkable object without specified link! Problem in map "+name+": row "+String.valueOf(index+1)+" column "+String.valueOf(i+1));
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    throw new Exception("Invalid element format found! Problem in map "+name+": row "+String.valueOf(index+1)+" column "+String.valueOf(i+1));
                }
                switch (t) {
                    case 0:
                        tempTypes.add(new Floor());
                        break;
                    case 1:
                        tempTypes.add(new Wall());
                        break;
                    case 2:
                        tempElem = new Gate();
                        tempElem.setLink(l);
                        tempTypes.add(tempElem);
                        break;
                    case 3:
                        tempElem = new Level();
                        tempElem.setLink(l);
                        tempTypes.add(tempElem);
                        break;
                    case 4:
                        tempElem = new Door();
                        tempElem.setLink(l);
                        tempTypes.add(tempElem);
                        break;
                    case 5:
                        tempElem = new Key();
                        tempElem.setLink(l);
                        tempTypes.add(tempElem);
                        break;
                    case 6:
                        if (playerCoords[2] != null && playerCoords[3] != null) {
                            throw new Exception("Exit position already loaded, but got it again! Problem in map "+name+": row "+String.valueOf(index+1)+" column "+String.valueOf(i+1));
                        }
                        playerCoords[2] = t;
                        playerCoords[3] = index;
                        tempTypes.add(new Exit());
                        break;
                    case 7:
                        if (playerCoords[0] != null && playerCoords[1] != null) {
                            throw new Exception("Player position already loaded, but got it again! Problem in map "+name+": row "+String.valueOf(index+1)+" column "+String.valueOf(i+1));
                        }
                        playerCoords[0] = t;
                        playerCoords[1] = index;
                        tempTypes.add(new Floor());
                        break;
                    default:
                        throw new Exception("Type number not in <0,7>. Problem in map "+name+": row "+String.valueOf(index+1)+" column "+String.valueOf(i+1));
                }
            }
            elemTypes.add((ArrayList<MapElement>)tempTypes.clone());
            index++;
        }
        map.setElemTypes(elemTypes);
        player.setCoords(playerCoords);
        map.setPlayer(player);
        return map;
    }
}
