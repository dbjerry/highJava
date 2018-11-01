package d_thread;
/**
 * wait(), notify()를 이용한 두 쓰레드가
 * 번갈아 한번씩 실행하는 예제
 */
public class ThreadTest20 {
	public static void main(String[] args) {
		WorkObject work = new WorkObject();
		
		ThreadA thA = new ThreadA(work);
		ThreadB thB = new ThreadB(work);
		
		thA.start();
		thB.start();
		
	}
}


/* methodA()만 호출하는 쓰레드 */
class ThreadA extends Thread{
	private WorkObject work;
	public ThreadA(WorkObject work){
		this.work = work;
	}
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			work.methodA();
		}
	}
}


/* methodB()만 호출하는 쓰레드 */
class ThreadB extends Thread{
	private WorkObject work;
	public ThreadB(WorkObject work){
		this.work = work;
	}
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			work.methodB();
		}
	}
}


/* 공용으로 사용할 객체 */
class WorkObject{
	public synchronized void methodA(){
		System.out.println("methodA()에서 실행 중...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
//			e.printStackTrace();
		}
		
	}
	public synchronized void methodB(){
		System.out.println("methodB()에서 실행 중...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
//			e.printStackTrace();
		}
	}
}

