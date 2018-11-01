package d_thread;
/**
 * 은행의 입출금을 쓰레드로 처리하는 예제
 * (synchronized를 이용한 동기화처리)
 */
public class ThreadTest17 {
	private int balance;	//	잔액이 저장될 변수
	
	public int getBalance(){
		return balance;
	}

	public void setBalance(int balance){
		this.balance = balance;
	}
	
	/* 입금하는 메서드 */
	public synchronized void deposit(int money){
		balance += money;
	}
	
	/**
	 * 출금하는 메서드
	 * 출금 성공시 true, 실패시 false를 반환한다.
	 */
	public synchronized boolean withdraw(int money){
		if(balance > money){
			for(int i = 1; i <= 100000000; i++){}	//	시간차를 두는 반복문
			balance -= money;
			System.out.println("메서드 안에서 balance = " + balance);
			return true;
		}else{
			return false;
		}
	}
	
	
	public static void main(String[] args){
		/* Java 1.7에선 익명구현체를 구현할 때 객체 생성시 final을 붙여줘야함 */
		/* final */ThreadTest17 acount = new ThreadTest17();
		acount.setBalance(10000);	//	초기 잔액 세팅(설정)
		
		/* 익명구현체를 이용한 쓰레드 구현 */
		Runnable withdrawTest = new Runnable(){
			@Override
			public void run() {
				boolean result = acount.withdraw(4000);
				System.out.println("쓰레드에서 result = " + result + ", balance = "
						+ acount.getBalance());
			}
		};
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 익명구현체 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		
		Thread th1 = new Thread(withdrawTest);
		Thread th2 = new Thread(withdrawTest);
		
		th1.start();
		th2.start();
		
	}
	
}
