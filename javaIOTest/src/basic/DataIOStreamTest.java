package basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Java에서 사용되는 기본자료형 형식 그대로 입력출력하는 예제
 */
public class DataIOStreamTest {
	public static void main(String[] args) {
		
		try {
			FileOutputStream fout = new FileOutputStream("C:/1_ddit/D_Other/test.dat");
			
			/* DataOutputStream은 자료형에 맞게 출력해준다. */
			DataOutputStream dout = new DataOutputStream(fout);
			
			dout.writeInt(200);			//	정수형으로 출력
			dout.writeFloat(123.4F);	//	실수형으로 출력
			dout.writeBoolean(false);	//	논리형으로 출력
			
			System.out.println("출력 완료");
			dout.close();
			
			//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			
			/* 출력한 자료 읽어오기 */
			FileInputStream fin =  new FileInputStream("C:/1_ddit/D_Other/test.dat");
			DataInputStream din = new DataInputStream(fin);
			
			/* 읽어오는 순서는 출력한 순서대로 읽어온다. */
			System.out.println("정수형 자료 : " + din.readInt());
			System.out.println("실수형 자료 : " + din.readFloat());
			System.out.println("논리형 자료 : " + din.readBoolean());
			
			din.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
