package addressapp;

import addressapp.controller.PersonOverviewController;
import addressapp.controller.RootLayoutController;
import addressapp.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Babi
 */
public class MainApp extends Application {
    
    private static Stage primaryStage;
    private static BorderPane rootLayout;
    
    private static ObservableList<Person> personData = FXCollections.observableArrayList();
    
    public MainApp() {
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }
    
    public static ObservableList<Person> getPersonData() {
        return personData;
    }
    
    public static Stage getPrimaryStage() {
        return MainApp.primaryStage;
    }
    
    public static void setPrimaryStage(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
    }
    
    public static BorderPane getRootLayout() {
        return MainApp.rootLayout;
    }    
    
    public static void setRootLayout(BorderPane rootLayout) {
        MainApp.rootLayout = rootLayout;
    }    

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        
        this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

        RootLayoutController.initRootLayout();
        primaryStage.show();

        PersonOverviewController.showPersonOverview();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
        
}
