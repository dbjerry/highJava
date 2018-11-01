package d_thread;

import com.oracle.jrockit.jfr.Producer;

/**
 * 데이터를 공급하는 쓰레드와
 * 데이터를 소비하는 쓰레드 처리하기
 * 
 * wait(), notify()를 이용해서 구현
 */
public class ThreadTest21 {
	public static void main(String[] args) {
		DataBox databox = new DataBox();
		ProducerThread pro = new ProducerThread(databox);
		ConsumerThread con = new ConsumerThread(databox);
		
		pro.start();
		con.start();
		
	}
}


/* 데이터를 넣어주는 공급자 쓰레드 */
class ProducerThread extends Thread{
	private DataBox databox;
	
	public ProducerThread(DataBox databox){
		this.databox = databox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 3; i++){
			String data = "data-" + i;
			databox.setData(data);
		}
	}
}


/* 데이터를 꺼내가는 소비자 쓰레드 */
class ConsumerThread extends Thread{
	private DataBox databox;

	public ConsumerThread(DataBox databox) {
		this.databox = databox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 3; i++){
			String data = databox.getData();
		}
	}
}


/* 데이터를 공용으로 사용하는 클래스 */
class DataBox{
	private String data;
	
	public synchronized String getData(){
		if(data == null){
			try {
				wait();
			} catch (InterruptedException e) {
				
			}
		}
		String returnData = data;
		System.out.println("소비자 객체가 읽은 데이터 : " + returnData);
		data = null;
		notify();
		
		return returnData;
		
	}
	
	public synchronized void setData(String data){
		if(this.data != null){
			try {
				wait();
			} catch (InterruptedException e) {
				
			}
		}
		this.data = data;
		System.out.println("공급자 객체가 설정한 데이터 : " + data);
		notify();
	}
}

