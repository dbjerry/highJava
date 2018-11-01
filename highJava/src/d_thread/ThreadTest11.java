package d_thread;

import java.util.Arrays;

/*
	10마리의 말들이 경주를 하는 프로그램 작성하기
	
	* 말은 Horse라는 이름의 클래스로 구성하고
	  이 Horse클래스는 말이름(String), 등수(int), 현재위치(int)를
	  멤버변수로 갖는다.
	  그리고 이 클래스는 등수의 오름차순으로 처리할 수 있는 기능이 있다.
	  ( 즉, Compare 인터페이스를 구현한다.)
	
	* 경기 구간은 50구간으로 되어 있다.
	
	* 일정한 시간 마다 말들의 위치를 출력한다.
	예) 
	01번말 -->-------------------
	02번말 ---->-----------------
	03번말 ------->--------------
	...
	10번말 --->------------------

	=======================================
	
	* 경기가 끝나면 등수 순으로 출력한다.
	

*/
public class ThreadTest11 {
	public static int currentRank = 0;
	
	public static void main(String[] args) {
		Horse[] horses = new Horse[]{
			new Horse("01번말"),
			new Horse("02번말"),
			new Horse("03번말"),
			new Horse("04번말"),
			new Horse("05번말"),
			new Horse("06번말"),
			new Horse("07번말"),
			new Horse("08번말"),
			new Horse("09번말"),
			new Horse("10번말")
		};
		
		// 현재 상태를 출력할 쓰레드 객체 생성
		PlayState ps = new PlayState(horses);
		
		
		for(Horse ho : horses){
			ho.start();
		}
		ps.start();
		
		
		for(Horse ho : horses){
			try {
				ho.join();
			} catch (InterruptedException e) {
			}
		}
		
		try {
			ps.join();
		} catch (InterruptedException e) {
		}
		
		System.out.println("경기 끝...");
		
		System.out.println("-----------------------------------");
		
		System.out.println("경기결과(입력순_정렬전)");
		for(Horse ho : horses){
			System.out.println(ho);
		}
		
		System.out.println("-----------------------------------");
		// 배열의 값들을 등수의 오름차순으로 정렬한다.
		Arrays.sort(horses);  
		
		System.out.println("경기결과(등수순_정렬후)");
		for(Horse ho : horses){
			System.out.println(ho);
		}
		
	}

}

//-------------------------------------------------
// 경기 중 현재 상황을 출력하는 쓰레드
class PlayState extends Thread{
	// 경기에 임하는 말들이 저장된 배열을 저장할 변수
	private Horse[] horses;

	// 생성자에서 경기에 임하는 말 배열을 받아서 변수에 저장한다.
	public PlayState(Horse[] horses) {
		super();
		this.horses = horses;
	}  
	
	@Override
	public void run() {
		// 현재 상황을 출력한다.
		while(true){
			if(ThreadTest11.currentRank==10){
				break;
			}
			
			for(int i=1; i<30; i++){
				System.out.println();
			}
			
			
			// 말 개수만큼 반복
			for(int i=0; i<horses.length; i++){
				System.out.print(horses[i].getHorseName() + " : ");
				
				// 각 구간만큼 '-'를 출력한다.
				for(int j=1; j<=50; j++){
					if(j == horses[i].getLocation()){
						System.out.print(">");
					}else{
						System.out.print("-");
					}
				}
				System.out.println(); // 줄바꿈
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
	}
	
}



//-------------------------------------------------

// 말(Horse) 클래스 작성
class Horse extends Thread implements Comparable<Horse>{
	private String horseName;	// 말이름
	private int rank;			// 등수
	private int location;		// 현재위치
	
	// 생성자
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	// getter, setter
	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "경주마 " + horseName + "는 " + rank + "등 입니다.";
	}
	
	// 등수의 오름차순이 되도록 설정
	@Override
	public int compareTo(Horse o) {
		return Integer.compare(this.rank, o.rank);
	}
	
	// 쓰레드 처리 (말의 달리기 처리)
	@Override
	public void run() {
		for(int i=1; i<=50; i++){
			location = i;  // 현재 위치 저장
//			System.out.println("경주마 " + horseName + "이 " + i + 
//					"구간을 달리고 있습니다.");
			try {
				Thread.sleep((int)(Math.random()*400));
			} catch (InterruptedException e) {
			}
		}
		// 현재 말이 골인하면...
		ThreadTest11.currentRank++;  // 등수 증가
		this.rank = ThreadTest11.currentRank;
		
	}
	
	
}



