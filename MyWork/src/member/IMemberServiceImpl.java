package member;

import java.util.List;

import vo.MemberVO;

public class IMemberServiceImpl implements IMemberService{
	private static IMemberServiceImpl memService;
	private IMemberDaoImpl memDao;
	
	private IMemberServiceImpl(){
		memDao = IMemberDaoImpl.getInstance();
	}
	
	public static IMemberServiceImpl getInstance(){
		if(memService == null){
			memService = new IMemberServiceImpl();
		}
		
		return memService;
	}

	@Override
	public int insertMember(MemberVO mv) {
		return memDao.insertMember(mv);
	}

	@Override
	public int deleteMember(String mem_id) {
		return memDao.deleteMember(mem_id);
	}

	@Override
	public int updateMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return memDao.getAllMember();
	}

	@Override
	public boolean chkMember(String mem_id) {
		return memDao.chkMember(mem_id);
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		return memDao.searchMember(mv);
	}

	@Override
	public MemberVO getMember(String mem_id) {
		return memDao.getMember(mem_id);
	}
	
	
	
	
}
