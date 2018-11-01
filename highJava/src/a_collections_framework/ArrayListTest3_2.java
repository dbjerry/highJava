package a_collections_framework;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest3_2 {
	public static void main(String[] args) {
		ArrayList<String> aliasList = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		
		for(int i = 0; i < 5; i++){
			System.out.print((i+1) + "번째 별명 : ");
			String alias = scan.next();
			
			boolean chk = false;
			for(int j = 0; j < i; j++){
				if(alias.length() == aliasList.get(j).length()){
					chk = true;
				}
			}
			if(chk==true){
				System.out.println("중복된 길이의 별명입니다. 다시 작성해주세요.");
				i--;
				continue;
			}
			aliasList.add(alias);
		}
		
		String maxAlias = aliasList.get(0);
		for(int i = 0; i < aliasList.size(); i++){
			String temp = aliasList.get(i);
			if(temp.length() > maxAlias.length()){
				maxAlias = temp;
			}
		}
		System.out.println("제일 긴 별명 : " + maxAlias);
		
	}
}
