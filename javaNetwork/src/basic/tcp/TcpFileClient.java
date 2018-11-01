package basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TcpFileClient {
	public static void main(String[] args) {
		/*
		 * 내 컴퓨터 나타내는 방법
		 * 1. IP주소
		 * 2. 127.0.0.1
		 * 3) localhost
		 */
		FileInputStream fin = null;
		BufferedInputStream bis = null;
		DataOutputStream dos;
		OutputStream os;
		try {
			Socket socket = new Socket("127.0.0.1", 9997);
			System.out.println("Server Connect OK..");
			
			fin = new FileInputStream("C:/1_ddit/D_Other/minions.jpg");
			bis = new BufferedInputStream(fin);
			byte[] tmp = new byte[3000];
			int file;
			
			os = socket.getOutputStream();
			
			while((file = bis.read(tmp)) != -1) {
				os.write(tmp, 0, file);
				
			}
			System.out.println("파일 전송 완료");
			
			bis.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
