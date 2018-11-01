package basic.tcp;
/*
 * 소켓을 통해서 메시지를 보내는 역할을 담당한다.
 * 자바의정석 p.970
 */

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread{
	Socket socket;
	DataOutputStream dos;
	String name;
	
	// 생성자는 외부에서 Socket객체를 받아서 멤버변수에 세팅하고
	// 이름과 OutputStream객체를 구성한다.
	public Sender(Socket socket) {
		this.socket = socket;
		name = "[" + socket.getInetAddress() + ":"
			 + socket.getPort() + "]";
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(dos != null) {
			try {
				dos.writeUTF(name + " " + scan.nextLine());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

