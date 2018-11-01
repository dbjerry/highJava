package basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ComboBoxGugudan_sem extends Application {

	@Override
	public void start(Stage primaryStage) {
		ObservableList<Integer> danList =
				FXCollections.observableArrayList(
					1,2,3,4,5,6,7,8,9
				);
		
		BorderPane root = new BorderPane();
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setAlignment(Pos.CENTER);
		
		TextArea textArea = new TextArea();
		
		Button button = new Button("Done");
		button.setDisable(true);	//	컨트롤을 비활성화 시키기
		
		ComboBox<Integer> comboDan = new ComboBox<Integer>();
		comboDan.setItems(danList);
		comboDan.setPromptText("단 선택");
		
		comboDan.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
			
			@Override
			public ListCell<Integer> call(ListView<Integer> param) {
				// TODO Auto-generated method stub
				return new ListCell<Integer>() {
					@Override
					protected void updateItem(Integer item, boolean empty) {
						// TODO Auto-generated method stub
						super.updateItem(item, empty);
						if(empty) {
							setText(null);
						}else {
							setText(item.intValue() + "단");
						}
					}
				};
				
			}
		});
		
		comboDan.setButtonCell(new ListCell<Integer>() {
			@Override
			protected void updateItem(Integer item, boolean empty) {
				// TODO Auto-generated method stub
				super.updateItem(item, empty);
				if(empty) {
					setText(null);
				}else {
					setText(item.intValue() + "단");
				}
			}
		});
		
		
		// 콤보박스 이벤트
		comboDan.setOnAction(e -> {
			if(comboDan.getValue() != null) {
				// 비활성화였던 컨트롤을 다시 활성화 시킨다.
				button.setDisable(false);
			}
		});
		
		
		// 버튼 이벤트
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(comboDan.getValue() != null) {
					// 콤보박스에서 선택된 값 가져오기
					// 방법 1) int dan = comboDan.getValue();
					// 방법 2)
					int dan = comboDan.getSelectionModel().getSelectedItem();
					textArea.setText(dan + "단\n\n");
					
					for (int i = 1; i < 10; i++) {
						int r = dan * i;
						textArea.appendText(dan + " x " + i + " = " + r + "\n");
					}
				}else {
					textArea.setText("출력할 단을 선택하세요.");
				}
			}
		});
		
		hbox.getChildren().addAll(comboDan, button);
		
		root.setTop(hbox);
		root.setCenter(textArea);
		
		Scene scene = new Scene(root, 300, 200);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("combobox 구구단");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
