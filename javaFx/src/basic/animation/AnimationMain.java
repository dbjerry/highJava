package basic.animation;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AnimationMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setWidth(350);			// 창의 너비 설정
		primaryStage.setHeight(500);		// 창의 높이 설정
		primaryStage.setResizable(false);	// 창 크기 변경 불가
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
