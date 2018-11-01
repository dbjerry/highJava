package z_exam11;

import java.util.*;
/**
 * [11-7]
 * 다음에 제시된 BanNoAscending클래스를 완성하여, ArrayList에 담긴
 * Student인스턴스들이 반(ban)과 번호(no)로 오름차순 정렬되게 하시오.
 * (반이 같은 경우 번호를 비교해서 정렬한다.)
 */
class Student3 {
	String name;
	int ban;
	int no;
	int kor;
	int eng;
	int math;

	Student3(String name, int ban, int no, int kor, int eng, int math) {
		this.name = name;
		this.ban = ban;
		this.no = no;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	int getTotal() {
		return kor + eng + math;
	}

	float getAverage() {
		return (int) ((getTotal() / 3f) * 10 + 0.5) / 10f;
	}

	public String toString() {
		return name + "," + ban + "," + no + "," + kor + "," + eng + "," + math
				+ "," + getTotal() + "," + getAverage();
	}
} // class Student

class BanNoAscending implements Comparator<Student3> {
	public int compare(Student3 student1, Student3 student2) {
		/* (1) 알맞은 코드를 넣어 완성하시오. */
		int ban = student1.ban - student2.ban;
		if(ban == 0){
			return student1.no - student2.no;
		}
		return ban;
	}
}

public class Exercise11_7 {
	public static void main(String[] args) {
		ArrayList<Student3> list = new ArrayList<Student3>();
		
		list.add(new Student3("이자바", 2, 1, 70, 90, 70));
		list.add(new Student3("안자바", 2, 2, 60, 100, 80));
		list.add(new Student3("홍길동", 1, 3, 100, 100, 100));
		list.add(new Student3("남궁성", 1, 1, 90, 70, 80));
		list.add(new Student3("김자바", 1, 2, 80, 80, 90));
		
		Collections.sort(list, new BanNoAscending());
		
		Iterator it = list.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
	}
}
