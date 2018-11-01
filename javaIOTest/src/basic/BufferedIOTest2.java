package basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest2 {
	public static void main(String[] args) {
		/* 문자 기반 Buffered 스트림 예제 */
		try{
			/* 이클립스에서 만든 자바 프로그램이 실행 */
			FileReader fr = new FileReader("./src/basic/ByteArrayIOTest01.java");
			BufferedReader br = new BufferedReader(fr);
			
			String temp = "";
			for(int i = 1; (temp = br.readLine()) != null; i++){
				System.out.printf("%4d : %s\n", i, temp);
			}
			br.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
