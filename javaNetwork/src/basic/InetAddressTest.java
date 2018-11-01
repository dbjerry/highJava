package basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * InetAddress 클래스	==>	IP주소를 다루기 위한 클래스
 */
public class InetAddressTest {
	public static void main(String[] args) {
		
		// www.naver.com 사이트의 ip정보 가져오기
		try {
			InetAddress naverIp = InetAddress.getByName("ddit.or.kr");
			System.out.println("Host Name : " + naverIp.getHostName());
			System.out.println("Host Address : " + naverIp.getHostAddress());
			System.out.println();
			
			// 자신의 컴퓨터 IP주소 가져오기
			InetAddress localIp = InetAddress.getLocalHost();
			System.out.println("내 컴퓨터의 Host Name : " + localIp.getHostName());
			System.out.println("내 컴퓨터의 Host Address : " + localIp.getHostAddress());
			System.out.println();
			
			//  IP주소가 여러개인 호스트의 정보 가져오기
			InetAddress[] navers = InetAddress.getAllByName("ddit.or.kr");
			
			for (InetAddress nip : navers) {
				System.out.println(nip.toString());
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}









