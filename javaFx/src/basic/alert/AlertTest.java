package basic.alert;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class AlertTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		Alert alertInfo = new Alert(AlertType.INFORMATION);
		alertInfo.setTitle("INFOMATION");
		alertInfo.setHeaderText("INFOMATION");	//	message title
		alertInfo.setContentText("User Content");
		
		alertInfo.showAndWait();
		
		/////////////////////////////////////////////////////////
		
		Alert alertError = new Alert(AlertType.ERROR);
		alertError.setTitle("ERROR");
		alertError.setHeaderText("ERROR");
		alertError.setContentText("ERROR message");
		
		alertError.showAndWait();
		
		////////////////////////////////////////////////////////
		
		Alert alertWarning = new Alert(AlertType.WARNING);
		alertWarning.setTitle("WARNING");
		alertWarning.setHeaderText("WARNING");
		alertWarning.setContentText("WARNING message");

		////////////////////////////////////////////////////////
		
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		alertConfirm.setTitle("CONFIRMATION");
		alertConfirm.setHeaderText("CONFIRMATION");
		alertConfirm.setContentText("Are you Man ?");
		
		// Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
		ButtonType confirmResult = alertConfirm.showAndWait().get();
		
		if(confirmResult == ButtonType.OK) {
			System.out.println("OK 버튼을 눌렀습니다.");
		}else if(confirmResult == ButtonType.CANCEL){
			System.out.println("취소 버튼을 눌렀습니다.");
		}else {
			System.out.println("...");
		}
		
		////////////////////////////////////////////////////////
		
		// JavaScript의 prompt()창과 같은 기능
		// '기본값'은 생략 가능
		TextInputDialog prompt = new TextInputDialog("default value");
		prompt.setTitle("input value");
		prompt.setHeaderText("input your name");
		
		// 창을 보여주고 사용자가 입력한 값 구하기
		Optional<String> result = prompt.showAndWait();
		
		String strResult = "";
		if(result.isPresent()) {	//	값이 있으면..
			strResult = result.get();//	값 꺼내오기
		}else {
			strResult = null;
		}
		
		System.out.println("input value : " + strResult);
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
