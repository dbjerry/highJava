package singleton;

public class SingletonTest {
	public static void main(String[] args) {
		//MySingleton test1 = new MySingleton();	//	사용불가
		
		MySingleton test1 = MySingleton.getInstance();
		MySingleton test2 = MySingleton.getInstance();
		
		System.out.println("test1 ㅡ> " + test1);
		System.out.println("test2 ㅡ> " + test2);
		
		System.out.println(test1 == test2);
		
		test1.printScreen();
		
	}
	
}
