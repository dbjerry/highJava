package basic.tcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer_sem {
	private ServerSocket server;
	private Socket socket;
	private InputStream is;
	private FileOutputStream fos;
	
	
	public void serverRun() {
		try {
			File file = new File("C:/1_ddit/D_Other/network/minions2.jpg");
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			server = new ServerSocket(9996);
			System.out.println("서버가 준비되었습니다.");
			
			socket = server.accept();
			System.out.println("파일 다운로드 시작");
			
			is = socket.getInputStream();
			fos = new FileOutputStream(file);
			
			byte[] tmp = new byte[1024];
			int length = 0;
			
			while((length = is.read(tmp)) != -1) {	//	소켓으로 전송받기
				fos.write(tmp, 0, length);	//	파일로 저장하기
			}
			System.out.println("파일 다운로드 완료");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos != null) try { fos.close(); } catch (IOException e) {}
			if(is != null) try { is.close(); } catch (IOException e) {}
			if(socket != null) try { socket.close(); } catch (IOException e) {}
			if(fos != null) try { fos.close(); } catch (IOException e) {}
		}
	}
	
	public static void main(String[] args) {
		new TcpFileServer_sem().serverRun();
	}
}

