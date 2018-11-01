package z_practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//만드는 과정에서 많은 실패를 맛보아서 나쁜말이라는 이름이 되었습니다.
public class RunningBadHorse {

	public static void main(String[] args) {

		List<Horse3> list = new ArrayList<Horse3>();

		list.add(new Horse3("1번말"));
		list.add(new Horse3("2번말"));
		list.add(new Horse3("3번말"));
		list.add(new Horse3("4번말"));
		list.add(new Horse3("5번말"));
		list.add(new Horse3("6번말"));
		list.add(new Horse3("7번말"));
		list.add(new Horse3("8번말"));
		list.add(new Horse3("9번말"));
		list.add(new Horse3("0번말"));

		// 말달리는 메서드
		for (Horse3 horse : list) {
			horse.start();
		}

		PrintHorse ph = new PrintHorse(list);
		ph.start();
	}

}

class PrintHorse extends Thread {
	List<Horse3> list;

	public PrintHorse(List<Horse3> h1) {
		this.list = h1;
	}

	@Override
	public void run() {
		String[] arr = new String[50];
		int rnk = 1;
		boolean ing = true;
		while (ing) {

			for (Horse3 h2 : list) {
				if (h2.isGoal() == true) {
					System.out.print(h2.getName() + " : ");
					for (int j = 0; j < 50; j++) {
						arr[j] = "*";
						System.out.print(arr[j]);
					}
					System.out.println();
					continue;
				}

				// 말 달리는거 위치표시
				System.out.print(h2.getName() + " : ");
				for (int i = 0; i < 50; i++) {
					arr[i] = "-";
					if (h2.getLocation() == i) {
						arr[i] = ">";
					}
				}

				// 말 10마리 출력
				for (int j = 0; j < 50; j++) {
					System.out.print(arr[j]);
				}
				System.out.println();

				// 순위매기기
				if (h2.getLocation() >= 50) {
					h2.setRank(rnk);
					rnk++;
					h2.setGoal(true);
				}

			} // end for

			// 일정 시간마다 출력
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("=========================================");

			// 말 다 들어오면 경기 끝
			if (rnk == 11) {

				System.out.println("=========경기 끝!=======");
				ing = false;
			}
		}// end while
		Collections.sort(list);
		for (Horse3 h2 : list) {
			System.out.printf("%3d 등  :  %3s", h2.getRank(), h2.getName());
			System.out.println();
		}
		System.exit(0);
	}
	
}

// 말 클래스
class Horse3 extends Thread implements Comparable<Horse3> {
	private String name;
	private Integer rank = 0;
	private Integer location = 0;
	public volatile boolean goal = false; // 결승지점 통과 여부

	public Horse3(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		int cnt = 0;
		while (true) {
			location += cnt;
			try {
				Thread.sleep(1000 * (int) (Math.random() * 4));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (location == 50) {
				break;
			}
			cnt++;
		}
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

	public boolean isGoal() {
		return goal;
	}

	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	@Override
	public int compareTo(Horse3 horse) {
		return this.rank.compareTo(horse.rank);
	}



}
