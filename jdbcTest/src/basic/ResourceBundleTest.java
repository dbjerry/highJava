package basic;
/**
 * ResourceBundle 객체를 이용하여 파일 읽어오기
 */
import java.util.Enumeration;
import java.util.ResourceBundle;

public class ResourceBundleTest {
	/**
	 * ResourceBundle 객체	ㅡ>	확장자가 properties인 파일 정보를
	 * 							읽어와 key와 value를 분리해 정보를 갖는 객체
	 * 
	 * 			ㅡ>	읽어올 파일은 'key = value' 형태로 되어 있어야 한다.
	 */
	public static void main(String[] args) {
		/*
		 * ResourceBundle 객체 생성
		 * 	ㅡ>	파일을 지정할 때 '파일명'만 지정하고
		 * 		확장자는 지정하지 않는다.
		 * 		(이유: 확장자는 항상 properties이기 때문)
		 */
		ResourceBundle bundle = ResourceBundle.getBundle("db");
		
		/* key만 읽어오기 */
		Enumeration<String> keys = bundle.getKeys();
		
		/* key 갯수만큼 반복 */
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			
			/*
			 * key를 이용해서 value를 읽어오는 방법
			 * bundle.getString("key");
			 */
			String value = bundle.getString(key);
			
			System.out.println(key + " >\t" + value);
		}
		System.out.println("───── print success ─────");
	}
}
