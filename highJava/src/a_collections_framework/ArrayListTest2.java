package a_collections_framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListTest2{
	/*

	문제1) 5명의 사람 이름을 입력하여  ArrayList에 저장하고
		 이 중에 '김'씨 성을 가진 사람의 이름을 출력하시오.
		 (단, 입력은 Scanner를 이용하여 입력받는다.)


	문제2) 5명의 별명을 입력하여 ArrayList에 저장하고
		 이 중에 별명의 길이가 제일 긴 별명을 출력하시오.
		 (단, 별명의 길이는 제각각 모두 다르게 입력한다.)
		 
	 */
	public static void main(String[] args){
		System.out.println();
		List<String> nameArr = new ArrayList<String>();
		
		
		Scanner scanner = new Scanner(System.in);
		
		for (int i = 0; i < 5; i++){
			System.out.print((i+1) + "번째 이름 : ");
			String name = scanner.next();
			nameArr.add(name);
		}
		System.out.println();
		System.out.println("김씨 성을 가진 사람들 : ");
//		for(int i = 0; i < nameArr.size(); i++){
			
//			if(nameArr.get(i).charAt(0)=='김'){
//			System.out.println(nameArr.get(i));
//			}
			
//			if(nameArr.get(i).substring(0,1).equals("김")){
//				System.out.println(nameArr.get(i));
//			}
			
//			if(nameArr.get(i).indexOf("김")==0){
//				System.out.println(nameArr.get(i));
//			}
			
//			if(nameArr.get(i).startsWith("김")){
//				System.out.println(nameArr.get(i));
//			}
//		}
		for (String value : nameArr){
			if(value.contains("김")){
				System.out.println(value);
			}
		}
		scanner.close();
	}
}