package basic;

public class LambdaTest03 {
	
	/*
	 * 메서드의 매개변수도 메서드 내에서는 지역변수이다.
	 */
	public void myMethod(int temp) {
		int localVar = 40;
		
		// 람다식
		LambdaTestInterface1 lt =
			() -> {
				// 람다식 내에서 지역변수를 사용하는 경우
				/*
				 * 이 때 지역변수는 final이어야 한다.
				 * 지역변수가 선언되어 초기화된 후 값의 변경이 없을
				 * 경우에는 컴파일러가 자동으로 final을 붙여준다.
				 */
				System.out.println("temp = " + temp);
				System.out.println("localVar = " + localVar);
			};
			
		lt.test();
	}
	
	public static void main(String[] args) {
		LambdaTest03 lambda = new LambdaTest03();
		
		lambda.myMethod(50);
		
	}
}
