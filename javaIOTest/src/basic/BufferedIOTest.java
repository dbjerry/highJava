package basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest {
	public static void main(String[] args) {
		
		/* 입출력의 성능 향상을 위해 Buffered 스트림을 이용한다. */
		FileOutputStream fout = null;
		BufferedOutputStream bout = null; 
		
		try{
			/* 출력용 파일 스트림 객체 생성 - 기반이 되는 스트림 */
			fout = new FileOutputStream("C:/1_ddit/D_Other/bufferTest.txt");
			
			/**
			 * 크기가 5인 버퍼스트림 객체 생성
			 * 버퍼의 기본 크기는 8192 bytes이다.
			 */
			bout = new BufferedOutputStream(fout, 5);
			for(int i = '1'; i <= '9'; i++){
				bout.write(i);
			}
			
			bout.flush();	//	작업을 종료하기 전에 버퍼에 남아 있는
							//	데이터를 모두 출력시킨다.
			
			bout.close();
			
			System.out.println("작업 끝");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
