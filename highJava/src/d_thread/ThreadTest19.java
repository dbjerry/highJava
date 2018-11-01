package d_thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Vector, HashTable등과 같이 예전부터 존재하던
 * Collection들은 내부에 동기화가 처리되어 있다.
 * 
 * 최근 새로 구성된 Collection들은 동기화처리가 되어있지 않아
 * 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면
 * 동기화 처리를 한 후에 사용해야 한다.
 */
public class ThreadTest19 {
	
	/* Vector */
	private static Vector<Integer> vec = new Vector<Integer>();
	
	/* 동기화하지 않은 List */
	private static List<Integer> list1 = new ArrayList<Integer>();
	
	/* 동기화된 List */
	private static List<Integer> list2 = Collections
			.synchronizedList(new ArrayList<Integer>());
	
	
	
	
	
	public static void main(String[] args) {
		Runnable r = new Runnable(){
			@Override
			public void run() {
				for(int i = 0; i < 10000; i++){
					vec.add(i);
//					list1.add(i);
					list2.add(i);
					
				}
			}
		};
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		
		Thread[] ths = new Thread[]{
				new Thread(r), new Thread(r), new Thread(r),
				new Thread(r), new Thread(r)
		};
		
		for(Thread th : ths){
			th.start();
		}
		
		for(Thread th : ths){
			try{
				th.join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		System.out.println("vec 개수 : " + vec.size());
		System.out.println("list1 개수 : " + list1.size());
		System.out.println("list2 개수 : " + list2.size());
	}
}
