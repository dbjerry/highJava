/**
 * Sample Skeleton for 'custom.fxml' Controller Class
 */

package basic.dialog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CustomController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lblMsg"
    private Label lblMsg; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnOk"
    private Button btnOk; // Value injected by FXMLLoader

    @FXML
    void btnOkClicked(ActionEvent event) {
    	// 현재 창에 있는 컨트롤을 이용하여  현재 창을 가리키는
    	// Stage 객체 구하기
    	Stage currentStage = (Stage) btnOk.getScene().getWindow();
    	
    	// 창 닫기
    	currentStage.close();
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert lblMsg != null : "fx:id=\"lblMsg\" was not injected: check your FXML file 'custom.fxml'.";
        assert btnOk != null : "fx:id=\"btnOk\" was not injected: check your FXML file 'custom.fxml'.";

    }
}
