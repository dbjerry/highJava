package basic;

import java.net.MalformedURLException;
import java.net.URL;

/*
 * URL 클래스	==>	인터넷에 존재하는 서버들의 자원에
 * 				접근할 수 있는 주소를 관리하는 클래스
 */
public class URLTest {
	public static void main(String[] args) {
		try {
//			URL url = new URL("http://www.ddit.or.kr:80/index.html?ttt=123");
			URL url = new URL("http", "www.ddit.or.kr", 80, "index.html?ttt=123");
			
			System.out.println("protocol : " + url.getProtocol());
			System.out.println("host : " + url.getHost());
			System.out.println("port : " + url.getPort());
			System.out.println("file : " + url.getFile());
			System.out.println("query : " + url.getQuery());
			System.out.println("path : " + url.getPath());
			
			System.out.println(url.toExternalForm());	//	링크 중간에 들어가야 할 슬래쉬(/)나 콜론(:)이 빠져있다.
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
