package chat.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import chat.inf.ChatMessageInf;
import chat.inf.ChatServerInf;

public class ChatClientMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 입력 : ");
		String name = scan.next();
		scan.nextLine();
		
		
		try {
			// 클라이언트용 객체 생성
			ChatMessageInf client = new ChatClient();
			Registry reg = LocateRegistry.getRegistry("localhost", 1099);
			ChatServerInf server = (ChatServerInf)reg.lookup("chat");
			
			// 서버에 클라이어트 객체 등록
			server.setClient(client);
			
			String msg = "";
			do {
				msg = scan.nextLine();
				server.setMessage("[" + name + "] " + msg);
			} while(true);
			
		} catch (RemoteException e) {
			// TODO: handle exception
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
