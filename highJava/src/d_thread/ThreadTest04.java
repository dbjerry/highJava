package d_thread;

public class ThreadTest04 {
	/**
	 * 1~20억까지의 합계를 구하는데 걸린 시간 체크하기
	 * 
	 * 전체 합계를 구하는 작업을 단독으로 했을 때와
	 * 여러 쓰레드로 분할해서 작업할 때의 시간을 확인해보기
	 */
	public static void main(String[] args) {
		SumThread st = new SumThread(1L, 2_000_000_000L);
		
		SumThread[] stArr = new SumThread[]{
			new SumThread(1L, 500_000_000L),
			new SumThread(500_000_001L, 1_000_000_000L),
			new SumThread(1_000_000_001L, 1_500_000_000L),
			new SumThread(1_500_000_001L, 2_000_000_000L),
		};
		
		/* 단독으로 처리 했을 때 */
		long startTime = System.currentTimeMillis();
		st.start();
		
		try{
			st.join();
		}catch(InterruptedException e){
			
		}
		System.out.println("단독 처리의 실행시간 : " + (System.currentTimeMillis() - startTime));
		System.out.println();
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		
		/* 여러 쓰레드가 협력해서 처리 했을 경우 */
		startTime = System.currentTimeMillis();
		
		for(int i = 0; i < stArr.length; i++){
			stArr[i].start();
		}
		
		for(int i = 0; i < stArr.length; i++){
			try{
				stArr[i].join();
			}catch(InterruptedException e){
				
			}
		}
		System.out.println("협력 처리의 실행시간 : " + (System.currentTimeMillis() - startTime));
		System.out.println();
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
	}
}


/* 시작값부터 종료값까지의 합계를 구하는 쓰레드 */
class SumThread extends Thread {
	private long start, end;
	
	/* 생성자 */
	public SumThread(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		long sum = 0;
		for(long i = start; i <= end; i++){
			sum += i;
		}
		System.out.println(start + "부터 " + end + "까지의 합계 : " + sum);
	}
}
