package d_thread;

public class ThreadTest16 {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		WorkerThread th1 = new WorkerThread("Test1", sObj);
		WorkerThread th2 = new WorkerThread("Test2", sObj);
		
		th1.start();
		th2.start();
		
	}
}


class WorkerThread extends Thread{
	private ShareObject sObj;
	
	public WorkerThread(String name, ShareObject sObj){
		super(name);	//	쓰레드의 이름 설정
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++){
			sObj.add();
		}
	}
	
}


/* 공용으로 사용할 클래스 */
class ShareObject {
	
	/* 방법 1. 메서드에 동기화 설정 */
//	private synchronized int sum = 0;
	private int sum = 0;
	
	/* 동기화 하기 */
	public void add(){
		
		/* 방법 2. 동기화 블럭 */
		synchronized (this) {
			int n = sum;
			n += 10;
			sum = n;
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		}
	}
}

