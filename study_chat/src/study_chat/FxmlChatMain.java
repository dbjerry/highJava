package study_chat;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlChatMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(
			getClass().getResource("FxmlChatTest.fxml")
		);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Chatting Program Test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
