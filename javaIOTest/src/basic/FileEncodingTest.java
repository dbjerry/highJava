package basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 한글이 저장된 파일 읽어오기
 * (한글의 인코딩을 방식을 지정해서 읽어오기)
 */
public class FileEncodingTest {
	public static void main(String[] args) {
		FileInputStream fin = null;
		InputStreamReader isr = null;	//	byte기반을 문자기반으로 변환해주는 객체
		
		try{
//			fin = new FileInputStream("C:/1_ddit/D_Other/test.txt");
			fin = new FileInputStream("C:/1_ddit/D_Other/test_utf8.txt");
			
			/**
			 * InputStreamReader객체는 파일의 인코딩 방식을 지정할 수 있다.
			 * 	MS949		ㅡ>	윈도우의 기본 한글 인코딩 방식(ANSI)
			 * 	UTF-8		ㅡ>	유니코드 UTF-8 인코딩 방식
			 * 	US-ASCII	ㅡ>	영문 전용 인코딩 방식
			 */
			
//			isr = new InputStreamReader(fin);
			
			/* 'test_utf8.txt'를 notepad++로 읽고 [인코딩]ㅡ>[utf-8(BOM없음)]ㅡ>저장 */
			isr = new InputStreamReader(fin, "UTF-8");
//			isr = new InputStreamReader(fin, "MS949");
			
			int c;
			while((c = isr.read()) != -1){
				System.out.print((char)c);
			}
			
			isr.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
