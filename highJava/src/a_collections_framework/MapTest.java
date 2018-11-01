package a_collections_framework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *	Map	ㅡ>	key값과 value값을 한 쌍으로 관리하는 객체
 *		ㅡ>	key값은 중복을 허용하지 않고 순서가 없다.
 *		ㅡ>	value값은 중복을 허용
 */

public class MapTest {
	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		/*	자료추가	ㅡ>	put(key값, value값);	*/
		map.put("name", "홍길동");
		map.put("tel", "010-1234-5678");
		map.put("addr", "대전");
		System.out.println(map);

		
		/*
		 *	자료수정	ㅡ>	데이터를 저장할 때 key값이 같으면
		 *				나중에 입력한 값이 저장
		 */
		map.put("addr","서울");
		System.out.println(map);
		
		
		/*
		 *	자료삭제	ㅡ>	remove(key값);
		 *	map.remove("tel");
		 *	System.out.println(map);
		 */
		
		
		/*
		 *	자료읽기	ㅡ>	get(key값);
		 *			ㅡ>	지정한 key값과 한 쌍인 value값을 반환
		 */
		System.out.println(map.get("name"));
		
		
		/*
		 *	key값을 읽어와 자료를 출력하는 방법	
		 *	방법 1	ㅡ>	ketSet() 이용
		 *	 		ㅡ>	맵에서 키값만 읽어와 Set형으로 반환
		 */
		Set<String> keySet = map.keySet();
		
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key + " : " + map.get(key) + " / 방법1");
		}
		System.out.println("\n*************************************\n");
		
		
		/*
		 *	방법 2	ㅡ>	keySet()을 향상된 for문으로 처리
		 */
		for(String key : map.keySet()){
			System.out.println(key + " : " + map.get(key) + " / 방법2");
		}
		System.out.println("\n*************************************\n");
		
		
		/*
		 *	방법 3	ㅡ>	Map에서 value값만 읽고 처리
		 *			ㅡ>	values()를 이용
		 */
		for(String value: map.values()){
			System.out.println(value + " / 방법3");
		}
		System.out.println("\n*************************************\n");
		
		
		/*
		 *	Map에는 Entry라는 내부 class가 만들어져 있다.
		 *	Entry클래스는 key와 value라는 멤버변수로 구성한다.
		 *	Map에선 이 Entry객체들을 Set형식으로 저장 및 관리한다.
		 */
		
		
		/*
		 *	방법 4	ㅡ>	Entry객체 전체를 가져와서 처리
		 *				(가져온 Entry객체들은 Set형식으로 되어있다.)
		 *			ㅡ>	entrySet()을 이용
		 */
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		
		
		/*
		 *	가져온 Entry객체들을 순서대로 처리하기 위해 Iterator객체로 변환
		 */
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		
		while(entryIt.hasNext()){
			// Entry객체 하나 가져오기
			Map.Entry<String, String> entry = entryIt.next();
			
			System.out.println("key값 : " + entry.getKey());
			System.out.println("value값 : " + entry.getValue());
			System.out.println("\n*************************************\n");
		}
		
		
		
		
		
		
		
		
	}
}
