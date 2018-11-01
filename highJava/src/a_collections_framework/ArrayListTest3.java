package a_collections_framework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.sun.javafx.scene.control.behavior.ListCellBehavior;

public class ArrayListTest3 {
	/*
		문제2) 5명의 별명을 입력하여 ArrayList에 저장하고
		 이 중에 별명의 길이가 제일 긴 별명을 출력하시오.
		 (단, 별명의 길이는 제각각 모두 다르게 입력한다.)
		
		문제3) 위의 문제에서 별명의 길이가 중복되는 것을 여러개
		입력했을 때도 처리하시오.
	*/
	public static void main(String[] args) {
		
		System.out.print("길이가 다른 별명 5개를 입력해주세요.\n>");
		Scanner scan = new Scanner(System.in);
		List<String> aliasList = new ArrayList<String>();
		List<Integer> lengthList = new ArrayList<Integer>();
		
		String alias = scan.next();
		lengthList.add(alias.length());
		aliasList.add(alias);
		
		for(int i = 0; i < 4; i++){
			System.out.print(">");
			String alias2 = scan.next();
			if(lengthList.contains(alias2.length())){
				System.out.println("중복되는 길이의 닉네임이 존재합니다.\n다시 입력해주세요\n>");
				i--;
				continue;
			}else{
				lengthList.add(alias2.length());
				aliasList.add(alias2);
			}
		}
		
		ArrayList<String> longAlias = new ArrayList<String>();
		int length1 = 0;
		String longName = "";
		for(String value : aliasList){
			int length2 = value.length();
			if (length1 < length2){
				length1 = length2;
				longName = value;
			}else{
				continue;
			}
		}
		longAlias.add(longName);
		System.out.println(longAlias);
	}
}
