package programmers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetExam {
	public static void main(String[] args) {
		
		//인스턴스 생성할 때에 set은 인터페이스이기 때문에 
		//new로 객체생성이 불가능
		//set을 구현한 HashSet을 이용하여 인스턴스 생성
		Set<String> set1 = new HashSet<>();
		//리턴타입은 boolean, set은 중복이 되지 않음
		//저장할 때에 이미 같은 값이 있을 경우 false
		boolean flag1 = set1.add("kang");
		boolean flag2 = set1.add("kim");
		boolean flag3 = set1.add("kang"); 
		
		System.out.println(set1.size());	//2
		
		System.out.println(flag1);	//true
		System.out.println(flag2);	//true
		
		//String타입으로 flag1에 "kang"을 넣은 후
		//flag3에도 넣었으므로 false
		System.out.println(flag3);	//false
		
		
		//하나씩 꺼내볼 수 있는 Iterator를 사용
		//Iterator는 hasNext를 가지고 있음
		
		
		
		
	}
}
