package addressapp.controller;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import addressapp.model.Person;
import addressapp.util.AlertsHelper;
import addressapp.util.DateUtil;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author Babi
 */
public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) 
            errorMessage += "Nome inválido!\n"; 
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) 
            errorMessage += "Sobrenome inválido!\n"; 
        if (streetField.getText() == null || streetField.getText().length() == 0) 
            errorMessage += "Rua inválida!\n"; 
        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) 
            errorMessage += "Código Postal inválido!\n"; 
        else {
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Código Postal inválido (deve ser um inteiro)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) 
            errorMessage += "Cidade inválida!\n"; 

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) 
            errorMessage += "Aniversário inválido!\n";
        else 
            if (!DateUtil.validDate(birthdayField.getText())) 
                errorMessage += "Aniversário inválido. Use o formato dd.mm.yyyy!\n";

        if (errorMessage.length() == 0) 
            return true;
        else {
            Alert alert = AlertsHelper.createAlert("Campos Inválidos", 
                    "Por favor, corrija os campos inválidos", 
                    errorMessage, AlertType.ERROR);
                alert.showAndWait();
            return false;
        }
    }
    
}