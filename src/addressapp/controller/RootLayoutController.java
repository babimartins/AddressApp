package addressapp.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import addressapp.MainApp;
import addressapp.util.AlertsUtil;
import addressapp.util.Files;
import addressapp.util.FilesUtil;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Babi
 */
public class RootLayoutController implements Initializable {

    public void initialize(URL url, ResourceBundle rb) { }    
    
    private static MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        FilesUtil.setPersonFilePath(null);
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
        File personFile = FilesUtil.getPersonFilePath();
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
        AlertsUtil.createAlert("AddressApp", "Sobre", "Autor original: Marco Jakob"
                + "\nWebsite: http://code.makery.ch\nAluna: Bárbara Martins", Alert.AlertType.INFORMATION).showAndWait();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    @FXML
    private void handleShowBirthdayStatistics() {
        BirthdayStatisticsController.showBirthdayStatistics();
    }
       
    public static void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            MainApp.setRootLayout((BorderPane) loader.load());

            Scene scene = new Scene(MainApp.getRootLayout());
            Stage auxPrimaryStage = MainApp.getPrimaryStage();
            auxPrimaryStage.setScene(scene);
            MainApp.setPrimaryStage(auxPrimaryStage);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(mainApp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = FilesUtil.getPersonFilePath();
        if (file != null) {
            Files.loadPersonDataFromFile(file);
        }
    }
    
}
