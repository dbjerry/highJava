package basic.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import basic.vo.FileInfoVO;
import basic.vo.TestVO;

// RMI용 인터페이스는 Remote를 상속해야 한다.
public interface RemoteInterface extends Remote {
	// 이 인터페이스에 선언되는 모든 메서드는 RemoteException을
	// throws해야 한다.
	
	public int doRmotePrint(String str) throws RemoteException;
	
	public void doPrintList(ArrayList<String> list) throws RemoteException;
	
	public void doPrintVo(TestVO vo) throws RemoteException;
	
	// 파일 전송용 메서드
	public void setFile(FileInfoVO fileVo) throws RemoteException;
}
