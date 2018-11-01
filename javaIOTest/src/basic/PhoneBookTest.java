package basic;
/**
 * 전화번호부 관리 프로그램
 * @author 김지태
 * 2018/07/19
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
	전화번호 관리 프로그램
	
	이름, 주소, 전화번호 속성을 갖는 Phone클래스를 만들고
	이 Phone클래스를 이용하여 전화번호 정보를 관리하는 프로그램을
	완성하시오.
	
	이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체 출력하는 
	기능이 있다.
	
	전체 전화번호 정보는 Map을 이용하여 관리한다.
	(단, 동명이인은 없다고 가정한다.)
	(key는 이름으로하고 value는 Phone클래스의 인스턴스로 한다.)
	
	
	저장파일명 : "C:/1_ddit/D_Other/phoneBook.bin"
	
	
	실행 예시)
	
	****************************
	전화번호 관리 프로그램
	****************************
	
	--------------------------
	1. 전화번호 등록
	2. 전화번호 수정
	3. 전화번호 삭제
	4. 전화번호 검색
	5. 전화번호 전체 출력
	6. 전화번호 저장
	7. 전화번호 불러오기
	0. 프로그램 종료
	--------------------------
	번호입력 >> 1
	
	새롭게 등록할 전화번호 정보를 입력하세요
	이름 >> 홍길동
	전화번호 >> 010-1111-1111
	주소 >> 대전시 중구 대흥동
	
	등록되었습니다.
	
	--------------------------
	1. 전화번호 등록
	2. 전화번호 수정
	3. 전화번호 삭제
	4. 전화번호 검색
	5. 전화번호 전체 출력
	6. 전화번호 저장
	7. 전화번호 불러오기
	0. 프로그램 종료
	--------------------------
	번호입력 >> 5
	
	==================================================
	번호    이름     전화번호            주소
	==================================================
	1    홍길동    010-1111-1111  대전시 중구 대흥동
	
	~~~~~~
	==================================================
	출력완료...
	
	--------------------------
	1. 전화번호 등록
	2. 전화번호 수정
	3. 전화번호 삭제
	4. 전화번호 검색
	5. 전화번호 전체 출력
	6. 전화번호 저장
	7. 전화번호 불러오기
	0. 프로그램 종료
	--------------------------
	번호입력 >> 2
	
	수정할 전화번호 정보를 입력하세요.
	이름 >> 일지매   ==> 만약 등록이 안된 사람이면..
	
	'일지매'는 등록되지 않은 사람입니다.
	
	--------------------------
	1. 전화번호 등록
	2. 전화번호 수정
	3. 전화번호 삭제
	4. 전화번호 검색
	5. 전화번호 전체 출력
	6. 전화번호 저장
	7. 전화번호 불러오기
	0. 프로그램 종료
	--------------------------
	번호입력 >> 2
	
	수정할 전화번호 정보를 입력하세요.
	이름 >> 홍길동
	수정할 전화번호 >> 010-2222-2222
	수정할 주소 >> 대전시 유성구 궁동
	
	'홍길동' 정보가 수정되었습니다.
	
	--------------------------
	1. 전화번호 등록
	2. 전화번호 수정
	3. 전화번호 삭제
	4. 전화번호 검색
	5. 전화번호 전체 출력
	6. 전화번호 저장
	7. 전화번호 불러오기
	0. 프로그램 종료
	--------------------------
	번호입력 >>   
*/
public class PhoneBookTest {
	Scanner scan = new Scanner(System.in);
	PatternUtil pattern = new PatternUtil();
	Map<String, Phone> phoneMap = new HashMap<String, Phone>();
	
	public static void main(String[] args) {	
		/* PhoneBookTest 객체생성 */
		PhoneBookTest pbt = new PhoneBookTest();
		
		/* 참조변수 pbt를 통해 start()를 호출 */
		pbt.start();
		
	}	
	
