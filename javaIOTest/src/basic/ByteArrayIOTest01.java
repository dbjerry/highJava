package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * byte기반의 스트림 사용법
 */
public class ByteArrayIOTest01 {
	public static void main(String[] args) throws IOException {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data;	//	스트림으로 읽어온 자료를 저장할 변수
		
		/**
		 * 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		 */
		while( (data = input.read()) != -1 ){	//	입력
			output.write(data);	//	ㅡㅡㅡㅡㅡㅡㅡㅡㅡ	출력
		}
		
		/**
		 * 스트림에 출력된 자료들을 배열로 변환해서 저장한다.
		 */
		outSrc = output.toByteArray();
		
		System.out.println("inSrc ㅡ> " + Arrays.toString(inSrc));
		System.out.println("outSrc ㅡ> " + Arrays.toString(outSrc));
		
		input.close();
		output.close();
	}
}
