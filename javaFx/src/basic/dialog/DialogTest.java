package basic.dialog;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DialogTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		HBox root = new HBox(10);
		root.setPadding(new Insets(10));
		
		Button btnFileOpen = new Button("Open FileChooser 실행");
		Button btnFileSave = new Button("Save FileChooser 실행");
		Button btnDirectory = new Button("DirectoryChooser 실행");
		Button btnPopup = new Button("Popup 실행");
		Button btnCustom = new Button("Custom Dialog 실행");
		
		root.getChildren().addAll(btnFileOpen, btnFileSave, btnDirectory, btnPopup, btnCustom);
		
		
		// 파일 열기의 파일 선택 창
		btnFileOpen.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();

			// 확장자 종류에 따른 파일 목록 보이게 선택하는 옵션 설정
			// Chooser는 파일의 위치정보와 정보만 가져옴
			fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text File", "*.txt"),
				new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"),
				new ExtensionFilter("Audio File", "*.wav"),
				new ExtensionFilter("All Files", "*.*")
			);
			
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			
			if(selectedFile != null) {
				System.out.println("선택한 파일 : " + selectedFile.getPath());
			}
		});
		
		// 파일 저장의 파일 선택창
		btnFileSave.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			
			fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("All files", "*.*")
			);
			
			File selectedFile = fileChooser.showSaveDialog(primaryStage);
			
			if(selectedFile != null) {
				System.out.println("선택한 파일(저장) : " + selectedFile.getPath());
			}
		});
		
		// 디렉토리 선택 창
		btnDirectory.setOnAction(e -> {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			
			File selectedDir = directoryChooser.showDialog(primaryStage);
			
			if(selectedDir != null) {
				System.out.println("선택한 폴더 : " + selectedDir.getPath());
			}
		});
		
		// Popup창 --> 작업 중 간단한 메시지를 보여주는 창
		btnPopup.setOnAction(e -> {
			Popup popup = new Popup();
			
			HBox popRoot = new HBox();
			popRoot.setAlignment(Pos.CENTER_LEFT);
			popRoot.setStyle("-fx-background-color: black; -fx-background-radius: 20");
			
			ImageView imgView = new ImageView(
				new Image(
					getClass().getResource("../images/ok.png").toString())
			);
			
			// 이미지 뷰의 크기 설정
			imgView.setFitWidth(30);
			imgView.setFitHeight(30);
			
			imgView.setOnMouseClicked(ee -> {
				popup.hide();	//	popup창 닫기
			});
			
			Label lblMessage = new Label();
			lblMessage.setText("메시지가 왔습니다.");
			lblMessage.setStyle("-fx-text-fill: white;");
			HBox.setMargin(lblMessage, new Insets(0,5,0,5));
			
			popRoot.getChildren().addAll(imgView, lblMessage);
			
			popup.getContent().add(popRoot);
			popup.setAutoHide(true);	//	창 외부 아무곳이나 누르면 창이 닫힌다.
			popup.show(primaryStage);
		});

		// 사용자 정의 창 띄우기
		btnCustom.setOnAction(e -> {
			
			try {
//				Stage dialog = new Stage(StageStyle.UTILITY);
				Stage dialog = new Stage(StageStyle.DECORATED);
//				Stage dialog = new Stage(StageStyle.UNIFIED);	//	WINDOW 10 사용자(현재까지의 공통점)는 내용이 출력되지 않음
				
				//	MODAL창	-->	자식창이 닫히기 전에는 부모창을 사용하지
				//				못하는 창을 말한다.
				dialog.initModality(Modality.WINDOW_MODAL);	//	실행된 프로그램에 한하여 부모창을 컨트롤 할 수 없음.
//				dialog.initModality(Modality.APPLICATION_MODAL);
//				dialog.initModality(Modality.NONE);	//	부모창을 컨트롤 할 수 있음
				dialog.initOwner(primaryStage);	//	부모창 지정
				
				// 자식창 디자인 정보 읽어오기
				Parent childRoot = FXMLLoader.load(
						getClass().getResource("custom.fxml"));
				
//				// 부모창에서 fxml로 만든 자식창의 컨트롤 객체 구하기
//				// fxml문서의 id값을 이용하여 구할 수 있다.
				Label lblMessage = (Label) childRoot.lookup("#lblMsg");
				
				lblMessage.setText("진이 굿쟙");
				
				Scene childScene = new Scene(childRoot);
				
				dialog.setScene(childScene);
				dialog.setResizable(false);	//	창 크기 변경 불가
				dialog.show();
				
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
			
		});
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Dialog Test");
		primaryStage.setHeight(200);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
