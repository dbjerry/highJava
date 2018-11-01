package a_collections_framework;

import java.util.Vector;
// Collection객체에 저장되는 데이터는 모두
// 객체형만 저장할 수 있다.


// Vector => List계열의 객체 
//		  => 초창기 Java에서부터 지원되는 객체
public class VectorTest {
	public static void main(String[] args) {
		// 생성
		Vector v1 = new Vector();
		
		System.out.println("처음 크기 : " + v1.size());
		
		// Vector의 데이터 추가는 add()메서드를 이용한다.
		v1.add("aaa");
		v1.add(111);	// AutoBoxing(오토바싱, 자동형변환)
		v1.add(new Integer(123));
		v1.add('a');
		v1.add(true);
		v1.add(3.14);
		
		System.out.println("현재 크기 : " + v1.size());
		
		// addElement()메서드를 이용해서도 추가할 수 있다.
		// 이 메서드는 예전에 사용하던 메서드로 호환성을 위해서 존재한다.
		v1.addElement("KKK");
		System.out.println("현재값 : " + v1);
		
		// add(index, 데이터)
		// => Vector의 index번째에 '데이터'를 도중에 넣는다.
		// => index는 0부터 시작한다.
		v1.add(1, "zzz");
		System.out.println("v1 => " + v1);
		
		// set(index, 새로운 데이터)
		// => index번째의 데이터를 '새로운 데이터'로 덮어쓴다.
		// => 반환값 : 원래의 데이터
		
		String temp = (String)v1.set(0, "가나다");
		//Object temp = v1.set(0, "가나다");
		System.out.println("v1 => " + v1);
		System.out.println("temp => " + temp);
		
		//	remove(index)
		//	=>	index번째의 데이터를 삭제한다.
		//	=>	자료가 삭제되면 index 다음번째의
		//		데이터들이 앞으로 자동으로 당겨져서 채워진다.
		//	=>	반환값 : 삭제된 데이터
		
		v1.remove(0);
		System.out.println("삭제후 v1 => " + v1);
		temp = (String)v1.remove(0);
		
		System.out.println("삭제된 데이터 : " + temp);
		System.out.println("삭제후 v1 => " + v1);
		
		v1.add(123);
		System.out.println("v1 => " + v1);
		
		// remove(삭제할 데이터)
		//	=>	'삭제할 데이터'를 찾아서 삭제한다.
		//	=>	'삭제할 데이터'가 여러개이면 앞에서부터 삭제된다.
		//	=>	삭제할 데이터가 '정수형'이거나 'char'형일 경우에는
		//		객체로 변환해서 사용해야 한다.
		v1.remove(new Integer(123));
		System.out.println("삭제후 v1 => " + v1);
		
		v1.remove(new Character('a'));
		System.out.println("삭제후 v1 => " + v1);
		
		v1.remove(true);
		System.out.println("삭제후 v1 => " + v1);
		
		v1.remove(3.14);
		System.out.println("삭제후 v1 => " + v1);
		
		//	get(index)
		//	=>	index번째 데이터를 반환한다.
		int data = (int)v1.get(0);
		System.out.println("0번째 자료 : " + data);
		System.out.println("- - - - - - - - - - - - - - - - - - - - -");
		
		
		//	- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		/*
		제네릭  타입(generic type)이라는 것은
		객체를 선언할 때 < >괄호 안에  그 collection객체가 사용할 데이터 타입을
		정해주는 것을 말한다.
		
		이런 식으로 선언하게 되면 그 데이터 타입이외의  다른 종류 데이터를 저장할 수 없다.
		
		제네릭 타입으로 선언되면 데이터를 꺼내올 때 별도의 형변환이 필요없다.
		
		단, 제네릭으로 선언될 수 있는 데이터타입은 클래스(type)이어야한다.
		그래서 int는 Integer, boolean은 Boolean, char는 Character등으로 사용한다.
		*/
		
		//	String만 저장할 수 있는 vector
		Vector<String> v2 = new Vector<String>();
		
		//	int형만 저장할 수 있는 vector
		Vector<Integer> v3 = new Vector<Integer>();
		//Vector<int> v3 = new Vector<int>(); //	error!!
		
		v2.add("안녕하세요");
		//v2.add(100);	다른 자료형타입을 저장(추가)할 수 없다.
		
		String temp2 = v2.get(0);
		System.out.println("temp2 = " + temp2);
		
		
		Vector<Vector> vv = new Vector<Vector>();					// 2차원배열
		Vector<Vector<Vector>> vvv = new Vector<Vector<Vector>>();	// 3차원배열
		
		//	- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		
		v2.clear();
		System.out.println("clear()된 v2의 size : " + v2.size() + "\nclear()된 v2의 요소 : " + v2);
		
		
		v2.add("AAA"); 
		v2.add("BBB"); 
		v2.add("CCC"); 
		v2.add("DDD"); 
		v2.add("EEE"); 
		
		Vector<String> v4 = new Vector<String>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("추가된 v2 => " + v2);
		System.out.println("추가된 v4 => " + v4);
		
		//	removeAll(Collection객체)
		//	=> 'Collection객체'가 가지고 있는 모든 데이터를 삭제한다.
		v2.removeAll(v4);
		System.out.println("v2.removeAll(v4)된 v2 => " + v2);
		
		//	벡터의 전체 데이터를 순서대로 모두 처리하고 싶으면 반복문을 사용하면 된다.
		//	(주로 for문을 사용한다.)
		for(int i = 0; i < v2.size(); i++){
			System.out.println(i + "번째 자료 : " + v2.get(i));
		}
		System.out.println("- - - - - - - - - - - - - - - - - - - - -");
		
		//	향상된 for문
		System.out.println("향상된 for문");
		for(String s : v2){
			System.out.println(s);
		}
		/*
		형식)
		for(자료형명 변수명 : 배열이나 Collection객체 혹은 배열변수명){
			'반복할 구현부, 내용들';
		}
		=>	배열이나 Collection객체가 가지고 있는 데이터를
			하나씩 변수에 저장한 후 '반복할 내용'을 처리한다.
		*/
		
	}
}
