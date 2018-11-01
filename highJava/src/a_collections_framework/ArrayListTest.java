package a_collections_framework;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class ArrayListTest {
	
	//	ArrayList는 기본적으로 Vector와 사용법이 같다.
	
	public static void main(String[] args) {
		
		ArrayList list1 = new ArrayList();
		
		//add를 이용하여 저장(추가)한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('k');
		list1.add(true);
		list1.add(12.345);
		
		System.out.println("list1 => " + list1);
		System.out.println("list1의 size() => " + list1.size());
		
		//	get으로 데이터를 반환한다.
		System.out.println("1번째 자료 : " + list1.get(0));
		
		//	add로 데이터 추가(끼워넣기)도 같다.
		list1.add(0, "zzz");
		System.out.println("list1 => " + list1);
		
		//	element(데이터) 변경하기
		String temp = (String)list1.set(0, "yyy");
		System.out.println(temp);	//	0번 index의 zzz가 나오고 yyy가 들어갔다. 나온 zzz가 temp에 저장.
		System.out.println("list1 => " + list1);
		
		//	삭제도 같다.
		list1.remove(0);
		System.out.println("삭제후 list1 => " + list1);
		
		list1.remove("bbb");
		System.out.println("삭제후 list1 => " + list1);
		
		
		//	제네릭을 지정하여 선언할 수 있다.
		
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		
		System.out.println("- - - - - - - - - - - - - - - - - - - - -");		
		
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
		}
		System.out.println("- - - - - - - - - - - - - - - - - - - - -");
		
		//	향상된 for문
		for(String str : list2){
			System.out.println(str);
		}
		System.out.println("- - - - - - - - - - - - - - - - - - - - -");
		
		//	contains(비교객체)
		//	=>	리스트에 '비교객체'가 있으면 true
		//		없으면 false를 반환한다.
		System.out.println(list2.contains("CCCC"));
		System.out.println(list2.contains("KKKK"));
		System.out.println("- - - - - - - - - - - - - - - - - - - - -");
		
		//	indexOf(비교객체)
		//	=>	리스트에 '비교객체'가 있으면 '비교객체'가 있는 index값을 반환하고
		//		없으면 -1을 반환한다.
		System.out.println("DDDD의 index값 : " + list2.indexOf("DDDD"));	//	3, 3번 index(4번째 요소)
		System.out.println("ZZZZ의 index값 : " + list2.indexOf("ZZZZ"));	//	-1, 없음
		
		//	toArray()	=>	리스트 안의 데이터를 배열로 변환하여 반환한다.
		//				=>	기본적으로 Object형 배열로 변환한다.
		//	toArray(new generic Type[0])
		Object[] strArr = list2.toArray();
		System.out.println("배열의 개수 : " + strArr.length);
		
		String[] strArr2 = list2.toArray(new String[0]);
		for(String str : strArr2){
			System.out.println(str);
		}
		
	}
}
