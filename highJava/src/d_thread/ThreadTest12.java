package d_thread;
/**
 * 쓰레드의 상태를 출력하는 예제
 */
public class ThreadTest12 {
	public static void main(String[] args) {
		StatePrintThread spt = new StatePrintThread(new TargetThread());
		spt.start();
	}
}


/**
 * 쓰레드의 상태를 출력하는 클래스
 */
class StatePrintThread extends Thread{
	private Thread targetThread;	//	상태를 출력할 쓰레드를 저장할 변수

	public StatePrintThread(Thread targetThread) {
		super();
		this.targetThread = targetThread;
	}

	@Override
	public void run() {
		while(true){
			
			/* 쓰레드의 현재 상태값 구하기 */
			Thread.State state = targetThread.getState();
			System.out.println("타겟 쓰레드의 상태값 : " + state);
			
			/* 쓰레드 객체가 생성되었지만 실행중이 아닐 때 */
			if(state == Thread.State.NEW){	//	NEW상태인지 검사
				targetThread.start();
			}
			
			/* 타겟 쓰레드가 종료 상태이면 현재 쓰레드를 끝낸다 */
			if(state == Thread.State.TERMINATED){
				break;
			}
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


/**
 * Target Thread class
 */
class TargetThread extends Thread{

	@Override
	public void run() {
		for(long i = 1L; i <= 2_000_000_000L; i++){}	//	시간 지연용 반복문
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(long i = 1L; i <= 2_000_000_000L; i++){}	//	시간 지연용 반복문
		
	}
}


