package b_variable_argument;

/**
 * 메서드의 인수값이 여러개일 경우 어떻게 처리할까?
 * (파라미터 변수를 배열로 처리한다.)
 * 
 * 가변형인수	ㅡ>	- 메서드의 매개변수의 개수가 다를 때마다 사용한다.
 * 				- 가변형인수는 메서드 내에서 처리될 때는
 * 				배열로 인식되어 처리된다.
 * 				- 가변형인수는 한가지 자료형만 사용할 수 있다.
 * 				- 가변형인수가 아닌 매개변수와 같이 사용할 때는
 * 				가변형인수를 제일 뒤에 배치해야 한다.
 */


public class ArgsTest {
	
	/* 정수형 배열 데이터들의 합계를 반환하는 메서드 */
	public int sumArr(int[] data){
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//	가변형인수를 이용한 메서드
	public int sumArgs(int ... data){
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//	이름과 점수들(개수 미정)을 인수로 받아서
	//	점수들의 합계를 구한 후 '이름과 점수의 합계를 출력하는메서드'
	public void printSum(String name, int...data){
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		System.out.println(name + "의 총점 : " + sum);
	}
	
	
	
	
	public static void main(String[] args) {
		ArgsTest at = new ArgsTest();
		
		int[] nums = {10,20,30,40,50};
		
		int result = at.sumArr(nums);
		System.out.println("result : " + result);
		
		result = at.sumArr(new int[]{123,456,789});
		System.out.println(result);
		
		System.out.println();
		int result2 = at.sumArgs(1,2,3,4,5,6,7,8,9,10);
		System.out.println("result2 : " + result2);
		
		result2 = at.sumArgs(123,456,789);
		System.out.println("result2 : " + result2);
		
		at.printSum("홍길동", 60,50,40);
		
		
		
	}
}
