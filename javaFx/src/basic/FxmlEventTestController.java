/**
 * Sample Skeleton for 'FxmlEventTest.fxml' Controller Class
 */

package basic;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FxmlEventTestController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="textField"
    private TextField textField; // Value injected by FXMLLoader

    @FXML // fx:id="button"
    private Button button; // Value injected by FXMLLoader

    @FXML // fx:id="textArea"
    private TextArea textArea; // Value injected by FXMLLoader

    @FXML
    void displayGugudan(ActionEvent event) {
    	// 버튼을 클릭했을 때 이벤트를 처리
    	String dan = textField.getText();
    	if(!Pattern.matches("^[0-9]+$", dan)) {
    		textArea.setText("출력할 단을 입력하세요.");
    		return;
    	}
    	
    	int intDan = Integer.parseInt(dan);
    	textArea.setText(intDan + "단\n\n");
    	for(int i = 1; i < 10; i++) {
    		int r = intDan * i;
    		textArea.appendText(intDan + " x " + i + " = " + r + "\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert textField != null : "fx:id=\"textField\" was not injected: check your FXML file 'FxmlEventTest.fxml'.";
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'FxmlEventTest.fxml'.";
        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'FxmlEventTest.fxml'.";

    }
}
