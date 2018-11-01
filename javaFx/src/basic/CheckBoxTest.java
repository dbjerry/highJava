package basic;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CheckBoxTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		String[] names = new String[] {"Security", "Project", "Chart"};
		Image[] images = new Image[names.length];
		ImageView[] icons = new ImageView[names.length];
		CheckBox[] chkboxes = new CheckBox[names.length];
		
		Rectangle rect = new Rectangle(90,30);	//	4각형 그리기
		rect.setArcHeight(10);
		rect.setArcWidth(10);
		rect.setFill(Color.rgb(41, 41, 41));
		
		Button button = new Button("Done");
		
		for(int i = 0; i < names.length; i++) {
			final Image img = images[i] = new Image(
					getClass().getResourceAsStream(
							"./images/" + names[i] + ".png"));
			final ImageView icon = icons[i] = new ImageView();
			chkboxes[i] = new CheckBox(names[i]);
			
			// CheckBox를 클릭해서 상태값이 변경되었을 때 처리하기
			chkboxes[i].selectedProperty().addListener(
				new ChangeListener<Boolean>() {
					public void changed(javafx.beans.value.ObservableValue<? 
							extends Boolean> observable, Boolean oldValue, 
									Boolean newValue) {
						// oldValue ==> 변경되기 전 상태값
						// new Value ==> 변경된 후 상태값
						
						// 체크박스가 체크되면 해당 이미지를 보이게 한다.
						icon.setImage(newValue ? img : null);
					}; {};
				}
			);
		}
		
		button.setOnAction( e -> {
			// isSelected()는 현재 체크가 되어있는지 아닌지 확인
			// 현재 체크된 상태이면 true
			// 체크가 안된 상태이면 false
			if(chkboxes[0].isSelected() == true) {
				System.out.println("첫번째 CheckBox 선택");
			}else {
				System.out.println("첫번째 CheckBox 해제");
			}
			
			// setSelected() =>	true를 설정하면 체크되고
			// 					false를 설정하면 체크가 해제된다.
//			chkboxes[1].setSelected(true);
			chkboxes[1].setSelected(!chkboxes[0].isSelected());
		});
		
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(chkboxes);
		vbox.getChildren().add(button);
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(icons);
		hbox.setPadding(new Insets(0,0,0,5));
		
		StackPane stack = new StackPane();
		stack.getChildren().addAll(rect, hbox);
		StackPane.setAlignment(rect, Pos.TOP_CENTER);
		
		HBox root = new HBox();
		root.setPadding(new Insets(20, 10, 10, 20));
		root.setSpacing(40);
		root.getChildren().addAll(vbox, stack);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("CheckBox Test");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

