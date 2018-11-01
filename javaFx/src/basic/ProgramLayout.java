package basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ProgramLayout extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Java코드를 이용한 화면 구성 예제
		
		HBox hBox = new HBox();
		
		// Padding : 안쪽 여백
		/*
		 * Insets객체를 이용하여 여백을 설정하는데
		 * 이 객체는 위, 오른쪽, 아래, 왼쪽 순으로 값을 설정한다.
		 */
		hBox.setPadding(new Insets(10));
		
		// 컨트롤 사이의 간격
		hBox.setSpacing(10);
		
		// TextField객체 생성
		TextField text = new TextField();
		text.setPrefWidth(200);
		text.setPromptText("여기에 입력하세요.");	//	임시로 보여줄 메세지
		text.setText("content");	//	실제 출력할 내용
		
		// Button객체 생성
		Button button = new Button("DONE");
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// 처리할 내용을 이 구현부에 기술한다.
				
				// 창 닫기(프로그램 종료)
//				primaryStage.close();
//				System.exit(0);
				Platform.exit();
			}
		});
		
		// 생성된 컨트롤들을 컨테이너에 추가하기
		hBox.getChildren().addAll(text, button);
		
		// Scene객체 생성
		Scene scene = new Scene(hBox);
		
		// Scene객체를 Stage에 추가
		primaryStage.setScene(scene);
		primaryStage.setTitle("Program Layout");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
