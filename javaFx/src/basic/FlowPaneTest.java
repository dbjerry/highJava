package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		FlowPane root = new FlowPane();
		
		root.setPrefSize(300, 200);
		root.setHgap(10);
		root.setVgap(30);
		
		root.setPadding(new Insets(10));
		
		Button[] buttons = new Button[10];
		
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button("DONE-" + i);
		}	//	언더바(_) 한개는 생략, 두개는 출력, 세개는 한개로 출력, 그 외에도 여러 경우의 수가 존재함
		
		root.getChildren().addAll(buttons);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("FlowPane Test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
