package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BorderPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		BorderPane root = new BorderPane();
		root.setPrefSize(300, 200);
		
		ToolBar toolbar = new ToolBar(
					new Button("First"),
					new Button("Second")
				);
		
		TextArea textArea = new TextArea();
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10));
		hBox.setSpacing(15);
		hBox.setAlignment(Pos.CENTER);
		
		TextField fieldData = new TextField();
		
		// controls style setting
		fieldData.setStyle("-fx-background-color:powderblue; -fx-text-fill:blue; -fx-font-family:consolas;");
		
		Button buttonOk = new Button("DONE");
		
		hBox.getChildren().addAll(fieldData, buttonOk);
		
		root.setTop(toolbar);		//	Top영역에 추가
		root.setCenter(textArea);	//	Center영역에 추가
		root.setBottom(hBox);		//	Bottom영역에 추가
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("BorderPane Test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
