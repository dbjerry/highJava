package basic;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComboBoxTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		HBox hbox = new HBox(10);
		TextArea textArea = new TextArea();
		
		// 방법 1)
		ComboBox<String> combo = new ComboBox<String>();
		combo.getItems().addAll("한강", "금강", "압록강", "낙동강");
		combo.setValue("한강");	//	초기 선택값 설정
		
		// 방법 2)
		ObservableList<String> fruitList = 
				FXCollections.observableArrayList(
					"사과", "배", "딸기", "복숭아", "감"
				);
		ComboBox<String> combo2 = new ComboBox<String>(fruitList);
		combo2.getItems().addAll("포도", "대추", "귤");
		combo2.setValue("사과");
		
		Button button = new Button("Done");
		
		// 콤보박스에서 값이 변경될 때의 처리
		// 즉, change이벤트 처리
		combo.valueProperty().addListener(
			new ChangeListener<String>() {
				public void changed(javafx.beans.value.ObservableValue<?
						extends String> observable, String oldValue, String newValue) {
					textArea.setText("현재 선택값 : " + newValue);
				}; {};
			}
		);
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// 콤보박스의 현재 선택된 값은 getValue()를
				// 이용하여 구할 수 있다.
				if(combo.getValue() != null && combo2.getValue() != null) {
					textArea.setText(combo.getValue() + " 지역의 과일은 " 
							+ combo2.getValue() + "가 유명합니다.");
				}
				
			}
		});
		
		hbox.getChildren().addAll(combo, combo2, button);
		hbox.setPadding(new Insets(10));
		
		root.setTop(hbox);
		root.setCenter(textArea);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("ComboBox Test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
