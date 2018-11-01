package basic.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import basic.inf.RemoteInterface;
import basic.vo.FileInfoVO;
import basic.vo.TestVO;

/*
	Ŭ���̾�Ʈ���� ������Ʈ���� Interface�� �ִ� ��Ű�� ������
	VO��ü�� �ִ� ��Ű�� ������ �Ȱ��� �־�� �Ѵ�.
*/
public class RemoteClient {

	public static void main(String[] args) {
		try {
			// �������� �����ϴ� ���񽺸� ã�� ���� Registry��ü ����
			// ������ IP�ּҿ� ��Ʈ��ȣ�� �����Ѵ�.
			Registry reg = LocateRegistry.getRegistry("localhost", 8888);
			
			// �����ʿ��� ����� '��ü��Alias'�� �̿��Ͽ�
			// ����� ��ü�� ���Ѵ�.
			RemoteInterface inf = (RemoteInterface) reg.lookup("server");
			
			
			// �������ʹ� �ҷ��� ��ü�� �޼��带 ȣ���ؼ� ����� �� �ִ�.
			int a = inf.doRmotePrint("�ȳ��ϼ���");
			System.out.println("��ȯ�� : " + a);
			System.out.println("------------------------");
			
			
			ArrayList<String> list = new ArrayList<>();
			
			list.add("������");
			list.add("����");
			list.add("����");
			list.add("����");
			inf.doPrintList(list);
			System.out.println("doPrintList()�޼��� ȣ�� ��.");
			
			TestVO vo = new TestVO();
			vo.setTestId("ȫ�浿");
			vo.setTestNum(1234);
			inf.doPrintVo(vo);
			System.out.println("doPrintVo()�޼��� ȣ�� ��...");
			
			// ���� ���� ���� ���� ----------
			
			// ������ ���� ������ ���� File��ü ����
			File file = new File("d:/D_Other/��ȭ.jpg");
			
			if(!file.exists()) {
				System.out.println("������ ������ �����ϴ�.");
				return;
			}
			
			// FileInfoVO��ü�� �����Ͽ� �����Ѵ�.
			int len = (int)file.length();  // ���� ũ��
			FileInputStream fin = new FileInputStream(file);
			byte[] data = new byte[len];
			
			fin.read(data);  // ���� ������ �о byte�迭�� ����
			
			// FileInfoVO��ü�� �����ؼ� ������ �����Ѵ�.
			FileInfoVO fVo = new FileInfoVO();
			
			fVo.setFileName(file.getName());
			fVo.setFileData(data);
			
			// ������ ������ ������ �޼��带 ȣ���Ѵ�.
			inf.setFile(fVo);
			
			System.out.println("���� ���� ��...");
			// ���� ���� ���� ��...
		
			
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
