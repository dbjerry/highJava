package d_thread;
/**
 * 쓰레드 멈추기 예제
 * 
 * Thread의 stop()를 호출하면 쓰레드가 바로 멈춘다.
 * 이 때 사용하던 자원을 정리하지 못하고 쓰레드가 종료되어서
 * 쓰레드가 교착 상태에 빠지거나 다른 프로그램에 영향을 줄 수 있다.
 * 그래서 현재는 stop()는 비추천한다.
 */
public class ThreadTest14 {
	public static void main(String[] args) {
		
//		ThreadStopEx01 th = new ThreadStopEx01();
//		th.start();
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		th.stop();	//	stop()를 사용해서 확인한다.
		
		
		ThreadStopEx02 th2 = new ThreadStopEx02();
		th2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		th2.interrupt();
		
	}
}

/**
 * 방법 1
 * 반복문에 변수를 사용하고, 변수값을 변경해서 쓰레드가 멈추도록 한다.
 */
class ThreadStopEx01 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop){
		this.stop = stop;
	}

	@Override
	public void run() {
		while(!stop){
			System.out.println("쓰레드 실행 중...");
		}
		System.out.println("자원 정리하기");
		System.out.println("실행 종료");
	}
}

/**
 * 방법 2
 * interrupt()를 이용하는 방법
 */
class ThreadStopEx02 extends Thread{
	
	@Override
	public void run() {

//		/* 방법 2-1 */
//		try {
//			while(true){
//				System.out.println("쓰레드 실행 중...");
//				Thread.sleep(1);
//			}
//		} catch (InterruptedException e) {
//			
//		}
//		System.out.println("자원 정리중...");
//		System.out.println("실행 끝.");
	
		
		/**
		 * 방법 2-2
		 * interrupt()가 호출되었는지 검사하는 방법
		 */
		while(true){
			System.out.println("쓰레드 실행중...");
			
//			/* interrupt()가 호출되었는지 검사방법 1 */
//			/* 인스턴스 객체용 메서드를 이용하여 검사한다. */
//			if(this.isInterrupted()){
//				break;
//			}
			
			
			/**
			 * interrupt()가 호출되었는지 검사방법 2
			 * Thread의 정적메서드인 interrupted()를 이용하여 검사한다.
			 * interrupt()가 호출되면 true를 반환한다.
			 */
			if(Thread.interrupted()){
				break;
			}
			
			
			
			
		}
		System.out.println("자원 정리중...");
		System.out.println("실행 끝.");
	}
}	


