package member.vo;

public class MemberVO {
	
	/* DB테이블의 컬럼명을 멤버 변수로 지정 */
	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private int mem_age;
	private String mem_phone;
	
	/* 기본생성자 */
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* 생성자 */
	public MemberVO(String mem_id, String mem_pw, String mem_name, int mem_age, boolean mem_sex, String mem_phone) {
		super();
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.mem_age = mem_age;
		this.mem_phone = mem_phone;
	}

	
	/* 각 변수들의 getter, setter */
	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public int getMem_age() {
		return mem_age;
	}

	public void setMem_age(int mem_age) {
		this.mem_age = mem_age;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	
}

