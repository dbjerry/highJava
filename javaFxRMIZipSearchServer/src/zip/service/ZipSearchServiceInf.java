package zip.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import zip.vo.ZipVO;

/*
 * MVC패턴의 Service인터페이스는
 * RMI의 서버에 등록할 Interface로 만든다.
 */
public interface ZipSearchServiceInf extends Remote {
	public List<ZipVO> zipSearchDong(String dong) throws RemoteException;
	
	public List<ZipVO> zipSearchZipCode(String zipcode) throws RemoteException;
}

