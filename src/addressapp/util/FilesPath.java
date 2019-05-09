package addressapp.util;

import addressapp.MainApp;
import java.io.File;
import java.util.prefs.Preferences;
import javafx.stage.Stage;

/**
 *
 * @author Babi
 */
public class FilesPath {
    
    private static Stage primaryStage;
    
    public static File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) 
            return new File(filePath);
        else 
            return null;
    }

    public static void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");
            primaryStage.setTitle("AddressApp");
        }
    }
    
}
