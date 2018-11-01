package chat.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import chat.inf.ChatServerInf;

public class ChatServerMain {
	public static void main(String[] args) {
		ChatServerInf server;
		
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
			server = new ChatServer();
			reg.rebind("chat", server);
			System.out.println("서버가 준비되어있습니다.");
		} catch (RemoteException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
}
