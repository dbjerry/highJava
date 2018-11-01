package basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {
	public static void main(String[] args) throws Throwable{
		String serverIP = "192.168.203.67";
		
		System.out.println(serverIP + " 서버에 연결 중입니다.");
		
		// 소켓을 생성해서 서버에 연결 요청을 보낸다.
		Socket socket = new Socket(serverIP, 9999);
		
		// 연결 이후의 내용
		System.out.println("연결되었습니다.");
		System.out.println();
		
		// 서버가 보내온 메시지 출력하기
		
		// 메시지를 받기 위해 InputStream 객체 생성
		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		
		// 받은 메시지 출력하기
		System.out.println("서버에서 온 메시지 : " + dis.readUTF());
		
		System.out.println();
		System.out.println("연결을 종료합니다.");
		
		// socket과 stream객체 닫기
		dis.close();
		socket.close();
		
		
	}
}
