package basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 사용자가 입력한 내용을 그대로 파일로 저장하기
 */
public class FileWriterTest01 {
	public static void main(String[] args) {
		/**
		 * 콘솔(표준 입출력장치)과 연결된 입력 문자 스트림 생성
		 * InputStreamReader	ㅡ>	바이트 기반의 스트림을 문자 기반의
		 * 							스트림으로 변환해주는 보조 스트림이다.
		 */
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null;
		
		try {
			/* 파일 출력용 문자 스트림 객체 생성 */
			fw = new FileWriter("C:/1_ddit/D_Other/testChar.txt");
			
			int c;
			
			System.out.println("아무 내용이나 입력하세요.");
			
			while((c = isr.read()) != -1){
				fw.write(c);	//	콘솔에서 입력한 내용을 파일에 출력하기
			}
			
			isr.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
