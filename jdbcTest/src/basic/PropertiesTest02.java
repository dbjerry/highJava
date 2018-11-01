package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesTest02 {
	/*
	 * 외부의 properties파일을 읽어와 Properties객체로 처리
	 */
	public static void main(String[] args) {
		
		/* 읽어온 정보를 저장할 Properties객체 생성 */
		Properties prop = new Properties();
		
		/* 읽어올 파일명을 이용하여 File객체 생성 */
		File file = new File("res/dp.properties");
		
		try{
			/* 파일을 읽기 위한 InputStream객체 생성 */
			FileInputStream fin = new FileInputStream(file);
			
			/* Properties객체로 파일 읽어오기 */
			prop.load(fin);	//	파일 내용을 읽어와 key와 value값으로
							//	분류한 후 Properties객체에 담아준다.
			
			/* 읽어온 정보를 출력해보기 */
			Enumeration<String> names = (Enumeration<String>)prop.propertyNames();
			
			while(names.hasMoreElements()){
				String key = names.nextElement();
				String value = prop.getProperty(key);
				
				System.out.println(key + " >\t" + value);
			}
			System.out.println("───── print success ─────");
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
