package a_collections_framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
	정렬과 관련된 interface는 Comparator와 Comparable
	이렇게 두 가지가 있다.
	
	- 보통 객체 자체에 정렬기능을 넣기 위해서는 Comparable을 구현
	
	- 이전 예제처리 정렬기준을 별도로 구현하고 싶을 때는
	Comparator를 구현한다.
	
	Comparable에서는 compareTo()메서드를 재정의 해야하고
	Comparator에서는 compare()메서드를 재정의 해야한다.
 
*/

/*
	Member클래스를 구성하는데 회원의 이름을 기준으로
	오름차순 정렬이 될 수 있는 class만들기

*/
class Member implements Comparable<Member>{
	private int num;		// 회원번호
	private String name;	// 회원이름
	private String tel;		// 전화번호
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	// 이름을 기준으로 오름차순 정렬이 되도록 한다.
	@Override
	public int compareTo(Member mem) {
		return name.compareTo(mem.getName());
	}
	
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	
}







public class ListSortTest2 {
	public static void main(String[] args) {
		
		ArrayList<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-2222"));
		memList.add(new Member(10, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "이순신", "010-4444-1111"));
		memList.add(new Member(7, "강감찬", "010-5555-1111"));
		memList.add(new Member(2, "일지매", "010-6666-1111"));
		
		System.out.println("정렬 전 : ");
		for(Member mem : memList){
			System.out.println(mem);
		}
		
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		Collections.sort(memList);
		
		System.out.println("정렬 후 : ");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		
		// 회원번호의 내림차순으로 정렬하시오.
		System.out.println("정렬 전 : ");
		for(Member mem : memList){
			System.out.println(mem);
		}
		
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		Collections.sort(memList, new SortNumDesc());
		System.out.println("번호의 내림차순으로\n정렬 후 : ");
		for(Member mem : memList){
			System.out.println(mem);
		}
		
		
		
		
	}
}

// 정렬 기준의 외부 선언을 위해서는 Comparator를 구현한다.
// Member의 num값(회원번호)의 내림차순으로 정렬하는 class
class SortNumDesc implements Comparator<Member>{
	
	@Override
	public int compare(Member mem1, Member mem2) {
//		if(mem1.getNum() < mem2.getNum()){
//			return 1;
//		} else if(mem1.getNum() > mem2.getNum()){
//			return -1;
//		}else{
//			return 0;
//		}
		
		// Wrapper클래스를 이용하는 방법1
//		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
		
		// Wrapper클래스를 이용하는 방법2
		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		
	}
	
}