package socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {
	public static void main(String... args) {
		
		// auto close
		try (ServerSocket server = new ServerSocket()){
			
			// Server reset
			InetSocketAddress ipep = new InetSocketAddress(9999);
			server.bind(ipep);
			
			System.out.println("Initialize complate");
			
			
			// listen wait
			Socket client = server.accept();	//	연결요청이 올 때까지 실행을 멈추고 계속 기다린다.
			System.out.println("Connection");
			
			
			// sent, receiver get Stream(스트림 받아오기)
			// auto close
			try(OutputStream sender = client.getOutputStream();
					InputStream receiver = client.getInputStream();){
				// 클라이언트로 hello world 메시지 보내기
				// 11byte data
				String message = "Hello World";
				byte[] data = message.getBytes();
				sender.write(data, 0, data.length);
				
				// 클라이언트로부터 메시지 받기
				// 2byte data
				data = new byte[2];
				receiver.read(data, 0, data.length);
				
				// 수신 메시지 출력
				message = new String(data);
				String out = String.format("receive - %s", message);
				System.out.println(out);
				
			}
			
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
}
