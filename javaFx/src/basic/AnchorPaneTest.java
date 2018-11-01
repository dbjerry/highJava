package basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		/* AnchorPane	->	놓여질 컨트롤들을 x, y좌표로 설정하여 배치 */
		
		AnchorPane root = new AnchorPane();
		root.setPrefSize(300, 150);	//	Container Size
		
		Label labelId = new Label("ID : ");
		Label labelPw = new Label("Password : ");
		TextField fieldId = new TextField();
		PasswordField fieldPw = new PasswordField();
		Button buttonLogin = new Button("Login");
		Button buttonCancel = new Button("Cancel");
		
		/*
		 * 좌표 X, Y의 값을 설정하는 메서드
		 * 기본적인 좌표값(0,0)은 왼쪽 위 꼭지점
		 */
		// ID 위치
		labelId.setLayoutX(42);
		labelId.setLayoutY(22);
		
		// Password 위치
		labelPw.setLayoutX(42);
		labelPw.setLayoutY(62);
		
		// ID입력 위치
		fieldId.setPromptText("ID를 입력해주세요.");
		fieldId.setLayoutX(125);
		fieldId.setLayoutY(22);
		
		// Password입력 위치
		fieldPw.setPromptText("Password를 입력해주세요.");
		fieldPw.setLayoutX(125);
		fieldPw.setLayoutY(62);
		
		// 로그인버튼 위치
		buttonLogin.setLayoutX(100);
		buttonLogin.setLayoutY(110);
		
		// 취소버튼 위치
		buttonCancel.setLayoutX(190);
		buttonCancel.setLayoutY(110);
		
		root.getChildren().addAll(
				labelId, labelPw, fieldId, fieldPw, buttonLogin, buttonCancel);
		
		// Scene객체 생성
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("AchorPane Test");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