	/* program start method */
	public void start() {
		System.out.println("\n\n**************************");
		System.out.println("       전화번호부 관리 프로그램");
		System.out.println("**************************\n");
		
		while(true){
			System.out.println("\n──────────────────────────");
			System.out.println(" 1)등록  :: 2)수정  :: 3)삭제");
			System.out.println("──────────────────────────");
			System.out.println(" 4)검색  :: 5)출력  :: 6)저장");
			System.out.println("──────────────────────────");
			System.out.println(" 7)불러오기        ::    0)종료");
			System.out.println("──────────────────────────");
			System.out.print("서비스메뉴 입력  > ");
			scan = new Scanner(System.in);	// <ㅡ 없으면 InputMismatchException 발생
			int select;
			try{
				select = scan.nextInt();
			}catch(Exception e){
				System.out.println("\n\n\n잘못된 입력방식입니다. 다시 입력해주세요.\n\n\n");
				continue;
			}
			switch(select){
			
			case 1:
				add();		//ㅡㅡㅡㅡㅡ	1.전화번호부 등록
				break;				
			case 2:
				update();	//ㅡㅡㅡㅡㅡ	2.전화번호부 수정
				break;				
			case 3:
				remove();	//ㅡㅡㅡㅡㅡ	3.전화번호부 삭제
				break;				
			case 4:
				search();	//ㅡㅡㅡㅡㅡ	4.전화번호부 검색
				break;				
			case 5:
				print();	//ㅡㅡㅡㅡㅡ	5.전화번호부 출력
				break;
			case 6:
				save();		//ㅡㅡㅡㅡㅡ	6.전화번호부 저장
				break;
			case 7:
				load();		//ㅡㅡㅡㅡㅡ	7.전화번호부 불러오기
				break;
			case 0:			//ㅡㅡㅡㅡㅡ	0.프로그램 종료
				System.out.println("\n\n**************************");
				System.out.println("         프 로 그 램    종 료");
				System.out.println("**************************\n");
				return;			
			default :
				System.out.println("\n\n잘못된 메뉴선택입니다.");
				System.out.println("해당 서비스메뉴를 다시 입력해주세요.\n\n");
			}
		}
	}


