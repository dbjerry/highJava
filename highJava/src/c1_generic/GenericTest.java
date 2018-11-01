package c1_generic;

/**
 * 하나의 클래스에서 사용되는 데이터 클래스가
 * 사용될 때 다른 종류의 클래스일 경우
 * 
 * ㅡ>	제네릭을 이용하여 사용할 데이터 클래스의 타입을 정할 수 있다.
 * 
 * <제네릭클래스 만드는 방법>
 * class (클래스명)<제네릭타입>{
 * 		(제네릭타입) (변수명);	//	변수 선언에 사용할 경우
 * 		...
 * 
 * 		(제네릭타입) (메서드명)(){	//	메서드의 반환값에 사용할 경우
 * 		...
 * 		return (리턴값);
 * 		}
 * 
 * 		(메서드명)((제네릭타입)(변수명)){	//	메서드의 파라미터 변수에 사용할 경우
 * 		
 * 		}
 * }
 */


/* 제네릭을 사용하지 않을 경우 */
class NonGenericClass{
	
	private Object val;

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}
}


/* 제네릭을 사용하는 클래스 */
class MyGenericClass<T>{
	
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
	
}

/**
 * <제네릭 타입 글자>
 * T	ㅡ>	type
 * K	ㅡ>	key
 * V	ㅡ>	value
 * E	ㅡ>	Element
 */

public class GenericTest {
	public static void main(String[] args) {
		
		NonGenericClass ng1 = new NonGenericClass();
		
		ng1.setVal("가나다");
		String rtn = (String)ng1.getVal();
		System.out.println("문자열 반환값 : " + rtn);
		
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		Integer irtn = (Integer)ng2.getVal();
		System.out.println("정수 반환값 : " + irtn);
		
		
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		
		MyGenericClass<String> mg1 = new MyGenericClass<String>();
		
		mg1.setVal("홍길동");
		//mg1.setVal(3333); <ㅡ error
		String str = mg1.getVal();
		System.out.println("제네릭 문자열 반환값 : " + str);
		
		
		MyGenericClass<Integer> mg2 = new MyGenericClass<Integer>();
		mg2.setVal(123);
		int test = mg2.getVal();
		System.out.println("제네릭 정수형 반환값 : " + test);
		
	}
}
