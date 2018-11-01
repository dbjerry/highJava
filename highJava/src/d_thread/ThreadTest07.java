package d_thread;

import javax.swing.JOptionPane;

/**
 * 문제) 컴퓨터와 가위바위보 게임을 진행하는 프로그램 작성하기
 * 
 * 		컴퓨터의 가위바위보는 난수를 이용하여 구하고
 * 		사용자의 입력은 showInputDialog()를
 * 		이용하여 입력받는다.
 * 
 * 		입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 		5초동안 입력이 없으면 게임을 진 것으로 처리한다.
 * 
 * 		5초 안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과예시)
 * === 결	과 ===
 * COM : 가위
 * USER : 바위
 * 결  과  : USER가 이겼습니다.
 */
public class ThreadTest07 {
	public static boolean chk = false;
	
	public static void main(String[] args) {
		Thread th1 = new Input();
		Thread th2 = new Count();
		
		th1.start();
		th2.start();
	}
}

/* 데이터를 입력받는 쓰레드 */
class Input extends Thread{
	
	@Override
	public void run() {
		int random = (int)(Math.random()*2);
		int inputNum = 0;
		String input = JOptionPane.showInputDialog("\"가위, 바위, 보\" 중 하나를 입력하세요.");
		/* 입력이 완료되면 inputChk변수값을 변경한다. */
		ThreadTest07.chk = true;
		/* 값이 입력된 이후의 작업을 수행한다. */
		if(input.equals("가위")){
			inputNum = 0;
		}else if(input.equals("바위")){
			inputNum = 1;
		}else if(input.equals("보")){
			inputNum = 2;
		}
		
		switch(inputNum){
		
		case 0:
			if(random==0){
				System.out.println("=== 결  과 ===\nCOM : 가위\nUSER : " + input + "\n결과 : 비겼습니다.");
			}else if(random==1){
				System.out.println("=== 결  과 ===\nCOM : 바위\nUSER : " + input + "\n결과 : 졌습니다.");
			}else if(random==2){
				System.out.println("=== 결  과 ===\nCOM : 보\nUSER : " + input + "\n결과 : 이겼습니다.");
			}
			break;
				
		case 1:
			if(random==1){
				System.out.println("=== 결  과 ===\nCOM : 바위\nUSER : " + input + "\n결과 : 비겼습니다.");
			}else if(random==2){
				System.out.println("=== 결  과 ===\nCOM : 보\nUSER : " + input + "\n결과 : 졌습니다.");
			}else if(random==0){
				System.out.println("=== 결  과 ===\nCOM : 가위\nUSER : " + input + "\n결과 : 이겼습니다.");
			}
			break;
			
		case 2:
			if(random==2){
				System.out.println("=== 결  과 ===\nCOM : 보\nUSER : " + input + "\n결과 : 비겼습니다.");
			}else if(random==0){
				System.out.println("=== 결  과 ===\nCOM : 가위\nUSER : " + input + "\n결과 : 졌습니다.");
			}else if(random==1){
				System.out.println("=== 결  과 ===\nCOM : 바위\nUSER : " + input + "\n결과 : 이겼습니다.");
			}
			break;
		}
	}
}

/* 카운트다운(5초) 클래스 */
class Count extends Thread{
	public void run(){
		for(int i = 5; i > 0; i--){
			System.out.println(i);
			if(ThreadTest07.chk == true){
				return;
			}
			try{
				sleep(1000);
			}catch(Exception e){}
		}
	}
}