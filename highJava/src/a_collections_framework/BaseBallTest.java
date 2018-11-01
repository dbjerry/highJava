package a_collections_framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
	문제1) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
	컴퓨터가 제시할 숫자는 랜덤을 이용하여 구한다.
	('스트라이크 = S', '볼 = B'로 출력한다.)
	
	실행예)
		
		컴퓨터
		랜덤값	->	9	5	7
							
		사용자				
		숫자입력	->	3	5	6	(1S 0B)
				->	7	8	9	(0S	2B)
							
		...		...			
							
				->	9	5	7	(3S 0B)
				
		n번만에 맞췄군요.
*/

public class BaseBallTest {
	
	public static void main(String[] args) {
//		System.out.println("┌──────────────────────────────────┐");
//		System.out.println("│             /  |  \\              │");
//		System.out.println("│           N U M B E R  \t   │");
//		System.out.println("│         B A S E B A L L  \t   │");
//		System.out.println("│       G A M E - S T A R T  \t   │");
//		System.out.println("│       /        |       \\         │");
//		System.out.println("└──────────────────────────────────┘\n\n");
//		
//		Set<Integer> setBaseBall = new HashSet<Integer>();
//		
//		while(setBaseBall.size()<3){
//			int random = (int)(Math.random()*9+1);
//			setBaseBall.add(random);
//		}
//		
//		List computer = new ArrayList(setBaseBall);
//
//		Collections.shuffle(computer);
//		
//		System.out.println(computer);
//		System.out.println("Computer is  r e a d y.\n\n");
//		
//		List<Integer> player = new ArrayList<Integer>();
//		int count = 0;
//		
//		game: while(true){
//			
//			Scanner scan = new Scanner(System.in);
//			
//			while(player.size()<3){
//				System.out.print("Player's Choice > ");
//				Integer ballNum = scan.nextInt();
//				player.add(ballNum);
//			}
//			int s = 0;
//			int b = 0;
//			for (int i = 0; i < player.size(); i++) {
//				if(computer.get(i) == player.get(i)){
//					s++;
//				}
//				for(int j = 1; j <player.size(); j++){
//					if(computer.get(i)==player.get(j)||player.get(i)==computer.get(j)){
//						b++;
//					}
//				}
//			}
//			System.out.println(s + "S");
//			System.out.println(b + "B");
//			
//			count++;
//			
//			if(s == 3){
//				break;
//			}else{
//				player.clear();
//			}
//		}
//		System.out.println("G A M E - C L E A R\n(" + count + "회 시도)");
		
		
		BaseBallTest baseball = new BaseBallTest();
		baseball.startGame();
		
	}
	
	
	// 난수를 
	ArrayList<Integer> numList = new ArrayList<Integer>();
	
	// 사용자가 입력한 값을 저장할 리스트
	ArrayList<Integer> userList = new ArrayList<Integer>();
	
	int strike;		// 스트라이크 개수가 저장될 변수
	int ball;		// 볼의 개수가 저장될 변수
	
	Scanner scan = new Scanner(System.in);
	
	// 1~9사이의 서로 다른 난수 3개를 만들어서 리스트에 저장하는 메서드
	public void getNum(){
		Set<Integer> bbNum = new HashSet<Integer>();
		
		// Set을 이용한 난수 3개 만들기
		while(bbNum.size()<3){
			bbNum.add((int)(Math.random()*9+1));
		}
		
		// Set의 값들을 List에 저장하기
		// Java의 컬렉션들은 서로 변환하기 쉽다.
		// 방법 1.
		// numList = new ArrayList<Integer>(bbNum);
		
		
		// 방법 2. Iterator를 이용한 방법
		Iterator<Integer> it = bbNum.iterator();
		
		numList.clear(); // 기존의 리스트에 저장된 모든 값을 삭제한다.
		while(it.hasNext()){
			numList.add(it.next());
		}
		
		// 데이터 섞기
		Collections.shuffle(numList);
		System.out.println(numList);
		
	}
	
	
	// 사용자로부터 3개의 정수를 입력받아 리스트에 저장하는 메서드
	public void inputNum(){
		int n1, n2, n3; // 입력한 값을 저장할 변수
	
		do{
			System.out.print("숫자입력 >> ");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();
			
			if(n1 == n2 || n1 == n3 || n2 == n3){
				System.out.println("중복되는 숫자는 입력할 수 없습니다.\n다시 입력해주세요.");
			}
		}while(n1 == n2 || n1 == n3 || n2 == n3);
		
		userList.clear();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}
	
	// 스트라이크와 볼 판정 및 결과를 출력하는 메서드
	public void ballCount(){
		strike = 0;
		ball = 0;
		
		for(int i = 0; i < numList.size(); i++){
			for(int j = 0; j < userList.size(); j++){
				if(numList.get(i) == userList.get(j)){ // 값이 같은지 비교
					if(i == j){
						strike++;
					}else{
						ball++;
					}
				}
			}
		}
		System.out.println(userList + "-->" + strike + "S" + ball + "B");
	}
	
	// 게임을 시작하는 메서드
	public void startGame(){
		// 난수를 만드는 메서드 호출
		getNum();
		
		int cnt = 0; // 몇 번만에 맞췄는지 저장하는 변수
		
		do{
			cnt++;
			
			//사용자 입력 메서드 호출
			inputNum();
			
			// 볼 카운트 메서드 호출
			ballCount();
			
		}while(strike != 3); // 3스트라이크가 될 때까지 반복
		System.out.println(cnt + "번째만에 맞췄군요!");
	}
}
