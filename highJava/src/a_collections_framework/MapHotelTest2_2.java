package a_collections_framework;

import java.util.HashMap;
import java.util.Scanner;

/*
호텔 객실을 관리하는 프로그램.==> Map을 이용하여 처리한다.


**************************
  호텔 문을 열었습니다.
**************************


어떤 업무를 하시겠습니까?
*********************************************
1.체크인  2.체크아웃  3.객실상태  4.업무종료
*********************************************
메뉴선택 : 1 <-- 입력


어느방에 체크인 하시겠습니까?
방번호 입력 : 101 <-- 입력


누구를 체크인 하시겠습니까?
이름 입력 : 홍길동 <-- 입력
체크인 되었습니다.


어떤 업무를 하시겠습니까?
*******************************************
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 : 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 : 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 : 성춘향 <-- 입력
체크인 되었습니다

어떤 업무를 하시겠습니까?
*******************************************
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 : 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향
방번호 : 101, 투숙객 : 홍길동

어떤 업무를 하시겠습니까?
*******************************************
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 : 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 : 101 <-- 입력
체크아웃 되었습니다.

어떤 업무를 하시겠습니까?
*******************************************
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 : 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력
102호 방에는 이미 사람이 있습니다.

어떤 업무를 하시겠습니까?
*******************************************
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 : 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 : 101 <-- 입력
101호 방에는 체크인한 사람이 없습니다.

어떤 업무를 하시겠습니까?
*******************************************
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 : 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향

어떤 업무를 하시겠습니까?
*******************************************
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 : 4 <-- 입력

**************************
호텔 문을 닫았습니다.
**************************
*/

public class MapHotelTest2_2 {
	private Scanner scan;
	private HashMap<Integer, String> roomMap;
	
	
	// 생성자
	public MapHotelTest2_2() {
		scan = new Scanner(System.in);
		roomMap = new HashMap<Integer, String>();
	}
	
	
	// 메뉴를 출력하고 입력한 작업 번호를 반환하는 메서드
	public int displayMenu(){
		System.out.println();
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("********************************************");
		System.out.println(" 1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("********************************************");
		System.out.print("메뉴선택 : ");
		int num = scan.nextInt();
		return num;
	}
	
	
	// 프로그램을 시작하는 메서드
	public void start(){
		System.out.println();
		System.out.println("**************************");
		System.out.println("  호텔 문을 열었습니다.");
		System.out.println("**************************");
		
		while(true){
			// 메뉴를 출력하고 작업 번호를 입력 받는다.
			int choice = displayMenu();
			
			switch(choice){
				case 1 :  // 체크인
					checkIn();
					break;
				case 2 :  // 체크아웃
					checkOut();
					break;
				case 3 :  // 객실상태 
					roomState();
					break;
				case 4 :  // 업무종료 
					System.out.println();
					System.out.println("*************************");
					System.out.println("  호텔 문을 닫습니다.");
					System.out.println("*************************");
					return;
				default : 
					System.out.println("작업 번호를 잘못 입력 했습니다.");
					System.out.println("다시 입력하세요.");
			}
			
			
		}
	}
	
	
	// 체크인 하는 메서드
	public void checkIn(){

		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 : ");
		int roomNum = scan.nextInt();
		
		if(roomMap.containsKey(roomNum)){
			System.out.println(roomNum + "호 방에는 이미 사람이 있습니다.");
			return;
		}
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 : ");
		String name = scan.next();
		
		roomMap.put(roomNum, name);
		
		System.out.println("체크인 되었습니다. ");
		
	}
	
	// 체크 아웃
	public void checkOut(){
	
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 : ");
		int roomNum = scan.nextInt();
		
		if(!roomMap.containsKey(roomNum)){
			System.out.println(roomNum + "호 방에는 체크인한 사람이 없습니다.");
			return;
		}
		
		roomMap.remove(roomNum);
		System.out.println("체크아웃 되었습니다.");
		
		
	}
	
	// 객실 상태를 보여주는 메서드
	public void roomState(){

		System.out.println();
		for(Integer num : roomMap.keySet()){
			System.out.println("방번호 : " + num + ", 투숙객 : " +
						roomMap.get(num) );
		}
		System.out.println();
		
	}
	

	public static void main(String[] args) {
//		MapTest2 tt = new MapTest2();
//		tt.start();
		
		new MapHotelTest2_2().start();

	}

}




