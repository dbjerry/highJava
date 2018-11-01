package z_practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * Thread - Queue를 연계하여 과제해보기
 * 실패
 * Queue q = new LinkedList();	//	Queue 생성 성공
 * q.offer(horseList);			//	horseList를 Queue로 변환 성공
 * q.peek().start();			//	start()가 안됨, 여기서 실패;
 * 아직 제대로 사용할 줄 모름
 * @author 김지태
 * 2018/07/25
 */

/**
 * synchronized(동기화)로 풀어보기
 * 실패
 * java.lang.NullPointerException
 * 아직 제대로 사용할 줄 모름
 * @author 김지태
 * 2018/07/25
 */
public class ThreadTest11_2 {
	public static void main(String[] args) {
		List<Horse2> horseList = new ArrayList<Horse2>();
		
		/* 반복문을 이용해 List.add를 한다. */
		for(int i = 1; i <= 10; i++){
			if(i == 10){
				String name = i + "번말";
				horseList.add(new Horse2(name));
			}else{
				String name = " " + i + "번말";
				horseList.add(new Horse2(name));
			}
		} // for

		
		/* Thread Start */
		for(Horse2 horse : horseList){
			horse.start();
		}
		
		Location lc = new Location(horseList);
		lc.setDaemon(true);
		lc.start();
		
		/* 모든 Thread가 끝날 때까지 기다린다. */
		for(Horse2 horse : horseList){
			try{
				horse.join();
			}catch(Exception e){}
		}
		
	}
	
}
class Location extends Thread{
	List<Horse2> horseList;
	
	public Location(List<Horse2> horse) {
		this.horseList = horse;
	}
	
	@Override
	public void run(){
		String[] arr = new String[50];
		int rank = 1;
		boolean start = true;
		while(start){
			for(Horse2 horse : horseList){
//				if(horse.getAlive() == true){
//					System.out.println(horse.getterName() + " : ");
//					for(int i = 0; i < 50; i++){
//						arr[i] = "馬";
//						System.out.print(arr[i]);
//					}
//					System.out.println();
//					continue;
//				}
				
				System.out.print(horse.getterName() + " : ");
				
				for(int i = 0; i < 50; i++){
					arr[i] = "-";
					if(horse.getLocation() == i){
						arr[i] = "馬";
					}
				}
				
				for(int i = 0; i < 50; i++){
					System.out.print(arr[i]);
				}
				System.out.println();
				
				if(horse.getLocation() >= 50){
					horse.setRank(rank);
					rank++;
					horse.setAlive(true);
				}
			}
			
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
			
			if(rank == 11){
				start = false;
			}
		}
		System.out.println();
		System.out.println("경기결과");
		Collections.sort(horseList);
		System.out.println("순위 : " + horseList);
		System.exit(0);
	}
}

class Horse2 extends Thread implements Comparable<Horse2>{
	private String name;
	private Integer rank = 0;
	private Integer location = 0;
	private boolean alive = false;
	
	
	
	public String getterName() {
		return name;
	}

	public Horse2(String name) {
		this.name = name;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}
	
	public boolean getAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
	public void run() {
		
		for (int i = 0; i <= 50; i++) {
			location += i;
			try {
				Thread.sleep((int)(Math.random()*(1000-201)+1)+201);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
//			try {
//				Thread.sleep((int)(Math.random()*(1000-201)+1)+201);
//				System.out.print(name);
//			} catch (Exception e) {}
//			for (int j = 0; j <= 50; j++){
//				if(i == j){
//					System.out.print(">");
//				}else{
//					System.out.print("-");
//				}
//			}
//			System.out.println();
		}
	}

	@Override
	public int compareTo(Horse2 horse) {
		return this.rank.compareTo(horse.rank);
	}

	@Override
	public String toString() {
		return name;
	}
}