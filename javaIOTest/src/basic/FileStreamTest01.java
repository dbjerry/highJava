package basic;

import java.io.FileInputStream;
import java.io.IOException;
/**
 * FileInputStream객체를 이용해서 파일 내용 읽기
 */
public class FileStreamTest01 {
	public static void main(String[] args) {
		FileInputStream fin = null;
		try{
			/* 파일과 연결된 바이트 스트림을 생성한다. */
			fin = new FileInputStream("C:/1_ddit/D_Other/test01.txt");
			
			int c;	//	읽어온 데이터를 저장할 변수
			
			while((c = fin.read()) != -1){
				/* 읽어온 문자 출력하기 */
				System.out.print((char)c);
			}
			fin.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
		
	}
}
