package basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultichatClient {
	
	public void clientStart() {
		Scanner scan = new Scanner(System.in);
		String userName = null;
		
		System.out.print("대화명 입력 : ");
		userName = scan.next();
		
		try {
			String ServerIp = "localhost";
			Socket socket = new Socket("192.168.203.19", 7777);
			System.out.println("서버에 연결되었습니다.");
			
			// 메시지 전송용 객체 생성
			Thread sender = new ClientSender(socket, userName);
			
			// 메시지 수신용 객체 생성
			Thread receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	} // clientStart() 끝
	
	
	public static void main(String[] args) {
		new MultichatClient().clientStart();
	}
	
	
	/*
	 * 메시지 전송용 Thread
	 */
	class ClientSender extends Thread {
		Socket socket;
		DataOutputStream out;
		String name;
		Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket, String name) {
			this.socket = socket;
			this.name = name;
			
			try {
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {}
		}
		
		@Override
		public void run() {
			try {
				// 처음 시작하자마자 자신의 이름을 서버로 전송한다.
				if(out != null) {
					out.writeUTF(name);
				}

				while(out != null) {
					// 키보드로 입력한 내용을 서버로 전송한다.
					out.writeUTF(scan.nextLine());
				}
				
				
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
	} // ClientSender 끝
	
	
	/*
	 * 메시지 수신용 Thread
	 */
	class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		
		// 생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				// 서버로부터 메시지를 받을 수 있는 InputStream객체 생성
				in = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {}
		}
		
		@Override
		public void run() {
			while(in != null) {
				try {
					// 서버로부터 전송되어 온 메시지를 받아서 출력
					System.out.println(in.readUTF());
				} catch (Exception e) {}
			}
		}
		
	}
	
}

