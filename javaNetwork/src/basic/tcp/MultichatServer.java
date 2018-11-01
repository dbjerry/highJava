package basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultichatServer {
	
	// 대화명, 클라이언트의 소켓을 저장할 Map객체 변수 선언
	private Map<String, Socket> clients;
	
	public MultichatServer() {
		// Map객체를 동기화 처리 및 생성
		clients = Collections.synchronizedMap(new HashMap<>());
	}

	// Server Start method
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(8888);
			System.out.println("서버가 시작되었습니다.");
			System.out.println();
			
			while(true) {
				socket = server.accept();	//	클라이언트의 접속 기다림
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]");
				
				// 서버에서 클라이언트로 메시지를 전송하는 Thread 생성 및 실행
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(server != null) try { server.close(); } catch (IOException e) {}
		}
		
	}

	// Map(대화방)에 저장되어 있는 전체 사용자들에게 메시지를 전달하는 메서드
	public void sendToAll(String msg) {
		// 대화방에 접속한 사용자의 대화명(키값) 추출하기
		Iterator<String> it = clients.keySet().iterator();
		
		while(it.hasNext()) {
			try {
				String name = it.next();  //  키값(대화명) 구하기
				
				// 대화명에 해당하는 소켓을 이용하여 OutputStream객체 구하기
				DataOutputStream out = new DataOutputStream(
					clients.get(name).getOutputStream()
				);
				
				out.writeUTF(msg);
				
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new MultichatServer().serverStart();
	}
	
	
	// 서버에서 클라이언트로 메시지를 전송하는 Thread 클래스 작성
	// Inner클래스로 정의하여 대화방(Map)에 접근할 수 있도록 한다.
	class ServerReceiver extends Thread{
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				// Client socket에서 데이터를 수신받기 위한 InputStream 생성
				in = new DataInputStream(socket.getInputStream());
				
				// Client  Socket으로 데이터를 전송하기 위한 OutputStream 생성
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		@Override
		public void run() {
			String name = "";
			try {
				// 서버에서는 최초에 클라이언트가 보낸 대화명을 받아야 한다.
				name = in.readUTF();
				
				// 대화명을 받아서 전체 클라이언에게 대화방 참여 메시지를 보낸다.
				sendToAll("[" + name + "]님이 입장했습니다.");
				
				// 대화방(Map)에 저장한다.
				clients.put(name, socket);
				System.out.println("현재 접속자수 : " + clients.size() + "명");
				
				// 클라이언트가 보내온 메시지를 전체 클라이언트에게 보낸다.
				while(in != null) {
					sendToAll(in.readUTF());
				}
				
			} catch (IOException e) {
				// TODO: handle exception
			} finally {
				// finally절이 실행된다는 것은 클라이언트가 빠져나갔다는 것을 의미한다.
				sendToAll("[" + name + "]님이 나갔습니다.");
				
				// 대화방(Map)에서 삭제
				clients.remove(name);
				
				// 현재 접속자수
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]");
				System.out.println("현재 접속자수 : " + clients.size() + "명");
				
			}
		}
		
	}
	
}
