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
        mapNames = new ArrayList<String>();
        while ((line = list.readLine()) != null) {
            this.defaultMapAmount++;
            mapNames.add(line);
        }
        this.reload();
    }

    /**
     * Reload external maps
     * @throws Exception if programmer forgot something
     */
    void reload() throws Exception {
        for (int i = mapNames.size(); i > this.defaultMapAmount; i--)
            this.mapNames.remove(i-1);
        if (!(new File(appPath).exists()) || !(new File(appPath).isDirectory()))
            return;
        File files[] = new File(appPath).listFiles();
        File file;
        String fileName;
        for (int i = 0; i < files.length; i++) {
            file = files[i];
            if (file.exists() && file.isFile() && file.getName().endsWith(".map")) {
                fileName = file.getName();
                this.mapNames.add(fileName.substring(0, fileName.length()-4));
            }
        }
    }
}
