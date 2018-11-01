package basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
/**
 * C:/1_ddit/D_Other/images.jpg파일을
 * C:/1_ddit/D_Other/test 폴더 안에 복사하시오
 */
public class FileCopyTest {
	public static void main(String[] args) {

		try {
			/* 복사할 파일 스트림 객체 생성 */
			FileInputStream fin = new FileInputStream("C:/1_ddit/D_Other/images.jpg");
			
			/*
			 * 버퍼 스트림을 사용.
			 * 버퍼의 크기를 정하지 않으면 기본 크기는 8192Byte가 된다.
			 */
			BufferedInputStream bis = new BufferedInputStream(fin);
			
			/* 복사될 파일 스트림 객체 생성 */
			FileOutputStream fout = new FileOutputStream("C:/1_ddit/D_Other/test/images.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			int c;
			while((c = bis.read()) != -1){
				bout.write(c);
			}
			
			/* 버퍼에 남아 있는 내용을 모두 출력 */
			bout.flush();
			
			System.out.println("복사 완료.");
			
			bis.close();
			bout.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
