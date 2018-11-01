package d_thread;

public class ThreadTest03 {
	/* Thread의 처리시간 체크해보기 */
	public static void main(String[] args) {
		Thread th = new Thread(new MyRunner());
		
		/**
		 * 1970년 1월 1일 0시 0분 0초(표준시간)로 부터
		 * 현재까지 경과한 시간을 밀리세컨드(1/1000초)단위로 나타낸다.
		 */
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try{
			/* 현재 쓰레드가 끝날 때까지 기다린다. */
			th.join();
		}catch(InterruptedException e){
			
		}
		System.out.println("실행시간 main() : " + (System.currentTimeMillis() - startTime));
	}
}

class MyRunner implements Runnable{
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		long sum = 0L;
		for(long i = 1L; i <= 1_000_000_000L; i++){
			sum += i;
		}
		System.out.println("합계 : " + sum);
		System.out.println("실행시간 run() : " + (System.currentTimeMillis() - startTime));
	}
}