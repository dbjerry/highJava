package basic.tcp;
/*
 * 이 클래스는 소켓에서 메시지를 받아
 * 화면에 출려하는 일을 담당한다.
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread{
	Socket socket;
	DataInputStream dis;

	// 생성자에서 Socket을 세팅하고 InputStream객체를 구성한다.
	public Receiver(Socket socket) {
		this.socket = socket;
		
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		try {
			while(dis != null) {
				System.out.println(dis.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

