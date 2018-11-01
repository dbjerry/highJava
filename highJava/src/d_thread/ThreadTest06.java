package d_thread;

import javax.swing.JOptionPane;

public class ThreadTest06 {
	/**
	 * 입력여부를 확인하기 위한 변수를 선언
	 * 모든 쓰레드에서 공통으로 사용할 변수
	 */
	public static boolean inputChk = false;
	
	public static void main(String[] args) {
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
		
	}
}


/* 데이터를 입력받는 쓰레드 */
class DataInput extends Thread{
	
	int random1 = (int)(Math.random()*100);
	int random2 = (int)(Math.random()*100);
	
	@Override
	public void run() {
		String input = JOptionPane.showInputDialog(random1 + " + " + random2 + " = "
				+ "정답을 입력하세요.");
		
		/* 입력이 완료되면 inputChk변수값을 변경한다. */
		ThreadTest06.inputChk = true;
		
		/* 값이 입력된 이후의 작업을 수행한다. */
		System.out.println("입력값 : " + input);
	}
}


class CountDown extends Thread{

	@Override
	public void run() {
		for(int i = 10; i > 0; i--){
			
			System.out.println(i);
			/* 입력이 완료되면 쓰레드를 종료시킨다. */
			if(ThreadTest06.inputChk == true){
				
				return; // run() 끝내기 즉, 쓰레드 끝내기
			}
			
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				
			}
		} // for문
		
		//	위 반복문이 정상적으로 끝나면 제한시간동안
		//	입력을 못했다는 말이 되므로 이 때의 처리를 한다.
		System.out.println("시간이 초과됐습니다.");
		
	}
}

