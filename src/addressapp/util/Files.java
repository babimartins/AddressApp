package addressapp.util;

import addressapp.model.Person;
import addressapp.model.PersonListWrapper;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Babi
 */
public class Files {
    
    /**
     * Os dados como uma observable list de Persons.
     */
    private static ObservableList<Person> personData = FXCollections.observableArrayList();
    

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
            FilesPath.setPersonFilePath(file);

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
            FilesPath.setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Dialogs.create().title("Erro")
                    .masthead("Não foi possível salvar os dados do arquivo:\n"
                            + file.getPath()).showException(e);
        }
    }
    
}
