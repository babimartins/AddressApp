package addressapp.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Babi
 */
public class AlertsHelper {
    
    private static Alert alert;
    
    public AlertsHelper(){}

    public static Alert getAlert() {
        return alert;
    }
    public static void setAlert(Alert alert) {
        AlertsHelper.alert = alert;
    }
    
    public static Alert creatAlert(String title, String header, String content, AlertType type){
        alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
    
}
