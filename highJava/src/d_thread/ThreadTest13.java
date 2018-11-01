package d_thread;
/**
 * 양보(yield()) 예제
 */
public class ThreadTest13 {
	public static void main(String[] args) {
		
		ThreadYield th1 = new ThreadYield("1번 쓰레드");
		ThreadYield th2 = new ThreadYield("2번 쓰레드");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("11111111. ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		th1.work = false;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("22222222. ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		th1.work = true;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		th1.stop = true;
		th2.stop = true;
		
	}
}



/**
 * yield() 연습용 쓰레드
 */
class ThreadYield extends Thread{
	public boolean stop = false;
	public boolean work = true;
	
	public ThreadYield(String name){
		super(name);	//	쓰레드의 이름을 설정한다.
	}

	@Override
	public void run() {
		while(!stop){	//	stop이 true이면 반복문을 멈춘다.
			if(work){
				/**
				 * getName()는 Thread객체의 name속성값을 가져온다.
				 * 즉, 쓰레드의 이름을 반환한다.
				 */
				System.out.println(getName() + "작업중...");
			}else{
				System.out.println(getName() + "양보..");
				Thread.yield();
				System.out.println(getName() + "양보 후...");
			}
		}
		System.out.println(getName() + "종료.");
	}
}

