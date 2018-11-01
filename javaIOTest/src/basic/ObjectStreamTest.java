package basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.sun.xml.internal.ws.api.model.SEIModel;

public class ObjectStreamTest {
	public static void main(String[] args) {
		
		/* Member의 인스턴스 생성 */
		Member mem1 = new Member("김종훈", 30, "금산");
		Member mem2 = new Member("김지태", 28, "대전");
		Member mem3 = new Member("박진", 28, "청주");
		Member mem4 = new Member("박찬배", 32, "서울");
		
		try {
			/* 객체를 파일에 저장하기 */
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream("C:/1_ddit/D_Other/memObj.bin")
							)
					);
			
			/* 쓰기 작업 */
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			
			/* 스트림 닫기 */
			oos.close();
			System.out.println("쓰기 작업 완료.");
			
			
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*
			
			
			/* 입력용 스트림 객체 생성 */
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("C:/1_ddit/D_Other/memObj.bin")
							)
					);
			
			Object obj = null;	//	읽어온 객체를 저장할 변수
			
			try {
				while((obj = ois.readObject()) != null){
					/* 읽어온 데이터 값 출력해보기 */
					
					/* 읽어온 객체를 원래의 형태로 형변환한다. */
					Member mem = (Member)obj;
					
					System.out.println("이름 : " + mem.getName());
					System.out.println("나이 : " + mem.getAge());
					System.out.println("주소 : " + mem.getAddr());
					System.out.println();
				}
				
				ois.close();
				
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
			}finally{
				System.out.println("출력 완료.");
			}
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}


/**
 * 저장할 데이터를 보관하는 객체 ㅡ> VO클래스 작성
 */
class Member implements Serializable{
	private static final long serialVersionUID = -2268379959456101368L;
	
	/*
	 * 	transient	ㅡ>	직렬화가 되지 않을 멤버 변수에 지정한다.
	 * 					직렬화가 되지 않는 멤버 변수는 기본값으로 초기화된다.
	 * 					(기본값 ㅡ> 참조변수 : null / 숫자변수 : 0)
	 */
	
	private String name;
	private int age;
//	private transient int age;
	private String addr;
//	private transient String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
}