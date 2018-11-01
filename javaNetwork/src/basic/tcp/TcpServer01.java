package basic.tcp;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {
	public static void main(String[] args) {
		try {
			// TCP소켓 통신을 위해 ServerSocket객체 생성
			ServerSocket server = new ServerSocket(9999);
			System.out.println("접속을 기다립니다..");
			
			/*
			 * accept()는 Client의 연결 요청이 올 때까지
			 * 계속 기다린다.
			 * 연결 요청이 오면 Socket객체를 생성해서
			 * Client의 Socket과 연결한다.
			 */
			Socket socket = server.accept();
			
			System.out.println(socket.getInetAddress() + "에 연결되었습니다.");
			System.out.println("port : " + socket.getPort());
			System.out.println("localPort : " + socket.getLocalPort());
			
			// 접속된 Client에게 메시지 보내기
			// Socket의 outputStream 객체를 구성하여 전송한다.
			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			dos.writeUTF("환영합니다.");	//	메시지 보내기
			System.out.println("메시지를 보냈습니다.");
			
			dos.close();
			
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}
}
