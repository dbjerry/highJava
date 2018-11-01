package zip.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import zip.dao.ZipSearchDao;
import zip.dao.ZipSearchDaoInf;
import zip.vo.ZipVO;

/*
 * RMI서버용 객체
 */
public class ZipSearchService extends UnicastRemoteObject implements ZipSearchServiceInf {
	private ZipSearchDaoInf dao;
	
	// 생성자
	public ZipSearchService() throws RemoteException {
		dao = ZipSearchDao.getInstance();
	}

	// DB에서 검색한 결과를 클라이언트로 반환하는 메소드
	@Override
	public List<ZipVO> zipSearchDong(String dong) throws RemoteException {
		return dao.zipSearchDong(dong);
	}

	// DB에서 검색한 결과를 클라이언트로 반환하는 메소드
	@Override
	public List<ZipVO> zipSearchZipCode(String zipcode) throws RemoteException {
		return dao.zipSearchZipCode(zipcode);
	}
	
}

