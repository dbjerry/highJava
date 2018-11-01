package programmers;

public class DaemonThread implements Runnable{

	@Override
	public void run() {
		while(true){
			System.out.println("데몬 쓰레드가 실행중입니다.");
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Thread thread = new Thread(new DaemonThread());
		
		thread.setDaemon(true);
		thread.start();
		
		try {
			thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("메인 쓰레드가 종료됩니다.");
	}
}
