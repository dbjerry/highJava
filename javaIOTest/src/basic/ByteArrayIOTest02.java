package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02{
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		byte[] temp = new byte[4];
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			while(input.available() > 0){
//				input.read(temp);
//				output.write(temp);
				
				/* read()의 반환값은 실제 읽어온 데이터 개수이다. */
				int len = input.read(temp);
				
				/* temp배열 중에서 0번째부터 len개수만큼 출력한다. */
				output.write(temp, 0, len);
				
				System.out.println("temp ㅡ> " + Arrays.toString(temp));
			}
			
			outSrc = output.toByteArray();
			System.out.println(" inSrc ㅡ> " + Arrays.toString(inSrc));
			System.out.println("  temp ㅡ> " + Arrays.toString(temp));
			System.out.println("outSrc ㅡ> " + Arrays.toString(outSrc));
			
			input.close();
			output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
