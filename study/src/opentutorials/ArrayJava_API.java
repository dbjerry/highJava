package opentutorials;

public class ArrayJava_API {
	public static void main(String[] args) {
		
		int[] numbers1 = new int[4];
		numbers1[0] = 10;
		numbers1[1] = 20;
		numbers1[2] = 30;
		
		int[] numbers2 = {10,20,30,40};
		
		int[] numbers3 = new int[]{10,20,30,40};
		
		System.out.println(numbers1[0]);		//10
		System.out.println(numbers1.length);	//4, 이 배열의 크기
		
		
		int i = 0;
		//while문은 조건들이 흩어져있기 때문에 그 사이, 사이에 다른 코드들이
		//간섭할 수 있고 그러므로 인해서 여러 버그들이 발생할 가능성이 많다.
		while(i < numbers1.length){
			//while문은 조건들이 흩어져있기 때문에 그 사이, 사이에 다른 코드들이
			//간섭할 수 있고 그러므로 인해서 여러 버그들이 발생할 가능성이 많다.
			System.out.println(numbers1[i]);	//10,20,30,0
			//while문은 조건들이 흩어져있기 때문에 그 사이, 사이에 다른 코드들이
			//간섭할 수 있고 그러므로 인해서 여러 버그들이 발생할 가능성이 많다.
			i++;
		}
		
		for(i = 0; i < numbers1.length; i++){
			System.out.println(numbers1[i]);	//10,20,30,0
		}
	}
}
