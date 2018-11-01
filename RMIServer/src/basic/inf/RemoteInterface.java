package basic.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import basic.vo.FileInfoVO;
import basic.vo.TestVO;

// RMI�� �������̽��� Remote�� ����ؾ� �Ѵ�.
public interface RemoteInterface extends Remote {
	// �� �������̽��� ����Ǵ� ��� �޼���� RemoteException��
	// throws�ؾ� �Ѵ�.
	
	public int doRmotePrint(String str) throws RemoteException;
	
	public void doPrintList(ArrayList<String> list) throws RemoteException;
	
	public void doPrintVo(TestVO vo) throws RemoteException;
	
	// ���� ���ۿ� �޼���
	public void setFile(FileInfoVO fileVo) throws RemoteException;
}
