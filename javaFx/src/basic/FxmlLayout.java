package basic;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FxmlLayout extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		// Fxml로 디자인된 문서를 읽어와 보여주기
		
		// Fxml문서 읽기 방법 1
//		HBox hBox = FXMLLoader.load(getClass().getResource("FxmlLayout.fxml"));
//		Parent hBox = FXMLLoader.load(getClass().getResource("FxmlLayout.fxml"));
		
		
		// *Fxml문서 읽기 방법 2
		// 1) FXMLLoader객체 생성
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FxmlLayout.fxml"));
		
		// 2) 생성된 FXMLLoader객체의 load() 호출
		Parent hBox = loader.load();
		
		Scene scene = new Scene(hBox);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Program Layout");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
