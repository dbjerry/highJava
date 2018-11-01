package z_exam12;

public class Exercise12_1_3 {
/**
 * [12-1]
 * 클래스 Box가 다음과 같이 정의되어 있을 때, 다음 중 오류가 발생하는 문장은?
 * 경고가 발생하는 문장은?
 * 
 * class Box<T> {	// 제네릭 타입 T를 선언
 * 	T item;
 * 	
 * 	void setItem(T item){
 * 		this.item = item;
 * 	}
 * 
 * 	T getItem(){
 * 		return item;
 * 	}
 * }
 * 
 * 
 * a. Box<Object> b = new Box<String>(); 			//	error, 대입된 타입이 반드시 같아야 한다.
 * b. Box<Object> b = (Object)new Box<String>();	//	error, Object타입을 Box<Object>타입의 참조변수에 저장불가. (타입불일치)
 * c. new Box<String>().setItem(new Object());		//	error, 대입된 타입이 String이므로, setItem(T item)의 매개변수 역시, String타입만 허용된다.
 * d. new Box<String>().setItem("ABC");				//	OK. 대입된 타입인 String과 일치하는 타입을 매개변수로 지정했기 때문에 OK.
 * 
 * 
 */
}
