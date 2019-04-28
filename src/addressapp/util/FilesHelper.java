package addressapp.util;

import addressapp.MainApp;
import addressapp.model.Person;
import addressapp.model.PersonListWrapper;
import java.io.File;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Babi
 */
public class FilesHelper {
    
    /**
     * Os dados como uma observable list de Persons.
     */
    private static ObservableList<Person> personData = FXCollections.observableArrayList();
    private static Stage primaryStage;
    
    /**
     * Retorna o arquivo de preferências da pessoa, o último arquivo que foi
     * aberto. As preferências são lidas do registro específico do SO (Sistema
     * Operacional). Se tais prefêrencias não puderem ser encontradas, ele
     * retorna null.
     *
     * @return
     */
    public static File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Define o caminho do arquivo do arquivo carregado atual. O caminho é
     * persistido no registro específico do SO (Sistema Operacional).
     *
     * @param file O arquivo ou null para remover o caminho
     */
    public static void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }

    /**
     * Carrega os dados da pessoa do arquivo especificado. A pessoa atual será
     * substituída.
     *
     * @param file
     */
    public static void loadPersonDataFromFile(File file) {
        try {

            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Dialogs.create()
                    .title("Erro")
                    .masthead("Não foi possível carregar dados do arquivo:\n"
                            + file.getPath()).showException(e);
        }
    }

    /**
     * Salva os dados da pessoa atual no arquivo especificado.
     *
     * @param file
     */
    public static void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Envolvendo nossos dados da pessoa.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            // Enpacotando e salvando XML  no arquivo.
            m.marshal(wrapper, file);

            // Saalva o caminho do arquivo no registro.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Dialogs.create().title("Erro")
                    .masthead("Não foi possível salvar os dados do arquivo:\n"
                            + file.getPath()).showException(e);
        }
    }
    
}
