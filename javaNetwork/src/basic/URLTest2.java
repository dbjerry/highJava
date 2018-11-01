package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLTest2 {
	/*
	 * 특정 서버의 정보와 파일 내용 출력하기
	 */
	public static void main(String[] args) throws IOException {
		URL url = new URL("https://www.naver.com/index.html");
	
		/*
		 * header 정보 가져오기
		 */
		// URLConnection 객체
		URLConnection urlCon = url.openConnection();
		
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		// key값 구하기
		Iterator<String> it = headerMap.keySet().iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("\n");
		
		/**
		 * 방법 1	==>	URLConnection의 getInputStream() 이용하기
		 */
		
//		// 파일을 읽어오기 위한 스트림 객체 구하기
//		InputStream is = urlCon.getInputStream();	//	<--	바이트 기반으로 객체를 구해옴.
//		InputStreamReader isr = new InputStreamReader(is, "UTF-8");	//	<--	문자 기반으로 구해옴.
//		BufferedReader br = new BufferedReader(isr);
//		
//		// 내용을 읽어와 출력하기
//		while(true) {
//			String str = br.readLine();	//	한줄씩 읽기
//			if(str == null) {
//				break;
//			}
//			System.out.println(str);
//		}
//		br.close();
		
		
		/**
		 * 방법2	==>	URL객체의 openStream() 이용하기
		 */
		// 파일을 읽어오기 위한 스트림 객체 구하기
		InputStream is = url.openStream();	//	바이트 기반으로 객체를 구해옴.
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");	//	<--	문자 기반으로 구해옴.
		BufferedReader br = new BufferedReader(isr);
		
		// 내용을 읽어와 출력하기
		while(true) {
			String str = br.readLine();	//	한줄씩 읽기
			if(str == null) {
				break;
			}
			System.out.println(str);
		}
		br.close();
	}
}

