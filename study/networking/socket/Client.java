package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	public static void main(String... args) {
		
		// auto close
		try(Socket client = new Socket()){
			
			// Client reset
			InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", 9999);
			
			// Connect
			client.connect(ipep);
			
			// send, receiver get Stream(스트림 받아오기)
			// auto close
			try(OutputStream sender = client.getOutputStream();
					InputStream receiver = client.getInputStream();){
				
				// receiving data from the server
				// (서버로부터 데이터 받기)
				// 11byte
				byte[] data = new byte[11];
				receiver.read(data, 0, 11);
				
				// 수신메시지 출력
				String message = new String(data);
				String out = String.format("receiver - %s", message);
				System.out.println(out);
				
				// 서버로 데이터 보내기
				// 2byte
				message = "OK";
				data = message.getBytes();
				sender.write(data, 0, data.length);
				
			}
			
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
}

