package basic;

import java.util.Enumeration;
import java.util.Properties;

public class PropertiesTest01 {
	/**
	 * Properties는 Map과 같은 컬렉션인데
	 * Properties는 key값과 value값 모두 *문자열만 가능
	 * 
	 * 값 저장하기 : setProperty("key", "value");
	 * 값 읽어오기 : getProperty("key");
	 * 
	 * key만 가져오기 : propertyNames();
	 */
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		prop.setProperty("이름", "홍길동");
		prop.setProperty("나이", "20");
		prop.setProperty("주소", "대전");
		
//		System.out.println("이름 : " + prop.getProperty("이름"));
		
		/* key 정보들을 Enumeration객체로 받는다. */
		Enumeration<String> names = (Enumeration<String>)prop.propertyNames();
		
		/* key 갯수만큼 반복해서 처리 */
		while(names.hasMoreElements()){			// 다음 자료가 있으면 true.
			String key = names.nextElement();	// 다음 자료를 가져온다.
			String value = prop.getProperty(key);
			
			System.out.println(key + " >\t" + value);
		}
		System.out.println("\n───── print success ─────");
		
	}
	
}
