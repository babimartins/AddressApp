package addressapp.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.Dialogs;
import addressapp.MainApp;
import addressapp.util.Files;
import addressapp.util.FilesPath;

/**
 * FXML Controller class
 *
 * @author Babi
 */
public class RootLayoutController implements Initializable {

    public void initialize(URL url, ResourceBundle rb) { }    
    
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        FilesPath.setPersonFilePath(null);
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) 
            Files.loadPersonDataFromFile(file);
    }

    @FXML
    private void handleSave() {
        File personFile = FilesPath.getPersonFilePath();
        if (personFile != null) 
            Files.savePersonDataToFile(personFile);
        else 
            handleSaveAs();
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) 
                file = new File(file.getPath() + ".xml");
            Files.savePersonDataToFile(file);
        }
    }

    @FXML
    private void handleAbout() {
        Dialogs.create()
            .title("AddressApp")
            .masthead("Sobre")
            .message("Autor: Marco Jakob\nWebsite: http://code.makery.ch")
            .showInformation();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    @FXML
    private void handleShowBirthdayStatistics() {
        mainApp.showBirthdayStatistics();
    }
    
}
