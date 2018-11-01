package basic;

public class LambdaStudy01 {
	
	public static void main(String[] args) {
		
		/* not use Lambda */
		Thread thread01 = new Thread(
			new Runnable() {
				@Override
				public void run() {
					System.out.println("Hello World :: not use Lambda");
					System.out.println();
				}
			}
		);
		thread01.start();
		
		
		/* use Lambda */
		Thread thread02 = new Thread(
			() -> {
				System.out.println("Hello World :: use Lambda");
				System.out.println();
			}
		);
		thread02.start();
		
		
		/**
		 * use Interface + Lambda
		 * no parameter, no return
		 */
		ILambdaStudy01 study01 =
//				() -> {
//					System.out.println("Mercedes-benz");
//				};
				() -> System.out.println("Mercedes-benz");
		study01.study();
		System.out.println();
		
		
		/**
		 * use Interface + Lambda - 1
		 * no return
		 * @param String a
		 */
		ILambdaStudy02 study02 = (String a) -> {
					String result = "Mercedes-" + a;
					System.out.println("1) result -> " + result);
					System.out.println();
				};
		study02.study("benz");
		
		
		/**
		 * use Interface + Lambda - 2( =1 )
		 * no return
		 * @param String a
		 */
		ILambdaStudy02 study03 = (param) -> {
			String result = "Mercedes-" + param;
			System.out.println("2) result -> " + result);
			System.out.println();
		};
		study03.study("benz");
		
		
		/**
		 * use Interface + Lambda
		 * @param String a
		 * @param String b
		 */
		ILambdaStudy03 study04 = 
				(a, b) -> { return a + b; };
		String car = study04.study("Mercedes-", "benz");
		System.out.println("3) result -> " + car);
		
	}
	
}

