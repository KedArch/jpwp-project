package pl.kedarch.mazedea;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.File;
import java.net.URL;
public class Mazedea {
    public static void main(String[] args) throws Exception {
        String resDir = new Mazedea().getClass().getResource("resources/").toString();
        System.out.println(resDir+"maps");
        String appPath = new File(Mazedea.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getPath();
        System.out.println(appPath);
        URL file = new URL(resDir);
        System.out.println(file);
        BufferedReader d = new BufferedReader(new InputStreamReader(file.openStream()));
        if (resDir.startsWith("jar:")) {
            System.out.println("In jar");
        } else {
            System.out.println("Not in jar");
        }
        File files[] = new File(appPath).listFiles();
        System.out.println(files.length);
        System.out.println(files);
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
        Maze maze = new Maze(1, 1);
    }
}
