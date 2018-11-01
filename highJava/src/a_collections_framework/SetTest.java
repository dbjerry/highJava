package a_collections_framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * List와 Set의 차이점
 * 
 * 1. List
 * 	- 데이터의 순서가 있다.(입력한 순서)
 * 	- 중복되는 데이터를 저장할 수 있다.
 * 
 * 2. Set
 * 	- 데이터의 순서가 없다.
 * 	- 중복된 데이터를 저장할 수 없다.
 */
public class SetTest {
	public static void main(String[] args) {
		HashSet hs1 = new HashSet();
		
		// Set에 데이터를 추가할 때도 add()메서드를 사용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		
		// 중복되는 데이터를 add()하면 false를 반환하고
		// 데이터는 추가되지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdd);
		System.out.println("Set : " + hs1);
		System.out.println();
		
		isAdd = hs1.add("CC");
		System.out.println("중복될 때 : " + isAdd);
		System.out.println("Set : " + hs1);
		System.out.println();
		
		
		/**
		 * Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에
		 * 해당 자료를 삭제한 후에 추가해 주어야 한다.
		 * ex) 'FF'를 'EE'로 수정하기
		 * 삭제하는 메서드: remove(삭제할 데이터)
		 * 				clear() => 전체 삭제
		 */
		hs1.remove("FF");
		System.out.println("삭제한 후 Set : " + hs1);
		hs1.add("EE");
		System.out.println("Set : " + hs1);
		
		
		/**
		 * 전체 자료 삭제
		 * hs1.clear();
		 * System.out.println("clear 후 Set : " + hs1);
		 * System.out.println();
		 */
		
		
		/**
		 * Set은 데이터의 순서가 없기 때문에 List처럼 인덱스로
		 * 데이터를 하나씩 불러올 수 없다.
		 * 데이터를 하나씩 얻기 위해 Iterator로 변환해야 한다.
		 */
		
		// Set을 Iterator로 변환하기 => iterator()메서드 이용한다.
		Iterator it = hs1.iterator();
		
		// 데이터 개수만큼 차례로 처리하기
		while(it.hasNext()){
			// next() => 포인터를 다음 자료위치로 이동하고
			//			 이동한 위치의 자료를 반환한다.
			System.out.println(it.next());
		}
		System.out.println();
		
		System.out.println("Set의 자료갯수 : " + hs1.size());
		System.out.println();
		
		for(Object data : hs1){
			System.out.println(data);
		}
		
		
		// 로또 번호 만들기
		// 1~45사이의 중복되지 않는 6개의 난수 만들기
		ArrayList<Integer> lottoList = new ArrayList<Integer>();
		
		// 방법1.
		for(int i = 0; i < 6; i++){
			int num = (int)(Math.random()*45+1);
			
			boolean chk = false;
			for(int j = 0; j < i; j++){
				if(lottoList.get(j) == num){
					chk = true;
					break;
				}
			}
			if(chk == true){
				i--;
				continue;
			}
			lottoList.add(num);
		}
		System.out.println(lottoList);
		
		
//		// 방법2.
//		for(int i = 1; i <= 45; i++){
//			lottoList.add(i);
//		}
//		Collections.shuffle(lottoList);
//		
//		List<Integer> lotto = lottoList.subList(0, 6);
//		System.out.println(lotto);

		
		// 방법3.
		Set<Integer> lotto = new HashSet<Integer>();
		
		while(lotto.size() < 6){
			int num = (int)(Math.random()*45+1);
			lotto.add(num);
		}
		System.out.println(lotto);
	}
}
