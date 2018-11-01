package z_exam11;

import java.util.*;
/**
 * [11-2]
 * 다음 코드의 실행결과를 적으시오.
 */
public class Exercise11_2_4 {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		
		list.add(3);
		list.add(6);
		list.add(2);
		list.add(2);
		list.add(2);
		list.add(7);
		
		HashSet set = new HashSet(list);	//	set으로 중복값 제거
		TreeSet tset = new TreeSet(set);	//	treeset으로 정렬
		Stack stack = new Stack();			
		stack.addAll(tset);					//	stack에 저장 Last In First Out
		
		while(!stack.empty()){
			System.out.println(stack.pop());
		}
	}
}

/**
 * [11-3]
 * 다음 중 ArrayList에서 제일 비용이 많이 드는 작업은?
 * 단, 작업도중에 ArrayList의 크기 변경이 발생하지
 * 않는다고 가정한다.
 */
//	a. 첫 번째 요소 삭제			(v)
//	b. 마지막 요소 삭제			( )
//	c. 마지막에 새로운 요소 추가	( )
//	d. 중간에 새로운 요소 추가		( )

/**
 * [11-4]
 * LinkedList클래스는 이름과 달리 실제로는
 * 이중 원형 연결리스트(doubly circular linked list)로 구현되어 있다.
 * LinkedList인스턴스를 생성하고 11개의 요소를 추가했을 때,
 * 이 11개의 요소 중 접근시간(access time)이 가장 오래 걸리는 요소는 몇 번째 요소인가?
 */

//	6번째 요소
