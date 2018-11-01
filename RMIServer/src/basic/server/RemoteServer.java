package basic.server;
/*
	RMI���� �޼��带 �����ϴ� ��ü�� UnicastRemoteObject�� ����ϰ�
	�ۼ��� ���� �������̽��� �����Ͽ� �ۼ��Ѵ�.
*/

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import basic.inf.RemoteInterface;
import basic.vo.FileInfoVO;
import basic.vo.TestVO;

public class RemoteServer extends UnicastRemoteObject implements RemoteInterface {

	// �����ڵ� RemoteException�� throws�ؾ� �Ѵ�.
	public RemoteServer() throws RemoteException {}

	public static void main(String[] args) {
		try {
			// Ŭ���̾�Ʈ�� RMI����� ����Ͽ� �� ��ü���� �����ϴ�
			// �޼��带 ����� �� �ִ� ȯ�� �����ϱ�
			
			// 1) �������̽��� ������ ��ü ����
			RemoteInterface inf = new RemoteServer();
			
			// 2) Ŭ���̾�Ʈ�� ������ ��ü�� ã�� �� �ֵ��� �ϴ�
			//    Registry ��ü�� �����Ѵ�. (��Ʈ��ȣ ����)
			//    ��Ʈ��ȣ�� �����ϸ� �⺻���� 1099�� �ȴ�.
			Registry reg = LocateRegistry.createRegistry(8888);
			
			// 3) ������ Registry�� ���񽺸� ������ ��ü�� ����Ѵ�.
			//   ����) rebind("��ü��Alias", ��ü����)
			//        ��ü�� Alias�� Ŭ���̾�Ʈ���� ã�� �̸��� �ȴ�.
			reg.rebind("server", inf); 
			
			System.out.println("������ �غ�Ǿ����ϴ�.");
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		

	}

	// �޼���� �����ϱ�
	
	// �޼����� �Ű����� ������ Ŭ���̾�Ʈ���� �������� �����Ͱ�
	// ��ȯ���� �������� �޼��带 ó���� ����� Ŭ���̾�Ʈ��
	// ������ ���� �ȴ�.
	@Override
	public int doRmotePrint(String str) throws RemoteException {
		System.out.println("Ŭ���̾�Ʈ���� ������ ���� : " + str);
		System.out.println("doRemotePrint()�޼��带 ��Ĩ�ϴ�.");
		
		return 100;
	}

	@Override
	public void doPrintList(ArrayList<String> list) throws RemoteException {
		System.out.println("List�� ��� ����...");
		for(String str : list) {
			System.out.println(str);
		}
		System.out.println("List�� ��� ��...");
		
	}

	@Override
	public void doPrintVo(TestVO vo) throws RemoteException {
		System.out.println("TestVO�� �� ���");
		System.out.println("ID : " + vo.getTestId());
		System.out.println("Num : " + vo.getTestNum());
		System.out.println("TestVO ��� ��...");
		
	}
	
	
	// Ŭ���̾�Ʈ�� ������ ���� ������ �̿��Ͽ�
	// �����ʿ� �� ������ �����ϴ� �޼���
	@Override
	public void setFile(FileInfoVO fileVo) throws RemoteException {
		FileOutputStream fout = null;
		String dir = "d:/D_Other/temp/";  // ������ ���� ����
		
		try {
			// ������ ������ ���ϸ��� �����Ͽ� FileOutputStream��ü ����
			fout = new FileOutputStream(dir + fileVo.getFileName());
			
			// Ŭ���̾�Ʈ���� ������ ���ϵ�����(byte[])��
			// �����Ѵ�.
			fout.write(fileVo.getFileData());
			
			fout.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("���� ���� �Ϸ�...");
		
	}

}









