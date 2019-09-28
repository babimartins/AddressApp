package addressapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import addressapp.MainApp;
import addressapp.model.Person;
import addressapp.util.AlertsHelper;
import addressapp.util.DateUtil;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Babi
 */
public class PersonOverviewController {

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());

        showPersonDetails(null);

        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    } 
    
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private static MainApp mainApp;

    public PersonOverviewController() {
    }

    /**
     * É chamado pela aplicação principal para dar uma referência de volta a si mesmo.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        setMainAppItens(mainApp.getPersonData());
    }
    
    private void setMainAppItens(ObservableList<Person> personData){
        personTable.setItems(personData);
    }
    
    private void showPersonDetails(Person person) {
        if (person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = AlertsHelper.createAlert("Nenhuma seleção", "Nenhuma Pessoa Selecionada", 
                "Por favor, selecione uma pessoa na tabela.", AlertType.WARNING);
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = PersonEditDialogController.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = PersonEditDialogController.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            Alert alert = AlertsHelper.createAlert("Nenhuma seleção", "Nenhuma Pessoa Selecionada", 
                    "Por favor, selecione uma pessoa na tabela.", AlertType.WARNING);
                alert.showAndWait();
        }
    }
      
    
    public static void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            BorderPane auxRootLayout = MainApp.getRootLayout();
            auxRootLayout.setCenter(personOverview);
            MainApp.setRootLayout(auxRootLayout);

            PersonOverviewController controller = loader.getController();
            controller.setMainApp(mainApp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
}
