/**
 * Sample Skeleton for 'exControl.fxml' Controller Class
 */

package basic.exControl;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class ExController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tfName"
    private TextField tfName; // Value injected by FXMLLoader

    @FXML // fx:id="rbMale"
    private RadioButton rbMale; // Value injected by FXMLLoader

    @FXML // fx:id="sexGroup"
    private ToggleGroup sexGroup; // Value injected by FXMLLoader

    @FXML // fx:id="rbFemle"
    private RadioButton rbFemle; // Value injected by FXMLLoader

    @FXML // fx:id="view"
    private Button view; // Value injected by FXMLLoader

    @FXML // fx:id="textArea"
    private TextArea textArea; // Value injected by FXMLLoader
    
    @FXML // fx:id="hobby"
    private HBox hobby; // Value injected by FXMLLoader

    // 취미 체크박스를 배열로 만들어서 추가하기
    String[] hobbies = new String[] {
    		"여행", "등산", "독서", "바둑", "장기", "게임",
    		"테니스", "배드민턴"
    };
  
    // 체크박스 배열에 취미 체크박스 배열을 넣는다.
    CheckBox[] checkHobby = new CheckBox[hobbies.length];
    
    @FXML
    void viewClick(ActionEvent event) {
    	String name = tfName.getText();
    	if(name.isEmpty()) {
    		textArea.setText("이름을 입력하세요.");
    		alert("입력오류", "이름을 입력하세요");
    		tfName.requestFocus();
    		return;
    	}
    	
    	String sex = "";
    	if(rbMale.isSelected() == true) {
    		sex = "남자";
    	}else {
    		sex = "여자";
    	}
    	
    	String hobbies = "";
    	for(int i = 0; i < checkHobby.length; i++) {
    		if(checkHobby[i].isSelected() == true) {
    			if(!hobbies.equals("")) {
    				hobbies += ", ";
    			}
    			hobbies += checkHobby[i].getText();
    		}
    	}
    	textArea.setText(name + "님의\n");
    	textArea.appendText("성별은 " + sex + "이고, \n");
    	textArea.appendText("취미는 " + 
    			(hobbies.equals("") ? "없네요." : hobbies + "입니다."));
    	
    }

    public void alert(String header, String message) {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("WARNING");
    	alert.setHeaderText(header);
    	alert.setContentText(message);
    	
    	alert.showAndWait();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'exControl.fxml'.";
        assert rbMale != null : "fx:id=\"rbMale\" was not injected: check your FXML file 'exControl.fxml'.";
        assert sexGroup != null : "fx:id=\"sexGroup\" was not injected: check your FXML file 'exControl.fxml'.";
        assert rbFemle != null : "fx:id=\"rbFemle\" was not injected: check your FXML file 'exControl.fxml'.";
        assert view != null : "fx:id=\"view\" was not injected: check your FXML file 'exControl.fxml'.";
        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'exControl.fxml'.";
        assert hobby != null : "fx:id=\"hobby\" was not injected: check your FXML file 'exControl.fxml'.";
        
        for(int i = 0; i < hobbies.length; i++) {
        	checkHobby[i] = new CheckBox(hobbies[i]);
        }
        
        hobby.getChildren().addAll(checkHobby);
        
    }
}