	/* 전화번호부 신규등록(create) 메서드 */
	public void add(){
		System.out.println("\n신규 등록할 정보를 입력하세요.");
		System.out.println("======= 입 력 예 시 =======");
		System.out.println("이름 > 홍길동");
		System.out.println("전화번호 > 010-1111-1111");
		System.out.println("주소 > 대전시 중구 대흥동");
		System.out.println("=======================");
		String name;
		String number;
		String address;
		
		while(true){
			System.out.print("이름 > ");
			name = scan.next();
			/* 위에서 받은 name이 phoneMap에 들어있는 key인지 확인(있으면 true, 없으면 false)하는 if문 */
			if(phoneMap.containsKey(name)){
				System.out.println("중복된 이름이 있습니다.\n다시 입력해주세요.");
				continue;
			}else{
				if(pattern.regName(name)){	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ	정규식에 맞는지 확인하는 if문
					
				}else{
					System.out.println("이름을 예시에 맞춰 다시 입력해주세요.");
					continue;
				}
				while(true){
					System.out.print("전화번호 > ");
					number = scan.next();
					if(pattern.regNumber(number)){	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ	정규식에 맞는지 확인하는 if문
						break;
					}else{
						System.out.println("전화번호를 예시에 맞춰 다시 입력해주세요.");
						continue;
					}
				}
				while(true){
					System.out.print("주소 > ");
					address = scan.next();
					if(pattern.regAddress(address)){	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ	정규식에 맞는지 확인하는 if문
						break;
					}else{
						System.out.println("주소를 예시에 맞춰 다시 입력해주세요.");
						continue;
					}
				}
				
				/* phoneMap에 name(key), phone(value)를 저장 */
				phoneMap.put(name, new Phone(name, number, address));
				System.out.println("\n" + name + "님, 신규등록됐습니다.\n");
				break;
			}
		}
	}
	
	
	/* 전화번호부 수정(update) 메서드 */
	public void update(){
		System.out.print("\n수정하려는 분의 성함을 먼저 입력해주세요.\n > ");
		scan = new Scanner(System.in);	//	<ㅡ	없으면 InputMismatchException 발생할까봐 예방
		String name = scan.next();
		if(pattern.regName(name)){	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ	정규식에 맞는지 확인하는 if문
			if (phoneMap.containsKey(name)){
				while(true){
					System.out.println("───────────────────────");
					System.out.println(" 1)번호수정  :: 2)주소수정    ");
					System.out.println("───────────────────────");
					System.out.print("수정할 메뉴입력 > ");
					int select;
					try{
						select = scan.nextInt();
					}catch(Exception e){
						System.out.println("\n\n\n잘못된 입력방식입니다. 다시 입력해주세요.\n\n\n");
						continue;
					}
					switch(select){
					case 1:
						System.out.print("\n" + name + "님, 바꾸고 싶은 번호로 입력해주세요.\n(입력예시: 010-1234-5678)\n > ");
						String number = scan.next();
						if(pattern.regNumber(number)){	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ	정규식에 맞는지 확인하는 if문
							phoneMap.get(name).setNumber(number);	//ㅡㅡㅡ	해당 key값(name)의 value(number)를 setter를 사용해서 저장
							System.out.println("\n변경되었습니다.\n");
							return;
						}else{
							System.out.println("전화번호를 예시에 맞춰 다시 입력해주세요.");
							continue;
						}
						
					case 2:
						System.out.print("\n" + name + "님, 바꾸고 싶은 주소로 입력해주세요.\n(입력예시:대전(광역)시 유성구 관평동)\n > ");
						String address = scan.next();
						if(pattern.regAddress(address)){	//ㅡㅡㅡㅡㅡㅡㅡㅡ	정규식에 맞는지 확인하는 if문
							phoneMap.get(name).setAddress(address);	//ㅡㅡㅡ	해당 key값(name)의 value(number)를 setter를 사용해서 저장
							System.out.println("\n변경되었습니다.\n");
							return;
						}else{
							System.out.println("\n주소를 예시에 맞춰 다시 입력해주세요.\n");
							continue;
						}
					}
				}
			}else{
				System.out.println("\n\n" + name + "(으)로 등록되있는 이름이 없습니다.\n");
			}
		}else{
			System.out.println("등록시 작성하셨던 이름을 다시 입력해주세요.");
			update();
		}
	}		
		
	/* 전화번호부 삭제(delete) 메서드 */	
	public void remove() {
		System.out.print("\n삭제하려는 분의 성함을 먼저 입력해주세요.\n > ");
		scan = new Scanner(System.in);	//	 없으면 InputMismatchException 발생할까봐 예방
		String name = scan.next();
		if(pattern.regName(name)){	//ㅡㅡㅡ	정규식에 맞는지 확인하는 if문
			if (phoneMap.containsKey(name)){
				System.out.println("\n" + name + "님의 모든 정보가 삭제됐습니다.\n");
				phoneMap.remove(name);	//	해당 key값(name)과 일치하는 key-value객체를 삭제한다.
			}
		}else{
			System.out.println("이름을 예시에 맞춰 다시 입력해주세요.");
			remove();
		}
	}	
		
		
	/* 전화번호부 검색(read) 메서드 */
	public void search() {
		System.out.print("\n검색을 위해 성함을 먼저 입력해주세요.\n > ");
		String name = scan.next();
		if(pattern.regName(name)){	//ㅡㅡㅡ	정규식에 맞는지 확인하는 if문
			if (phoneMap.containsKey(name)) {
				System.out.println(phoneMap.get(name).toString());
			}else{
				System.out.println("\n" + name + "(으)로 등록된 이름이 없습니다.\n");
			}
		}else{
			System.out.println("\n\n이름을 예시에 맞춰 다시 입력해주세요.\n\n");
			search();
		}
	}	
	
	
	/* 전화번호부 출력(read) 메서드 */
	public void print() {
		for (String key : phoneMap.keySet()) {
			System.out.println(key + " : " + phoneMap.get(key));
		}
		if (phoneMap.isEmpty()) {
			System.out.println("\n등록된 번호가 없습니다.\n");
			return;
		}
	}

	
	/* 전화번호부 저장(save) 메서드 */
	private void save() {
		try {
			/* 객체를 파일에 저장하기 */
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream("C:/1_ddit/D_Other/phoneBook.bin")
							)
					);
			/* 쓰기 작업 */
			for (String key : phoneMap.keySet()) {
				oos.writeObject(phoneMap);
			}
			
