package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		GridPane root = new GridPane();
		root.setPrefSize(300, 200);
		root.setPadding(new Insets(10));
		root.setHgap(10);
		root.setVgap(20);
		
		Label labelId = new Label("ID : ");
		Label labelPw = new Label("PW : ");
		
		TextField fieldId = new TextField();
		PasswordField fieldPw = new PasswordField();
		Button buttonLogin = new Button("Login");
		Button buttonCancel = new Button("Cancel");
	
		// Add Controls in GridPane
		// add() 이용
		// 형식) add(추가할 컨트롤, 칸 번호, 행 번호, colspan, rowspan)
		root.add(labelId, 1, 1, 2, 1);	//	colspan, rowspan을 사용하지 않으면 생략이 가능
		root.add(labelPw, 1, 2, 2, 1);
		root.add(fieldId, 3, 1, 2, 1);
		root.add(fieldPw, 3, 2, 2, 1);
		root.add(buttonLogin, 3, 4);
		root.add(buttonCancel, 4, 4);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("GridPane Test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
