package test;

public class Test {
	public static void main(String[] args) {
		Sequence_test st = new Sequence_test();
		
		st.call();
		
	}
}


class Sequence_test{

	void call() {
		sequence_01();
		switch_test();
		sequence_02();
	}
	
	/* test 1) 1+3+5+7+9+...+97+99 수열 */
	void sequence_01() {
		for (int i = -1; i < 99; i++) {
			System.out.println("test 1) ");
			for (int j = 0; i < 99; j++) {
				i = i +2;
				j = j + i;
				System.out.println(i + " : " + j);
			}
			System.out.println("\n");
		}
	}
	
	
	/* switch test) 1-2+3-4+5-6+...+99-100 */
	void switch_test() {
		System.out.println("switch test)\n[i] : [j]");
		int sw = 0;
		int j = 0;
		for (int i = 1; i <= 100; i++) {
			
			switch(sw) {
			case 0 : 
				j = j + i;
				sw = 1;
				break;
			case 1 : 
				j = j - i;
				sw = 0;
				break;
			}
			System.out.println(i + " : " + j);
			System.out.println("\n");
		}
	}
	
	
	/* 1-2+3-4+5-6+...-98+99 */
	void sequence_02() {
		System.out.println("sequence_02)\n[i] : [j]");
		int j = 0;
		for(int i = 1; i < 1002; i++) {
			j = j + i;
			if(i < 99) {
				i = i + 1;
				j = j - i;
			}
			System.out.println(i + " : " + j);
		}
	}


}
	
