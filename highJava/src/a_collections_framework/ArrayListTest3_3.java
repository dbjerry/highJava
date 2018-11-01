package a_collections_framework;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest3_3 {
	/*
	문제2) 5명의 별명을 입력하여 ArrayList에 저장하고
	 이 중에 별명의 길이가 제일 긴 별명을 출력하시오.
	(단, 별명의 길이는 제각각 모두 다르게 입력한다.)
	 
	문제3) 위의 문제에서 별명의 길이가 중복되는 것을 여러개
	입력했을 때도 처리하시오.
	 */
	public static void main(String[] args) {
		ArrayList<String> aliasList = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		
		for(int i = 0; i < 5; i++){
			System.out.print("친구의 별명을 입력해주세요 : ");
			String alias = scan.next();
			aliasList.add(alias);
		}
		int maxLength = aliasList.get(0).length();
		
		ArrayList<String> longAlias = new ArrayList<String>();
		String temp = "";
		for(String value : aliasList){
			if(temp.length()<value.length()){
				temp = value;
			}
			longAlias.add(temp);
		}
		for (String value : longAlias){
			
		}
	}
}
