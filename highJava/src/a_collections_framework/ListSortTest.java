package a_collections_framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest {
	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전 : " + list + "\n");
		
		// 정렬은 Collections.sort() 메서드를 이용하여 정렬
		// 정렬은 기본적으로 오름차순 정렬을 수행
		
		Collections.sort(list);
		System.out.println("오름차순\n정렬 후 : " + list + "\n");
		
		// 데이터 섞기
		Collections.shuffle(list);
		System.out.println("셔플 후 : " + list + "\n");
		
		// 정렬 기준을 지정해서 정렬하기
		Collections.sort(list, new Desc());
		System.out.println("내림차순\n정렬 후 : " + list);
		
		
	}
}

// 정렬 기준을 설정하는 객체 만들기
// => Comparator 인터페이스를 구현
class Desc implements Comparator<String>{
	/*
	1. compare메서드의 반환값은 양수, 0, 음수이다.
	
	2. compare메서드의 반환값이 양수이면 앞, 뒤의 데이터의 순서가 바뀐다.
	
	3. 오름차순 기준 => 앞의 값이 크면 양수(1)
					같으면 0
					앞의 값이 작으면 음수(-1)
					
	- String객체에는 정렬을 위해서 compareTo()메서드가 구현되어 있는데
	이 메서드의 반환값은 오름차순에 맞게 반환되도록 구현되어 있다.
	(Wrapper클래스와 Date, File클래스에도 구현되어 있다.)
	*/
	
	@Override
	public int compare(String s1, String s2) {
//		//앞의 값이 크면 음수를 반환
//		if(s1.compareTo(s2)>0){
//			return -1;
//			
//		//앞의 값이 같으면 0을 반환
//		}else if(s1.compareTo(s2)==0){
//			return 0;
//			
//		//앞의 값이 작으면 양수를 반환
//		}else{
//		return 1;
//		}
		return s1.compareTo(s2) * -1;
	}
}
