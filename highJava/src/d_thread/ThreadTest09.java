package d_thread;
/**
 * 데몬쓰레드 연습(자동저장 기능 구현)
 */
public class ThreadTest09 {
	public static void main(String[] args) {
		AutoSaveThread autoSave = new AutoSaveThread();
		
		/* start()호출 전에 데몬쓰레드로 설정한다. */
		autoSave.setDaemon(true);
		
		autoSave.start();
		
		try{
			for(int i = 1; i <= 20; i++){
				System.out.println(i);
				Thread.sleep(1000);
			}
		}catch(Exception e){
			
		}
		System.out.println("Main Thread End.");
		
	}
}


/**
 * Auto Save Thread
 * (3초에 한 번씩 저장하는 Thread)
 */
class AutoSaveThread extends Thread{
	
	/* Save Method */
	public void save(){
		System.out.println("작업 내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while(true){
			try{
				// wait 3sec
				Thread.sleep(3000);
			}catch(Exception e){
				
			}
			save();
		}
	}
	
}