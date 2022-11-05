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
        this.resDir = new Mazedea().getClass().getResource("resources/").toString();
        this.appPath = new File(Mazedea.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getPath();
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
                    System.err.println("'"+file.getName()+"' map name already exists in list");
                    throw new Exception();
                }
                fileName = file.getName();
                this.mapNames.add(fileName.substring(0, fileName.length()-4));
            }
        }
    }

    /**
     * Creates Maze object from name if it exist in mapNames<br>
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
     * 2 - Player (attribute not used)<br>
     * 3 - Exit (attribute not used)<br>
     * 4 - Gate<br>
     * 5 - Level<br>
     * 6 - Door<br>
     * 7 - Key<br>
     * @param name map name<br>
     * @return map (Maze) corresponding to name
     * @throws Exception if programmer forgot something
     */
    Maze returnMaze(String name) throws Exception {
        Maze map;
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
        String t[];
        String line;
        map = new Maze();
        ArrayList<Integer> listTypes = new ArrayList<Integer>();
        ArrayList<Integer> listAttribs = new ArrayList<Integer>();
        while ((line = reader.readLine()) != null) {
            listTypes.clear();
            listAttribs.clear();
            element = line.split(";");
            for (int i = 0; i < element.length; i++) {
                t = element[i].split(",");
                listTypes.add(Integer.parseInt(t[0]));
                listAttribs.add(Integer.parseInt(t[1]));
            }
            map.elemTypes.add((ArrayList<Integer>)listTypes.clone());
            map.elemAttribs.add((ArrayList<Integer>)listAttribs.clone());
        }
        return map;
    }
}
