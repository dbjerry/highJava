package basic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventTest2 extends Application {

	TextField textField = new TextField();
	TextArea textArea = new TextArea();
	
	@Override
	public void start(Stage primaryStage) {
		GridPane root = new GridPane();
		root.setPrefSize(600, 700);
		root.setPadding(new Insets(20));
		root.setHgap(10);
		root.setVgap(20);
		
		textField.setPromptText("출력할 단의 숫자를 입력해주세요.");
		
		Button button = new Button("출력");
		
		String dan = textField.getText();
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String dan = textField.getText();
				if(dan.isEmpty()) {
					textArea.setText("출력할 단을 입력하세요.");
					textField.requestFocus();	//	포커스 주기
					return;
				}
				
				int intDan = Integer.parseInt(dan);
				
				textArea.setText(intDan + "단\n\n");
				for(int i = 1; i < 10; i++) {
					int r = intDan * i;
					textArea.appendText(intDan + " * " + i + " = " + r + "\n");
				}
				
			}
		});
		
		
		
		root.add(textField, 0, 0, 10, 1);
		root.add(button, 10, 0, 2, 1);
		root.add(textArea, 0, 1, 13, 30);
		
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("home work");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
