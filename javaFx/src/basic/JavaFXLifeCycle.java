package basic;

import javafx.application.Application;
import javafx.stage.Stage;
/* 
 * Java의 GUI : AWT	-->	Swing	-->	JavaFx
 * 	
 * FX에서 사용되는 객체
 * - Stage(무대)객체	-->	window창을 나타내는 객체
 * - Scene(장면)객체	-->	무대에는 하나의 장면이 배치된다.
 * 
 * - 실행순서
 * 		main()  -->  launch() 호출  -->  객체생성(생성자 호출)
 * 		-->  init()  -->  start()  -->  GUI환경에서 작업 진행
 * 		-->  종료  -->  stop()
 * 
 * - 종료되는 경우
 * 		1) 마지막 윈도우(Stage)가 닫힐 때
 * 		2) 마지막 윈도우(Stage)의 close()가 호출 될 때
 * 		3) 자바코드의 Platform.exit()가 호출 될 때
 * 			(단, System.exit(0)도 프로그램을 종료하지만 이 때는
 * 			 stop()를 자동으로 호출해주지 않는다.)
 */

/*
 * Scene을 구성하는 객체들
 * 
 * 컨테이너 객체들	-->	다른 객체들을 포함하는 객체들
 * 컨트롤 객체들	-->	개별적으로 필요에의해 사용되는 객체들(버튼, Label등)
 */
public class JavaFXLifeCycle extends Application {
	
	/* 생성자 */
	public JavaFXLifeCycle() {
		System.out.println(Thread.currentThread().getName() + " : 생성자");
	}
	
	/* init 메서드 */
	@Override
	public void init() throws Exception {
		System.out.println(Thread.currentThread().getName() + " : init()");
		super.init();
	}
	
	/* start 메서드 */
	@Override
	public void start(Stage primaryStage) {
		// Stage에 나타날 Scnen을 구성하여 창(Stage)을 보여주는 메서드
		System.out.println(Thread.currentThread().getName() + " : start()");
		primaryStage.show();
	}
	
	/* stop 메서드 */
	@Override
	public void stop() throws Exception {
		// 프로그램이 종료될 때 필요한 자원 정리등을 처리하는 메서드
		System.out.println(Thread.currentThread().getName() + " : stop()");
		super.stop();
	}
	
	/* main 메서드 */
	public static void main(String[] args) {
		System.out.println(
				Thread.currentThread().getName() + " : main()");
		launch(args);
	}
	
}

