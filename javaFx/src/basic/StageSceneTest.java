package basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StageSceneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox();		//	컨트롤들을 세로로 배치하는 컨테이너
		
		root.setPrefWidth(650);		//	가로 크기
		root.setPrefHeight(150);	//	세로 크기
		
		root.setAlignment(Pos.CENTER);
		root.setSpacing(20);
		
		Label label = new Label();	//	Label컨트롤
		label.setText("Hello, JavaFx.");
		label.setFont(new Font(50));	//	Font객체를 이용하여 글자 크기 설정
		
		Button button = new Button();
		button.setText("DONE");
		
		// 버튼을 클릭했을 때 이벤트 처리하기
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
		
		/* VBox컨테이너에 컨트롤들 추가하기 */
		
		// 방법 1 - 각각의 컨트롤들을 하나씩 추가
//		root.getChildren().add(label);
//		root.getChildren().add(button);
		
		// 방법 2 - 여러개의 컨트롤들을 한꺼번에 추가(addAll() 이용)
		root.getChildren().addAll(label, button);
		
		System.out.println();
		//-----------------------------------------------
		
		/* VBox를 루트 컨테이너로 하는 Scene객체 생성 */
		Scene scene = new Scene(root);
		
		// Scene객체를 Stage객체 추가
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Stage & Scene");	//	창 제목
		
		primaryStage.show();	//	창 보이기
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}

