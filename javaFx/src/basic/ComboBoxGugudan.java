package basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ComboBoxGugudan extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		
		ComboBox<Gugudan> gugudan = new ComboBox<Gugudan>();
		TextArea textArea = new TextArea();
		
		ObservableList<Gugudan> list = FXCollections.observableArrayList(
					new Gugudan(1), new Gugudan(2), new Gugudan(3),
					new Gugudan(4), new Gugudan(5), new Gugudan(6),
					new Gugudan(7), new Gugudan(8), new Gugudan(9)
				);
		
		gugudan.setItems(list);
		
		gugudan.setCellFactory(
			new Callback<ListView<Gugudan>, ListCell<Gugudan>>() {
				
				@Override
				public ListCell<Gugudan> call(ListView<Gugudan> param) {
					// TODO Auto-generated method stub
					return new ListCell<Gugudan>() {
						protected void updateItem(Gugudan item, boolean empty) {
							super.updateItem(item, empty);
							if(item == null || empty) {
								setText(null);
							}else {
								setText(item.getDan()+"단");
							}
						}; {};
					};
				}
			}
		);
		
		gugudan.setButtonCell(
			new ListCell<Gugudan>() {
				protected void updateItem(Gugudan item, boolean empty) {
					super.updateItem(item, empty);
					if(item == null || empty) {
						setText(null);
					}else {
						setText(item.getDan() + "단");
						
					}
				}; {};
			}
		);
		
		gugudan.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Gugudan selData = gugudan.getSelectionModel().getSelectedItem();
				
				textArea.setText(selData.dan + "단\n");
				for (int i = 0; i < 10; i++) {
					int r = selData.dan * i;
					textArea.appendText(selData.dan + " x " + i + " = " + r + "\n");
				}
			}
		});
		
		vbox.getChildren().addAll(gugudan, textArea);
		
		Scene scene = new Scene(vbox, 300, 200);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("gugudan test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	class Gugudan {
		
		int dan;
		
		public Gugudan(int dan) {
			this.dan = dan;
		}

		public int getDan() {
			return dan;
		}

		public void setDan(int dan) {
			this.dan = dan;
		}
		
	}
}

