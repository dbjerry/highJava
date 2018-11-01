package basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamTest02 {
	public static void main(String[] args) {
		
		FileInputStream fin = null;
		FileOutputStream fout = null;
		
		try{
			fout = new FileOutputStream("C:/1_ddit/D_Other/out.txt");
			
			for(char i = 'a'; i <= 'z'; i++){
				fout.write(i);
			}
			fout.close();
			System.out.println("출력 작업 완료.");

			// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			
			/**
			 * 저장된 파일 내용을 가져와 읽고 확인하기
			 */
			fin = new FileInputStream("C:/1_ddit/D_Other/out.txt");
			int c;
			
			System.out.print("\"");
			while((c = fin.read()) != -1){
				System.out.print((char)c);
			}
			System.out.print("\"");
			
			fin.close();
			System.out.println(" 입력 완료.");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

