package member.service;

import java.util.List;

import member.dao.MemberDaoImpl;
import vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	// DAO를 객체변수로 선언
	private MemberDaoImpl memDao;
	
	// 싱글톤 적용
	private static MemberServiceImpl service;
	
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if (service == null) {
			service = new MemberServiceImpl();
		}
		
		return service;
	}
	
	@Override
	public List<MemberVO> selectMemAll() {
		return memDao.selectMemAll();
	}

	@Override
	public int insertMem(MemberVO mv) {
		return memDao.insertMem(mv);
	}

	@Override
	public int updateMem(MemberVO mv) {
		return memDao.updateMem(mv);
	}

	@Override
	public int deleteMem(String mem_id) {
		return memDao.deleteMem(mem_id);
	}

	@Override
	public boolean checkMem(String mem_id) {
		return memDao.checkMem(mem_id);
	}

	@Override
	public List<MemberVO> selectMem(MemberVO mv) {
		return memDao.selectMem(mv);
	}

}
