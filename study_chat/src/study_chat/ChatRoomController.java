/**
 * Sample Skeleton for 'ChatRoom.fxml' Controller Class
 */

package study_chat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ChatRoomController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="chatArea"
    private TextArea chatArea; // Value injected by FXMLLoader

    @FXML // fx:id="chatSend"
    private Button chatSend; // Value injected by FXMLLoader

    @FXML // fx:id="chatPane"
    ListView chatPane; // Value injected by FXMLLoader
    
    @FXML
    void sendAction(ActionEvent event) {
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert chatArea != null : "fx:id=\"chatArea\" was not injected: check your FXML file 'ChatRoom.fxml'.";
        assert chatSend != null : "fx:id=\"chatSend\" was not injected: check your FXML file 'ChatRoom.fxml'.";
        assert chatPane != null : "fx:id=\"chatPane\" was not injected: check your FXML file 'ChatRoom.fxml'.";

    }
}
