package basic;
/*
 * 	람다식	=>	보통 '익명함수를 생성하기 위한 식'을 말한다.
 * 				자바에서는 '매개변수를 가진 코드 블럭'으로
 * 				런타임시 컴파일러에 의해 '익명구현 객체로 생성된다.'
 * 				즉, 추상메서드가 하나인 인터페이스를 구현하는 식으로
 * 				사용된다.
 * 
 * 	기본형식)
 * 		인터페이스명 변수명 = 람다식;
 * 
 * 	람다식 형식)
 * 		( 매개변수들.. ) -> { 실행코드들..; }
 * 
 * 	- 람다식 작성 방법
 * 		기본형식)
 * 		(자료형이름 변수명, ...) -> { 실행문들;... }
 * 
 * 	1.	자료형 이름은 생략할 수 있다.
 * 		(int a) -> { System.out.println(a); }
 * 		(a)		-> { System.out.println(a); }
 * 
 * 	2.	매개변수가 1개일 경우에는 괄호'()'를 생략할 수 있다.
 * 		a -> { System.out.println(a); }
 * 
 * 	3.	실행문이 1개이면 중괄호'{}'를 생략할 수 있다.
 * 		a -> System.out.println(a);
 * 
 * 	4.	매개변수가 하나도 없으면 괄호'()'를 생략할 수 없다.
 * 		() -> System.out.println("Hello World");
 * 
 * 	5.	반환값이 있을 경우 return을 사용한다.
 * 		(a, b) -> { return a + b; }
 * 		(a, b) -> return a + b;
 * 
 * 	6.	실행문에 return문만 들어가는 경우에는 return과 중괄호'{}'를
 * 		생략할 수 있다.
 * 		(a, b) -> a + b;
 * 
 * 	함수적 인터페이스	=>	람다식으로 변환할 수 있는 인터페이스
 * 					=>	하나의 추상메서드만 선언된 인터페이스를 말한다.
 * 					=>	@FunctionalInterface라는 어노테이션을 붙여준다.
 */
public class LambdaTest01 {
	public static void main(String[] args) {
		
		/* 쓰레드를 람다식을 사용하지 않고 사용하는 경우 */
		
		
		/* Runnable인터페이스를 구현하여 실행하기 */
		Thread t1 = new Thread(
			new Runnable() {
				@Override
				public void run() {
					for(int i = 1; i <= 10; i++) {
						System.out.println("기존 방식" + i);
					}
				}
			}
		);
		t1.start();
		
		/////////////////////////////////////////////////
		
		/* 람다식을 사용하여 구현 */
		Thread t2 = new Thread(
			() -> {
				for(int i = 1; i <= 10; i++) {
					System.out.println("람다식 - " + i);
				}
			}
		);
		t2.start();
		
		
	}
}