			/* 스트림 닫기 */
			oos.close();
			System.out.println("파일 저장완료.");
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}


	private void load() {
		
		try{
			/* 입력용 스트림 객체 생성 */
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("C:/1_ddit/D_Other/phoneBook.bin")
							)
					);
			
			/* 읽어온 객체를 저장할 변수 */
			Object phoneList = null;
			
			try {
				while((phoneList = ois.readObject()) != null){
					
					/* 읽어온 객체를 HashMap타입으로 생성 및 초기화 및 형변환 */
					HashMap<String, Phone> p = (HashMap)phoneList;
					
					/**
					 * each for문으로 불러온 객체를 반복.
					 */
					for(String key : p.keySet()){
						System.out.println(p.get(key));
					}
					
					/* phoneMap에 불러온 객체를 다시 저장 */
					phoneMap.putAll(p);
					
					break;
				}
				
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
			}finally{
				ois.close();
				System.out.println("불러오기 완료.");
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}


/*
 * 저장할 데이터를 보관하는 객체 -> VO클래스 작성
 */
class Phone implements Serializable{
	private static final long serialVersionUID = 3610927249866053822L;

	private String name;		//	이름을 저장할 변수
	private String number;		//	번호를 저장할 변수
	private String address;		//	주소를 저장할 변수
	
	public Phone() {
		
	}

	public Phone(String name, String number, String address) {
		this.name = name;
		this.number = number;
		this.address = address;
	}

	public String getName() {	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ	이름을 반환해줄 getter
		return name;
	}
	
	public void setName(String name) {	//ㅡㅡㅡㅡㅡㅡㅡ	이름을 저장해줄 setter
		this.name = name;
	}
	
	public String getNumber() {	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ	번호를 반환해줄 getter
		return number;
	}
	
	public void setNumber(String number) {	//ㅡㅡㅡㅡㅡ	번호를 저장해줄 setter
		this.number = number;
	}
	
	public String getAddress() {	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ	주소를 반환해줄 getter
		return address;
	}
	
	public void setAddress(String address) {	//ㅡㅡㅡ	주소를 저장해줄 setter
		this.address = address;
	}

	@Override
	public String toString() {
		return "이름 : " + name + ", 전화번호 : " + number + ", 주소 : "
				+ address;
	}
}


/* 정규식 클래스 */
class PatternUtil {

	/* 이름 정규식 */
	public boolean regName(String name){
		Pattern pattern = Pattern.compile("^([가-힣]{2,5})|(^[A-Z].[a-zA-Z]{1,15})$");
		Matcher matcher = pattern.matcher(name);
		
		if(matcher.find()){
			return true;
		}else{
			return false;
		}
	}
	
	/* 전화번호 정규식 */
	public boolean regNumber(String number){	
		Pattern pattern = Pattern.compile("^01[016789]-[1-9][0-9]{2,3}-\\d{4}$");
		Matcher matcher = pattern.matcher(number);
		
		if(matcher.find()){
			return true;
		}else{
			return false;
		}
	}
	
	/* 주소 정규식 */
	public boolean regAddress(String address){	
		Pattern pattern = Pattern.compile("^[가-힣]*$");
		Matcher matcher = pattern.matcher(address);
		
		if(matcher.find()){
			return true;
		}else{
			return false;
		}
	}
}

