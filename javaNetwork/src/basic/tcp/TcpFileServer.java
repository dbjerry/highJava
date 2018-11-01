package basic.tcp;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	
	
	
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket = null;
		FileWriter fw;
		InputStream is;
		try {
			server = new ServerSocket(9997);
			System.out.println("Server OK..");
			
			socket = server.accept();
			
			fw = new FileWriter("C:/1_ddit/D_Other/network/minions2.jpg");
			is = socket.getInputStream();
			int file;
			
			while((file = is.read()) != -1) {
				fw.write(file);
			}
			System.out.println("파일 저장 완료");
			
			is.close();
			fw.close();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
