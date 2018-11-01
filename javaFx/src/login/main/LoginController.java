/**
 * Sample Skeleton for 'LoginMain.fxml' Controller Class
 */

package login.main;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import login.service.IMemberService;
import login.vo.MemberVO;
import login.service.*;

public class LoginController {

	private IMemberService service;
	
	public LoginController() {
		service = MemberService.getInstance();
	}
	
	MemberVO mem_vo = new MemberVO();
	List<MemberVO> mem_list = null;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="field_id"
    private TextField field_id; // Value injected by FXMLLoader

    @FXML // fx:id="field_pw"
    private PasswordField field_pw; // Value injected by FXMLLoader

    @FXML // fx:id="btn_login"
    private Button btn_login; // Value injected by FXMLLoader

    @FXML // fx:id="btn_cancel"
    private Button btn_cancel; // Value injected by FXMLLoader
    
    public void errorAlert(String header, String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText(header);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
	
	public void infoAlert(String header, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFOMATION");
		alert.setHeaderText(header);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert field_id != null : "fx:id=\"field_id\" was not injected: check your FXML file 'LoginMain.fxml'.";
        assert field_pw != null : "fx:id=\"field_pw\" was not injected: check your FXML file 'LoginMain.fxml'.";
        assert btn_login != null : "fx:id=\"btn_login\" was not injected: check your FXML file 'LoginMain.fxml'.";
        assert btn_cancel != null : "fx:id=\"btn_cancel\" was not injected: check your FXML file 'LoginMain.fxml'.";

        mem_list = service.getMemberList();
        Map<String, String> param = new HashMap<String, String>();
        
        btn_login.setOnAction(e -> {
        	int chk_id = -1;
        	int chk_pw = -1;
        	
        	chk_id = service.getMember(field_id.getText());
        	chk_pw = service.getMemberPw(field_pw.getText().toString());
        	
        	String value1, value2;
        	
        	if(chk_id > 0 && chk_pw > 0) {
        		
        		value1 = field_id.getText();
        		value2 = field_pw.getText();
        		param.put("value1", value1);
        		param.put("value2", value2);
        		
        		List<MemberVO> memList = service.searchMember(param);

        		String str = "";
        		
        		for(MemberVO list : memList) {
        			if(list.getMem_id().equals(value1)) {
        				str = list.getMem_name();
        				infoAlert("작업 결과", str + "님, 로그인되었습니다.");
        			}
        		}
        		
        	}else {
        		errorAlert("Login Error", "ID나 패스워드를 확인 후 다시 하세요.");
        		return;
        	}
        	
        	
        });
        
        btn_cancel.setOnAction(e -> {
        	field_id.clear();
        	field_pw.clear();
        });
    }
}

