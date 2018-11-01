package basic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventTest extends Application {

	TextArea textArea = new TextArea();

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox(10);	//	괄호 속의 값은 spacing값
		root.setPadding(new Insets(10));
		
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(10));
		
		Button button1 = new Button("첫번째");
		Button button2 = new Button("두번째");
		Button button3 = new Button("세번째");
		Button button4 = new Button("네번째");
		
		
		// Event setting(이벤트 설정하기)
		
		/*
		 *	방법 1.	->	객체.setOn이벤트명() 이용하기
		 *			->	EventHandler인터페이스를 익명구현체 형식으로
		 *				구현한다.(이 인터페이스에는 handle()가 있는데
		 *				이 메서드에 처리할 내용을 기술한다.
		 */
		button1.setOnAction(
//				new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				// 이벤트를 처리할 내용
//				textArea.setText("첫번째 버튼 클릭입니다.\n이어서 출력할 내용입니다.");
//				textArea.appendText("\n이어서 출력할 내용입니다.");
//			}
//		}
			/* 람다식 */
			event -> {
				textArea.setText("람다식의 첫번째 버튼 이벤트처리입니다.");
			}
		);
		
		
		/*
		 * 방법 2.	->	객체.addEventHandler() 이용하기
		 * 				첫번째 매개변수로 이벤트 종류를 지정하고,
		 * 				두번째 매개변수는 EventHandler인터페이스를
		 * 				구현한 객체를 지정한다.	
		 */
		button2.addEventHandler(
			ActionEvent.ACTION,
//			new EventHandler<Event>() {
//				@Override
//				public void handle(Event event) {
//					// 처리할 내용
//					textArea.appendText("\n두번째 버튼");
//				}
//			}
			/* 람다식 */
			e -> {
				textArea.appendText("\n람다식으로 처리한 두번째 버튼 이벤트");
			}
		);
		
		
		/*
		 * 방법 3-2.	->	inner클래스로 작성한 class의 인스턴스를
		 * 				객체.setOn이벤트명() 나
		 * 				객체.addEventHandler()에 지정한다.
		 */
//		button3.setOnAction(new MyEventHandler());
		button3.addEventFilter(ActionEvent.ACTION, new MyEventHandler());
		
		
		/*
		 * 방법 4-2.	->	외부클래스로 작성한 class의 인스턴스를
		 * 				객체.setOn이벤트명()나
		 * 				객체.addEventHandler()에 지정한다.
		 */
		button4.setOnAction(new MyEvent(textArea));
		
		
		hbox.getChildren().addAll(button1, button2, button3, button4);
		
		root.getChildren().addAll(hbox, textArea);
		
		// 크기를 Scene객체 생성할 때 지정할 수 있다.
		Scene scene = new Scene(root, 300, 200);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Event Test");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/*
	 * 방법 3-1.	->	inner클래스로 EventHandler인터페이스를
	 * 				구현한 클래스를 작성한다.
	 */
	class MyEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			textArea.setText("inner클래스 이용한 방법 - 세번째 버튼");
		}
	}
}


/*
 * 방법 4-1.	->	외부의 독립된 형태의 객체를 이용하는 방법
 * 			->	이 객체도 EventHandler인터페이스를 구현하여 처리한다.
 */
class MyEvent implements EventHandler<ActionEvent> {
	TextArea ta;
	
	/* 생성자 */
	public MyEvent(TextArea ta) {
		this.ta = ta;
	}
	
	@Override
	public void handle(ActionEvent event) {
		ta.setText("inner클래스 이용한 방법 - 네번째 버튼\n");
	}
}


