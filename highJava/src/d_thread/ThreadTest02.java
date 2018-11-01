package d_thread;

public class ThreadTest02 {
	public static void main(String[] args) {
		
		/**
		 * 방법1
		 * Thread클래스를 상속한 class의 인스턴스를 생성한 후
		 * 이 인스턴스의 start()를 호출한다.
		 */
		MyThread1 th1 = new MyThread1();
		//th1.start();
		
		
		/**
		 * 방법2
		 * Runnable인터페이스를 구현한 class의 인스턴스를 생성한 후
		 * 이 인스턴스를 Thread객체의 인스턴스를 생성할 때
		 * Thread 생성자의 매개변수로 넘겨준다.
		 * 이 때 생성된 Thread 인스턴스의 start()를 호출한다.
		 */
		MyThread2 r = new MyThread2();
		Thread th2 = new Thread(r);
		
		
		/**
		 * 방법3
		 * 익명 구현체를 이용하는 방법
		 * Runnable인터페이스를 구현한 익명 구현체를
		 * Thread인스턴스를 생성할 때 Thread 생성자의
		 * 매개변수로 넘겨준다.
		 */
		Thread th3 = new Thread(
					new Runnable(){
						@Override
						public void run() {
							for(int i = 0; i < 200; i++){
								System.out.print("*");
								try{
									Thread.sleep(100);
								}catch(InterruptedException e){
									
								}
							} // for
						} // run
					}/* Runnable */);
		
		
		th1.start();
		th2.start();
		th3.start();
	}
}

/* Thread를 상속받는 class */
class MyThread1 extends Thread{
	// run()에 쓰레드가 처리할 내용을 기술한다.
	@Override
	public void run() {
		for(int i = 0; i < 200; i++){
			System.out.print("-");
			try{
				// Thread.sleep(시간)은
				// 주어진 시간동안 작업을 잠시 멈춘다.
				// '시간'은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);
			}catch (InterruptedException e){
				
			}
		}
	}
}

/* Runnable을 구현한 class */
class MyThread2 implements Runnable{
	@Override
	public void run() {
		for(int i = 0; i < 200; i++){
			System.out.print("|");
			try{
				Thread.sleep(100);
			}catch (InterruptedException e){
				
			}
		}
	}
}