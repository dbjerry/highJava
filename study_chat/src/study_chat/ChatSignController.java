/**
 * Sample Skeleton for 'ChatSign.fxml' Controller Class
 */

package study_chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import member.vo.MemberVO;
import member.service.*;

public class ChatSignController {
	
	private IMemberService service;
	
    public ChatSignController() {
    	service = MemberService.getInstance();
    }

    MemberVO mem_vo = new MemberVO();
    
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="field_id"
    private TextField field_id; // Value injected by FXMLLoader

    @FXML // fx:id="field_pw"
    private TextField field_pw; // Value injected by FXMLLoader

    @FXML // fx:id="field_name"
    private TextField field_name; // Value injected by FXMLLoader

    @FXML // fx:id="field_age"
    private TextField field_age; // Value injected by FXMLLoader

    @FXML // fx:id="field_phone"
    private TextField field_phone; // Value injected by FXMLLoader

    @FXML // fx:id="btn_sign_done"
    private Button btn_sign_done; // Value injected by FXMLLoader

    @FXML // fx:id="btn_sign_cancel"
    private Button btn_sign_cancel; // Value injected by FXMLLoader

    @FXML
    void sign_cancel(ActionEvent event) {
    	try {
			Parent sign = FXMLLoader.load(getClass().getResource("FxmlChatTest.fxml"));
			Scene scene = new Scene(sign);
			Stage primaryStage = (Stage)btn_sign_cancel.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void sign_done(ActionEvent event) {
    	if(field_id.getText().isEmpty() ||
    			field_pw.getText().isEmpty() ||
    			field_name.getText().isEmpty() ||
    			field_age.getText().isEmpty() ||
    			field_phone.getText().isEmpty()) {
    		errorAlert("Input Error", "작성되지 않은 항목이 있습니다.");
    		return;
    	}
    	
    	mem_vo = new MemberVO();
    	mem_vo.setMem_id(field_id.getText());
    	mem_vo.setMem_pw(field_pw.getText());
    	mem_vo.setMem_name(field_name.getText());
    	mem_vo.setMem_age(Integer.parseInt(field_age.getText()));
    	mem_vo.setMem_phone(field_phone.getText());
    	
    	service.insertMember(mem_vo);
    	
    	infoAlert("작업 결과", field_name.getText() + "님의 회원가입을 축하드립니다!");
    	
    	try {
			Parent sign = FXMLLoader.load(getClass().getResource("FxmlChatTest.fxml"));
			Scene scene = new Scene(sign);
			Stage primaryStage = (Stage)btn_sign_done.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void errorAlert(String header, String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(header);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
	
	public void infoAlert(String header, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Infomation");
		alert.setHeaderText(header);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert field_id != null : "fx:id=\"field_id\" was not injected: check your FXML file 'ChatSign.fxml'.";
        assert field_pw != null : "fx:id=\"field_pw\" was not injected: check your FXML file 'ChatSign.fxml'.";
        assert field_name != null : "fx:id=\"field_name\" was not injected: check your FXML file 'ChatSign.fxml'.";
        assert field_age != null : "fx:id=\"field_age\" was not injected: check your FXML file 'ChatSign.fxml'.";
        assert field_phone != null : "fx:id=\"field_phone\" was not injected: check your FXML file 'ChatSign.fxml'.";
        assert btn_sign_done != null : "fx:id=\"btn_sign_done\" was not injected: check your FXML file 'ChatSign.fxml'.";
        assert btn_sign_cancel != null : "fx:id=\"btn_sign_cancel\" was not injected: check your FXML file 'ChatSign.fxml'.";
    }
}
