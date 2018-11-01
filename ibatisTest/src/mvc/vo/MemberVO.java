package mvc.vo;

public class MemberVO {
	
	/* DB테이블의 컬럼명을 멤버 변수로 지정 */
	private String mem_id;
	private String mem_name;
	private String mem_tel;
	private String mem_addr;
	
	/* 각 변수별 getter, setter */
	public String getMem_id() {		//	ㅡㅡㅡㅡㅡㅡ	mem_id의 getter
		return mem_id;
	}
	
	public void setMem_id(String mem_id) {	//	ㅡ	mem_id의 setter
		this.mem_id = mem_id;
	}
	
	public String getMem_name() {	//	ㅡㅡㅡㅡㅡㅡ	mem_name의 getter
		return mem_name;
	}
	
	public void setMem_name(String mem_name) {	//	mem_name의 setter
		this.mem_name = mem_name;
	}
	
	public String getMem_tel() {	//	ㅡㅡㅡㅡㅡㅡ	mem_tel의 getter
		return mem_tel;
	}
	
	public void setMem_tel(String mem_tel) {	//	mem_tel의 setter
		this.mem_tel = mem_tel;
	}
	
	public String getMem_addr() {	//	ㅡㅡㅡㅡㅡㅡ	mem_addr의 getter
		return mem_addr;
	}
	
	public void setMem_addr(String mem_addr) {	//	mem_addr의 setter
		this.mem_addr = mem_addr;
	}
	
}