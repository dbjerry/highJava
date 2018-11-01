package basic.tcp;

import java.net.Socket;

public class TcpClient02 {
	public static void main(String[] args) {
		/*
		 * 내 컴퓨터 나타내는 방법
		 * 1. IP주소
		 * 2. 127.0.0.1
		 * 3) localhost
		 */
		try {
			Socket socket = new Socket("127.0.0.1", 9998);
			System.out.println("서버에 연결되었습니다.");
			
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
