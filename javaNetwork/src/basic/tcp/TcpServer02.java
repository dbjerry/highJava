package basic.tcp;

import java.net.ServerSocket;
import java.net.Socket;

/*
 * 메시지를 받는 클래스와 보내는 클래스를
 * 따로 만들어 작동시키고
 * 이 클래스는 쓰레드로 작동되도록 한다.
 */
public class TcpServer02 {
	public static void main(String[] args) {
		
		// Server Socket을 만들고, Client가 접속해오면
		// Socket을 만들어 메시지를 받는 클래스와 보내는 클래스에
		// 이 Socket을 넘겨준다.
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(9998);
			System.out.println("서버가 준비되었습니다.");
			
			socket = server.accept();
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
