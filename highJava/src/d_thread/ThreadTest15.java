package d_thread;
/**
 * 쓰레드에서 데이터(객체)를 공통으로 사용하기
 * 
 * 원주율을 계산하는 쓰레드가 있고,
 * 계산된 원주율을 출력하는 쓰레드가 있다.
 * 
 * 원주율을 저장하는 객체가 필욯다.
 * 이 객체를 두 쓰레드가 공통으로 사용한다.
 */
public class ThreadTest15 {
	public static void main(String[] args) {
		ShareData sd = new ShareData();	//	공유될 객체 생성
		
		//쓰레드 객체들 생성
		CalcPIThread ct = new CalcPIThread(sd);
		PrintThread pt = new PrintThread(sd);
		
		ct.start();
		pt.start();
	}
}
/* 원주율을 계산하는 쓰레드 */
class CalcPIThread extends Thread{
	private ShareData sd;

	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		/**
		 * 원주율 = (1/1 - 1/3 + 1/5 - 1/7 + ...) * 4;
		 * 			 1 -   3 +   5 -   7 + ...) * 4;
		 * 			 0     1     2     3
		 */
		double sum = 0.0;
		for(int i = 1; i <= 1000000; i += 2){	//	1부터 2씩 증가되는 반복문
			if((i/2)% 2 == 0){
				sum += (1.0 / i);
			}else{
				sum -= (1.0 / i);
			}
		}
		
		// 계산된 원주율을 공유 객체에 저장한다.
		sd.result = sum * 4;
		sd.isOk	= true;	//	계산이 완료됨은 나타낸다.
	}
}

/* 계산이 완료되면 계산된 원주율을 출력하는 쓰레드 */
class PrintThread extends Thread{
	private ShareData sd;
	
	public PrintThread(ShareData sd){
		this.sd = sd;
	}
		 
	 @Override
	public void run() {
		while(sd.isOk == false){
			continue;
		}
		System.out.println();
		System.out.println("결과 : " + sd.result);
		System.out.println("PI : " + Math.PI);
	}
}


/* 원주율을 관리하는 클래스(공유클래스) */
class ShareData{
	
	public double result;			//	계산된 결과가 저장될 변수
	
	/**
	 * volatile	:	선언된 변수를 컴파일러의 최적화 대상에서 제외시킨다.
	 * 				즉, 값이 변경되는 즉시 변수에 적용시킨다.
	 */
	public volatile boolean isOk = false;	//	계산이 완료되었는지를 나타내는 변수
}

