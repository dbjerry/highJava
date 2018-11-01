package basic.tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TcpFileClient_sem {
	private Socket socket;
	private OutputStream os;
	private FileInputStream fis;
	
	public void clientRun() {
		try {
			File file = new File("C:/1_ddit/D_Other/minions.jpg");
			if(!file.exists()) {
				System.out.println("전송할 파일이 없습니다.");
				return;
			}
			
			socket = new Socket("127.0.0.1", 9996);
			System.out.println("파일 전송 시작");
			
			fis = new FileInputStream(file);
			
			os = socket.getOutputStream();
			
			byte[] tmp = new byte[1024];
			int length = 0;
			
			while((length = fis.read(tmp)) != -1) {	//	파일 읽기
				os.write(tmp, 0, length);			//	소켓 전송
			}
			System.out.println("파일 전송 완료");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} finally {
			if(fis != null) try { fis.close(); } catch (IOException e) {}
			if(os != null) try { os.close(); } catch (IOException e) {}
			if(socket != null) try { socket.close(); } catch (IOException e) {}
		}
	}
	
	public static void main(String[] args) {
		new TcpFileClient_sem().clientRun();
	}
}

