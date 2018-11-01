package zip.dao;

import java.util.List;

import zip.vo.ZipVO;

public interface ZipSearchDaoInf {
	public List<ZipVO> zipSearchDong(String dong);
	
	public List<ZipVO> zipSearchZipCode(String zipcode);
}
