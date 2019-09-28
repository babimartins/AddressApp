package addressapp.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Babi
 */
public class AlertsUtil {
    
    private static Alert alert;
    
    public AlertsUtil(){}

    public static Alert getAlert() {
        return alert;
    }
    public static void setAlert(Alert alert) {
        AlertsUtil.alert = alert;
    }
    
    public static Alert createAlert(String title, String header, String content, AlertType type){
        alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }
    
}
