package programmers;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListExam {
	public static void main(String[] args) throws java.lang.Exception {
		
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(10);
		numbers.add(20);
		numbers.add(30);
		numbers.add(40);
		System.out.println("add(값)");
		System.out.println(numbers);
		
		
		numbers.add(1, 50);
		System.out.println("\nadd(인덱스, 값)");
		System.out.println(numbers);
		
		
		numbers.remove(2);
		System.out.println("\nremove(인덱스)");
		System.out.println(numbers);
		
		
		numbers.get(2);
		System.out.println("\nget(인덱스)");
		System.out.println(numbers.get(0));
		System.out.println(numbers.get(1));
		System.out.println(numbers.get(2));
		System.out.println(numbers.get(3));
		
		
		numbers.size();
		
		
		
		System.out.println(numbers);
		
		
		System.out.println("\nfor each");
		for(int value : numbers){
			System.out.println(value);
		}
		
		System.out.println("\nfor");
		for(int i = 0; i < numbers.size(); i++){
			System.out.println(numbers.get(i));
		}
		
	}
	
}
