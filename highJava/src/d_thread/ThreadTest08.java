package d_thread;

public class ThreadTest08 {
	public static void main(String[] args) {
		Thread th1 = new ShowUpperCase();
		Thread th2 = new ShowLowerCase();
		
		/**
		 * 우선순위 설정 ㅡ> start()를 호출하기 전에 설정한다.
		 */
		th1.setPriority(9);
		th2.setPriority(3);
		
		System.out.println("th1의 우선순위 : " + th1.getPriority());
		System.out.println("th2의 우선순위 : " + th2.getPriority());
		
		th1.start();
		th2.start();
		
	}
}


/* 대문자를 출력하는 쓰레드 */
class ShowUpperCase extends Thread{
	@Override
	public void run() {
		for(char c = 'A'; c <= 'Z'; c++){
			System.out.println(c);
			
			// 시간에 간격을 두기 위한 반복문
			for(long i = 1L; i <= 100000000L; i++){}
		}
	}
}


/* 소문자를 출력하는 쓰레드 */
class ShowLowerCase extends Thread{
	@Override
	public void run() {
		for(char c = 'a'; c <= 'z'; c++){
			System.out.println(c);
			
			// 시간에 간격을 두기 위한 반복문
			for(long i = 1L; i <= 100000000L; i++){}
		}
	}
}