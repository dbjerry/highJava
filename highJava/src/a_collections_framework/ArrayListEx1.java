package a_collections_framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListEx1 {
	public static void main(String[] args) {
		
		List list1 = new ArrayList();
		
		list1.add(new Integer(5));
		list1.add(new Integer(4));
		list1.add(new Integer(2));
		list1.add(new Integer(0));
		list1.add(new Integer(1));
		list1.add(new Integer(3));
		
		
		/*
		 *	subList(int fromIndex, int toIndex)
		 *	ㅡ>	from(포함) ~ to(미포함)
		 */
		List list2 = new ArrayList(list1.subList(1, 4));
		print(list1, list2);
									
									
		Collections.sort(list1);	// list1과 list2를 정렬
		Collections.sort(list2);	// Collections.sort(List l)
		print(list1, list2);
		
		
		System.out.println("list1.containsAll(list2) : " + list1.containsAll(list2));
		
		list2.add("B");
		list2.add("C");
		list2.add(3, "A");	//	3번 인덱스에 "A"값을 추가
							//	ㅡ>	기존 3번 인덱스 이후로 하나씩 밀림
							//		(3번 인덱스 포함)
		print(list1, list2);
		
		
		list2.set(3,"AA");	//	set(int index, Object obj)를
		print(list1, list2);//	이용해서 다른 객체로 변경
		
		
		/* 	list1에서 list2와 겹치는 부분만 남기고 나머지는 삭제한다.  */
		System.out.println("list1.retainAll(list2)" + list1.retainAll(list2));// retainAll에 의해 list1에 변화가
																			  // 있었으므로 true를 반환
		print(list1, list2);
		
		// list2에서 list1에 포함된 객체들을 삭제한다.
		for(int i = 0; i < list2.size(); i++){
			if(list1.contains(list2.get(i))){
				list2.remove(i);
				
			}
		}
		print(list1, list2);

	}
	
	static void print(List list1, List list2) {
		System.out.println("list1 : " + list1);
		System.out.println("list2 : " + list2);
		System.out.println();
	}
}
