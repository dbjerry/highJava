/**
 * Sample Skeleton for 'root.fxml' Controller Class
 */

package basic.animation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class RootController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn_login"
    private Button btn_login; // Value injected by FXMLLoader

    public void handleBtnLogin(ActionEvent e) {
    	try {
			Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
			
			// 현재 컨트롤을 이용하여 root컨테이너 구하기
			StackPane root = (StackPane)btn_login.getScene().getRoot();
			
			root.getChildren().add(login);
			
			/*
			// Fade효과 애니메이션 (불투명도를 이용하여 나타낸다.)
			// 값의 범위 : 투명 (0.0) ~ 불투명 (1.0)
			login.setOpacity(0);  //  불투명도 설정(시작값)
			*/
			
			/*
			// 이동 애니메이션
			
			// X축으로 평행이동할 값 설정
			login.setTranslateX(350);
			*/
			
			/*
			// 회전 애니메이션
			login.setRotate(180);
			*/
			
			// 확대, 축소 배율
			login.setScaleX(0);
			login.setScaleY(0);
			
			
			// KeyFrame에 설정된 내용대로 애니메이션을 진행시키는 객체
			Timeline timeline = new Timeline();
			
			// 타겟속성
//			KeyValue keyValue = new KeyValue(login.translateXProperty(), 0);
//			KeyValue keyValue = new KeyValue(login.opacityProperty(), 1);
//			KeyValue keyValue = new KeyValue(login.rotateProperty(), 0);
			KeyValue keyValue1 = new KeyValue(login.scaleXProperty(), 1);
			KeyValue keyValue2 = new KeyValue(login.scaleYProperty(), 1);
			
			// 지속시간과 KeyValue를 설정하기
			// 형식) new KeyFrame(지속시간, KeyValue객체);
			// 		new keyFrame(지소기간, 애니메이션이 종료된 후 처리될 이벤트, KeyValue객체) ;
//			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue1, keyValue2);
			
			// TimeLine에 KeyFrame 추가
			timeline.getKeyFrames().add(keyFrame);
			
			// 애니메이션 실행
			timeline.play();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btn_login != null : "fx:id=\"btn_login\" was not injected: check your FXML file 'root.fxml'.";

        btn_login.setOnAction(e -> handleBtnLogin(e));
        
    }
}

