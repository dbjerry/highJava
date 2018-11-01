package z_practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadTest11_1 {
	public static void main(String[] args) {
		List<Horse> horseList = new ArrayList<Horse>();
		
		for(int i = 1; i <= 10; i++){
			if(i != 10){
				String name =" " + i + "번말";
				horseList.add(new Horse(name));
			}else{
				String name = i + "번말";
				horseList.add(new Horse(name));
			}
		}
		
		/* Thread Start */
		for(int i = 0; i < horseList.size(); i++){
//			int prio = 20;
//			horseList.get(i).setPriority(prio--);
			
			horseList.get(i).start();
		}
		
		/* 모든 Thread가 끝날 때까지 기다린다. */
		for(Horse horse : horseList){
			try{
				horse.join();
			}catch(Exception e){}
		}
		System.out.println();
		System.out.println("경기결과");
		Collections.sort(horseList);
		System.out.println("순위 : " + horseList);
	}
}


class Horse extends Thread implements Comparable<Horse>{

	String name;
	Integer rank = 0;
	Integer location = 0;
	
	public Horse(String name) {
		this.name = name;
	}
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public void run() {
//		try {
//			Thread.sleep(1500);
//		} catch (Exception e) {}
		for (int i = 0; i <= 50; i++) {
			try {
				Thread.sleep((int)(Math.random()*(700-181)+1)+181);
				System.out.print(name);
			} catch (Exception e) {}
			for (int j = 0; j <= 50; j++){
				if(i == j){
					System.out.print(">");
				}
				System.out.print("-");
			}
			System.out.println();
		}
	}
	
	@Override
	public int compareTo(Horse horse) {
		return this.rank.compareTo(horse.rank);
	}

	@Override
	public String toString() {
		return name;
	}
}
