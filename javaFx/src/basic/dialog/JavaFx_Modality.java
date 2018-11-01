package basic.dialog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JavaFx_Modality extends Application {

	@Override
	public void start(Stage primaryStage) {
		Button btnNONE = new Button("Open NONE Dialog");
		Button btnAPP_MODAL = new Button("Open APPLICATION_MODAL Dialog");
		Button btnWIN_MODAL = new Button("Open WINDOW_MODAL Dialog");
		
		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(btnNONE, btnAPP_MODAL, btnWIN_MODAL);
		
		
		// NONE
		btnNONE.setOnAction(e -> {
			Stage dialogNONE = new Stage();
			dialogNONE.initModality(Modality.NONE);
			dialogNONE.initOwner(primaryStage);
			VBox none_vbox = new VBox(10);
			Text none_txt1 = new Text("NONE Dialog");
			Text none_txt2 = new Text("모달 기능이 없는 모달리스 창");
			none_vbox.getChildren().addAll(none_txt1, none_txt2);
			none_vbox.setAlignment(Pos.CENTER);
			none_vbox.setPadding(new Insets(10));
			
			Scene sceneNone = new Scene(none_vbox, 500, 90);
			dialogNONE.setScene(sceneNone);
			dialogNONE.setTitle("NONE Dialog");
			dialogNONE.show();
		});
		
		btnAPP_MODAL.setOnAction(e -> {
			Stage dialogAPP = new Stage();
			dialogAPP.initModality(Modality.APPLICATION_MODAL);
			dialogAPP.initOwner(primaryStage);
			VBox app_vbox = new VBox(10);
			Text app_txt1 = new Text("APPLICATION_MODAL Dialog");
			Text app_txt2 = new Text("부모창 및 다른 창을 선택하지 못하는 모달 창");
			app_vbox.getChildren().addAll(app_txt1, app_txt2);
			app_vbox.setAlignment(Pos.CENTER);
			app_vbox.setPadding(new Insets(10));
			
			Scene sceneApp = new Scene(app_vbox, 500, 90);
			dialogAPP.setScene(sceneApp);
			dialogAPP.setTitle("APPLICATION_MODAL Dialog");
			dialogAPP.show();
		});
		
		btnWIN_MODAL.setOnAction(e -> {
			Stage dialogWIN = new Stage();
			dialogWIN.initModality(Modality.WINDOW_MODAL);
			dialogWIN.initOwner(primaryStage);
			VBox win_vbox = new VBox(10);
			Text win_txt1 = new Text("WINDOW_MODAL Dialog");
			Text win_txt2 = new Text("부모창을 선택하지 못하는 모달 창");
			win_vbox.getChildren().addAll(win_txt1, win_txt2);
			win_vbox.setAlignment(Pos.CENTER);
			win_vbox.setPadding(new Insets(10));
			
			Scene sceneWin = new Scene(win_vbox, 500, 90);
			dialogWIN.setScene(sceneWin);
			dialogWIN.setTitle("WINDOW_MODAL Dialog");
			dialogWIN.show();
		});
		
		
		Scene scene = new Scene(vbox, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Modality Test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
