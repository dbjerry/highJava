package d_thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 은행의 입출금을 쓰레드로 처리하는 예제
 * (lock()를 이용한 동기화 처리)
 */
public class ThreadTest18 {
	private int balance;	//	잔액이 저장될 변수
	
	/* Lock객체 생성 */
	private final Lock lock = new ReentrantLock();
	
	public int getBalance(){
		return balance;
	}

	public void setBalance(int balance){
		this.balance = balance;
	}
	
	/* 입금하는 메서드 */
	public void deposit(int money){
		/**
		 * lock()로 락을 설정한 곳에서는
		 * 반드시 unlock()로 락을 해제해 주어야 한다.
		 */
		lock.lock();	//	동기화 시작
		balance += money;
		lock.unlock();	//	동기화 해제
	}

	/**
	 * 출금하는 메서드
	 * 출금 성공시 true, 실패시 false를 반환한다.
	 */
	public boolean withdraw(int money){
		/**
		 * try-catch 블럭이 사용되는 부분에서 unlock()의 호출은
		 * finally 영역에서 하도록 한다.
		 */
		lock.lock();	//	동기화 시작
		boolean chk = false;
		try{
			if(balance > money){
				for(int i = 1; i <= 100000000; i++){}	//	시간차를 두는 반복문
				balance -= money;
				System.out.println("메서드 안에서 balance = " + balance);
//				return true;
				chk = true;
			}else{
//				return false;
				chk = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();	//	동기화 해제
		}
		return chk;
	}

	
	public static void main(String[] args){
		/* Java 1.7에선 익명구현체를 구현할 때 객체 생성시 final을 붙여줘야함 */
		/* final */ThreadTest18 acount = new ThreadTest18();
		acount.setBalance(10000);	//	초기 잔액 세팅(설정)
		
		/* 익명구현체를 이용한 쓰레드 구현 */
		Runnable withdrawTest = new Runnable(){
			@Override
			public void run() {
				boolean result = acount.withdraw(6000);
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

