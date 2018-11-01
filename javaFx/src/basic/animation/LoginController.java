/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

package basic.animation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LoginController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="root_login"
    private BorderPane root_login; // Value injected by FXMLLoader

    @FXML // fx:id="btn_main"
    private Button btn_main; // Value injected by FXMLLoader

    public void handleBtnMain(ActionEvent e) {
		try {
//			Parent main = FXMLLoader.load(getClass().getResource("root.fxml"));
//			
//			StackPane root = (StackPane)btn_main.getScene().getRoot();
//			
//			root.getChildren().add(main);
//			
//			main.setTranslateY(500);
//			
//			Timeline timeline = new Timeline();
//			
//			KeyValue keyValue = new KeyValue(main.translateYProperty(), 0);
//			
//			KeyFrame keyFrame = new KeyFrame(Duration.millis(400), keyValue);
//			
//			timeline.getKeyFrames().add(keyFrame);
//			
//			timeline.play();
			StackPane root = (StackPane)btn_main.getScene().getRoot();
			
			root_login.setTranslateX(0);
			
			Timeline timeline = new Timeline();
			
			KeyValue keyValue = new KeyValue(root_login.translateXProperty(), 0);
			
			KeyFrame keyFrame = new KeyFrame(
							Duration.millis(400), 
							new EventHandler<ActionEvent>() {
								// 애니메이션이 종료되면 처리될 이벤트 설정
								@Override
								public void handle(ActionEvent event) {
									// 애니메이션을 마친 객체를 StackPane에서 제거한다.
									root.getChildren().remove(root_login);
								}
							},
							keyValue);
			
			timeline.getKeyFrames().add(keyFrame);
			
			timeline.play();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert root_login != null : "fx:id=\"root_login\" was not injected: check your FXML file 'login.fxml'.";
        assert btn_main != null : "fx:id=\"btn_main\" was not injected: check your FXML file 'login.fxml'.";
        
    	btn_main.setOnAction(e -> handleBtnMain(e));
    	
    }
}

