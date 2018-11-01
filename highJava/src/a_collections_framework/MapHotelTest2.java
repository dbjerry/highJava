package a_collections_framework;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
호텔 객실을 관리하는 프로그램.


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

public class MapHotelTest2 {
	
	Scanner scan = new Scanner(System.in);
	Map<Integer, String> hotel = new HashMap<Integer, String>();
	
	Integer roomNum = 0;
	String guestName = "";
	boolean power = false;
	
	Set<Integer> keySet = hotel.keySet();
	
	
	public static void main(String[] args) {
		
		MapHotelTest2 maptest2 = new MapHotelTest2();
		maptest2.Entrance();
		
	}
	
	public void Entrance(){
		
		System.out.println("\n\n*************************");
		System.out.println("        호텔 문을 열었습니다.");
		System.out.println("*************************");
		
		while(!power){
			
			System.out.println("\n\n어떤 업무를 하시겠습니까?");
			System.out.println("*********************************************");
			System.out.println("1.체크인  2.체크아웃  3.객실상태  4.업무종료");
			System.out.println("*********************************************");
			
			System.out.print("\n\n메뉴선택 > ");
			
			int selectNum = 0;
			
			try{
				selectNum = scan.nextInt();
			}catch(Exception e){
				System.out.println("해당 업무의 번호를 다시 눌러주세요.");
				continue;
			}
			
			switch(selectNum){
			
			case 1:
				checkIn();
				break;
			
			case 2:
				checkOut();
				break;
			case 3: 
				roomState();
				break;
			case 4:
				System.out.println("감사합니다, 안녕히 가세요.");
				return;
			}
		}
	}


	/* 체크인 메서드 */
	private void checkIn() {
		
		while(true){
			
			System.out.println("\n\n어느방에 체크인 하시겠습니까?\n\n");
			
			try{
				System.out.print("방번호 입력 > ");
				roomNum = scan.nextInt();
				System.out.print("입실하실 고객명 입력 > ");
				guestName = scan.next();
			}catch(Exception e){
				System.out.println("제시한 메뉴에 알맞은 내용을 입력해주세요.");
				continue;
			}
			
			if(!(hotel.containsKey(roomNum))){
				hotel.put(roomNum, guestName);
				System.out.println("\n" + roomNum + "호 방으로 " + guestName + "님이 체크인 되었습니다.");
				break;
			}else{
				System.out.println(roomNum + "호 방에는 이미 사람이 있습니다.");
			}	
		}
	}
	

	/* 체크아웃 메서드 */
	private void checkOut() {
		
		while(true){
		
			System.out.println("\n\n어느방을 체크아웃 하시겠습니까?\n\n");
		
			try{
				System.out.print("방번호 입력 > ");
				roomNum = scan.nextInt();
			}catch(Exception e){
				System.out.println("제시한 메뉴에 알맞은 내용을 입력해주세요.");
				continue;
			}
			
			if(hotel.containsKey(roomNum)){
				System.out.println("\n\n" + hotel.get(roomNum) + "님이 체크인 하셨던\n" + roomNum + "가 체크아웃되었습니다.\n감사합니다, 또 이용해주세요.");
				hotel.remove(roomNum);
				break;
			}else{
				System.out.println("해당 호실은 체크인이 되어있지 않습니다.\n확인하시고 다시 체크아웃 하세요.");
				continue;
			}
		}	
	}


	/* 객실상태 확인 메서드 */
	private void roomState() {
		
		if(hotel.isEmpty()){
			System.out.println("체크인 되어있는 객실이 없습니다.\n영업팀은 분발하세요.");
		}
		for(Integer key : hotel.keySet()){
			System.out.println(key + "호     |   투숙객 : " + hotel.get(key));
		}
		
	}
	
	
//	/* finish 메서드 */
//	private void finish() {
//		power = true;
//
//		System.out.println("감사합니다, 안녕히 가세요.");
//	}
	
}


