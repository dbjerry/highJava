package member;

import java.util.List;

import vo.MemberVO;

public interface IMemberService {
	public int insertMember(MemberVO mv);
	
	public int deleteMember(String mem_id);
	
	public int updateMember(MemberVO mv);
	
	public List<MemberVO> getAllMember();
	
	public boolean chkMember(String mem_id);
	
	public MemberVO getMember(String mem_id);
	
	public List<MemberVO> searchMember(MemberVO mv);
}
