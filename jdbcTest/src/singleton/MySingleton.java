package singleton;
/*
 * Singleton패턴 클래스
 * 	- 외부에서 new를 사용하지 않고 동일한 객체를 반환하는 클래스
 * 	- Singleton 패턴 객체는 new를 사용하지 못한다.
 * 	(생성자를 외부에서 접근하지 못하도록 한다. ㅡ> private으로 설정)
 * 
 * 	- 작성방법
 * 		1.	생성자를 private으로 한다.
 * 			외부에서 객체생성(MySingleton ms = new MySingleton();)이 불가능
 * 		
 * 		2.	getInstance()를 만들어서 생성자를 대체한다.
 * 			(이 메서드는 public static으로 작성한다.)
 * 			(이 메서드의 반환값은 자기자신 참조값으로 한다.)
 * 
 * 		3.	객체 내부에 자기 자신 참조값을 저장할 변수를 선언해야 한다.
 * 			(이 변수도 private static으로 설정한다.)
 */
public class MySingleton {
	
	/* 자기 자신 참조값을 가질 변수 선언 */
	private static MySingleton single = new MySingleton();
	
	/* 생성자를 private으로 설정 */
	private MySingleton(){
		System.out.println("싱글톤의 생성자입니다.");
	}
	
	/* 자기자신 참조값을 반환하는 getInstance() 작성 */
	public static MySingleton getInstance(){
		if(single == null){
			single = new MySingleton();
		}
		return single;
	}
	
	/* 기타 나머지는 해당 클래스의 용도에 맞게 작성한다. */
	public void printScreen(){
		System.out.println("싱글톤에서 처리되는 메서드입니다.");
	}
	
	
}
