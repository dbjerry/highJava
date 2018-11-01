package basic;

public class LambdaTest02 {
	public static void main(String[] args) {
		/*
		// 람다식을 사용하지 않을 경우
		LambdaTestInterface1 t1 = new LambdaTestInterface1() {
			@Override
			public void test() {
				System.out.println("Hello World");
			}
		};
		t1.test();
		*/
		
		LambdaTestInterface1 t1 =
				() -> {
					System.out.println("Hello World-1");
				};
		t1.test();
		
		
		LambdaTestInterface1 t2 = 
				() -> System.out.println("Hello World-2"); 
		t2.test();
		
		System.out.println();
		
		
		LambdaTestInterface2 t3 = (int a) -> {
			int result = a + 10;
			System.out.println("result = " + result);
		};
		t3.test(6);
		
		
		LambdaTestInterface2 t4 = (a) -> {
			int r = a * 20;
			System.out.println("r = " + r);
		};
		t4.test(8);
		
		
		LambdaTestInterface2 t5 = a -> System.out.println(a + 50);
		t5.test(3);
		System.out.println();

		
		LambdaTestInterface3 t6 =
				(int a, int b) -> {
					int r = a * b;
					return r;
				};
		int k = t6.test(4, 8);
		System.out.println("k = " + k);
		
		
		LambdaTestInterface3 t7 =
				(a, b) -> { return a - b; };
		k = t7.test(32, 31);
		System.out.println("k = " + k);
		
		
		LambdaTestInterface3 t8 = 
				(a, b) -> a + b;
		k = t8.test(14, 20);
		System.out.println("k = " + k);
		
		
	}

}
