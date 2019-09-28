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
    
    private static ObservableList<Person> personData = FXCollections.observableArrayList();
    
    public static void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            FilesUtil.setPersonFilePath(file);
        } catch (Exception e) { 
            Dialogs.create()
                    .title("Erro")
                    .masthead("Não foi possível carregar dados do arquivo:\n"
                            + file.getPath()).showException(e);
        }
    }

    public static void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            m.marshal(wrapper, file);

            FilesUtil.setPersonFilePath(file);
        } catch (Exception e) { 
            Dialogs.create().title("Erro")
                    .masthead("Não foi possível salvar os dados do arquivo:\n"
                            + file.getPath()).showException(e);
        }
    }
    
}
