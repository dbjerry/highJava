/**
 * Sample Skeleton for 'ChatDisplay.fxml' Controller Class
 */

package study_chat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatDisplayController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="field_search"
    private TextField field_search; // Value injected by FXMLLoader

    @FXML // fx:id="userList"
    private ListView<?> userList; // Value injected by FXMLLoader

    @FXML // fx:id="onLineCount"
    private Label onLineCount; // Value injected by FXMLLoader

    @FXML // fx:id="roomList"
    private ListView<?> roomList; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert field_search != null : "fx:id=\"field_search\" was not injected: check your FXML file 'ChatDisplay.fxml'.";
        assert userList != null : "fx:id=\"userList\" was not injected: check your FXML file 'ChatDisplay.fxml'.";
        assert onLineCount != null : "fx:id=\"onLineCount\" was not injected: check your FXML file 'ChatDisplay.fxml'.";
        assert roomList != null : "fx:id=\"roomList\" was not injected: check your FXML file 'ChatDisplay.fxml'.";

    }
}
