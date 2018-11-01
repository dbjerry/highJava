/**
 * Sample Skeleton for 'FxmlChatTest.fxml' Controller Class
 */

package study_chat;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import member.vo.MemberVO;
import member.service.*;

public class FxmlChatController {
	
	Socket socket;
	
	private IMemberService service;
	
    public FxmlChatController() {
    	service = MemberService.getInstance();
    }
    
    MemberVO mem_vo = new MemberVO();
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_sign"
    private Button btn_sign; // Value injected by FXMLLoader

    @FXML // fx:id="field_id"
    private TextField field_id; // Value injected by FXMLLoader

    @FXML // fx:id="field_pw"
    private PasswordField field_pw; // Value injected by FXMLLoader

    @FXML // fx:id="btn_login"
    private Button btn_login; // Value injected by FXMLLoader

    @FXML // fx:id="btn_cancel"
    private Button btn_cancel; // Value injected by FXMLLoader

    // 클라이언트 프로그램 동작 메소드
 	public void startClient(String IP, int port) {
 		Thread thread = new Thread() {
 			@Override
 			public void run() {
 				try {
 					socket = new Socket(IP, port);
 					
 				} catch (Exception e) {
 					if(!socket.isClosed()) {
 						stopClient();
 						System.out.println("[서버접속 실패]");
 						Platform.exit();
 					}
 				}
 			}
 		};
 		thread.start();
 	}
 	
 	// 클라이언트 프로그램 종료 메소드
 	public void stopClient() {
 		try {
 			if(socket != null && !socket.isClosed()) {
 				socket.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}
 	
    @FXML
    void goLogin(ActionEvent event) {
    	String login_id = field_id.getText();
    	String login_pw = field_pw.getText();
    	
    	Map<String, String> param = new HashMap<String, String>();
    	
    	int chk_id = service.getMember(login_id);
    	int chk_pw = service.getMemberPw(login_pw);
    	
    	if(chk_id > 0 && chk_pw > 0) {
    		param.put("value1", login_id);
    		param.put("value2", login_pw);
    		
    		mem_vo = service.searchMember(param);
    		
    		if(mem_vo.getMem_id().equals(login_id)) {
    			infoAlert("작업 결과", mem_vo.getMem_name() + "님, 로그인되었습니다.");
    		}
    	}else {
        	errorAlert("Login Error", "ID 혹은 Password를 확인해주세요.");
        	return;
    	}
    	
    	TextField IPText = new TextField("127.0.0.1");
		TextField portText = new TextField("8888");
    	
    	if(btn_login.getText().equals("Login")) {
			int port = 8888;
			try {
				port = Integer.parseInt(portText.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
			startClient(IPText.getText(), port);

		} else {
			stopClient();
		}
    	
    	try {
			Parent sign = FXMLLoader.load(getClass().getResource("ChatDisplay.fxml"));
			Scene scene = new Scene(sign);
			Stage primaryStage = (Stage)btn_login.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void goSign(ActionEvent event) {
    	try {
			Parent sign = FXMLLoader.load(getClass().getResource("ChatSign.fxml"));
			Scene scene = new Scene(sign);
			Stage primaryStage = (Stage)btn_sign.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void goCancel(ActionEvent event) {
    	Platform.exit();
    }
    
    
    
    
    
    
    
    
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
        assert btn_sign != null : "fx:id=\"btn_sign\" was not injected: check your FXML file 'FxmlChatTest.fxml'.";
        assert field_id != null : "fx:id=\"field_id\" was not injected: check your FXML file 'FxmlChatTest.fxml'.";
        assert field_pw != null : "fx:id=\"field_pw\" was not injected: check your FXML file 'FxmlChatTest.fxml'.";
        assert btn_login != null : "fx:id=\"btn_login\" was not injected: check your FXML file 'FxmlChatTest.fxml'.";
        assert btn_cancel != null : "fx:id=\"btn_cancel\" was not injected: check your FXML file 'FxmlChatTest.fxml'.";

    }
    
}

