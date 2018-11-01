package basic;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioButtonTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		// 라디오 버튼들을 묶음으로 처리할 객체
		ToggleGroup group = new ToggleGroup();
		
		ImageView icon = new ImageView();
		
		RadioButton radio1 = new RadioButton("HOME");
		radio1.setToggleGroup(group);	//	그룹 설정
		radio1.setUserData("HOME");		//	선택했을 때의 값을 나타내기 위해 설정
		
		RadioButton radio2 = new RadioButton("Calendar");
		radio2.setToggleGroup(group);
		radio2.setUserData("Calendar");
		
		RadioButton radio3 = new RadioButton("Contacts");
		radio3.setToggleGroup(group);
		radio3.setUserData("Contacts");
		
		// 그룹 내에서 RadioButton 중 하나가 선택되었을 때 처리하기
		group.selectedToggleProperty().addListener(
					new ChangeListener<Toggle>() {
						public void changed(javafx.beans.value.ObservableValue<? 
								extends Toggle> observable, Toggle oldValue, Toggle newValue) {
							if(group.getSelectedToggle().getUserData() != null) {
								String url = group.getSelectedToggle().getUserData().toString();
								Image img = new Image(getClass().getResourceAsStream(
										"images/" + url + ".jpg"));
								icon.setImage(img);
							}
						}; {};
					}
				);
		
		radio2.setSelected(true);
		
		HBox hbox = new HBox();
		VBox vbox = new VBox();
		
		vbox.getChildren().addAll(radio1, radio2, radio3);
		vbox.setSpacing(10);
		
		hbox.getChildren().addAll(vbox, icon);
		hbox.setSpacing(30);
		hbox.setPadding(new Insets(15));
		
		Scene scene = new Scene(hbox, 250, 200);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Radio Button Test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
