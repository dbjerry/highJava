package mvc.dao;

import java.util.List;
import java.util.Map;

import mvc.vo.MemberVO;

public interface IMemberDao {
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param memVO insert할 자료가 저장된 MemberVO객체
	 * @return	DB작업이 성공하면 1이상의 정수값이 반환되고
	 * 			실패면 0이 반환된다.
	 */
	public Object insertMember(MemberVO memberVo);

	/**
	 * 회원 ID값을 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 ID값이 저장된 String변수
	 * @return 작업 성공: 1, 실패: 0
	 */
	public int deleteMember(String memId);

	/**
	 * 하나의 MemberVO자료를 이용하여 DB를 update하는 메서드
	 * @param memVO update할 회원정보가 들어있는 MemberVO객체
	 * @return 작업 성공: 1, 작업 실패: 0
	 */
	public int updateMember(MemberVO memVO);
	
	/**
	 * DB의 mymember테이블의 전체 레코드를 가져와서
	 * List에 담아 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> getMemberList();
	
	/**
	 * 주어진 회원 ID가 존재하는 여부를 알아내는 메서드
	 * @param memId 검색할 회원 ID
	 * @return 해당 회원 ID가 있으면 1, 없으면 0
	 */
	public int getMember(String memId);
	
	/**
	 * 주어진 파라미터에 맞게 자료를 검색하는 메서드
	 * @param col 검색할 컬럼명이 저장되는 변수
	 * @param value 검색할 단어가 저장되는 변수
	 * @param op 검색할 연산자가 저장되는 변수
	 * @return 검색결과를 List에 담아서 반환한다.
	 */
	public List<MemberVO> searchMember(String col, String value, String op);
	
	/**
	 * 주어진 파라미터에 맞게 자료를 검색하는 메서드 2
	 * @param param 검색할 컬럼명, 검색할 단어, 연산자를 저장하는 Map형 변수
	 * @return 검색결과를 List에 담아서 반환한다.
	 */
	public List<MemberVO> searchMember(Map<String, String> param);
	
	
}
