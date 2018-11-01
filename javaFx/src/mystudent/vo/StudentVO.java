package mystudent.vo;

public class StudentVO {
	
	/* DB테이블의 컬럼명을 멤버 변수로 지정 */
	private String std_name;
	private int std_kor;
	private int std_mat;
	private int std_eng;
	
	public StudentVO() {}

	public StudentVO(String std_name, int std_kor, int std_mat, int std_eng) {
		super();
		this.std_name = std_name;
		this.std_kor = std_kor;
		this.std_mat = std_mat;
		this.std_eng = std_eng;
	}

	public String getStd_name() {
		return std_name;
	}

	public void setStd_name(String std_name) {
		this.std_name = std_name;
	}

	public int getStd_kor() {
		return std_kor;
	}

	public void setStd_kor(int std_kor) {
		this.std_kor = std_kor;
	}

	public int getStd_mat() {
		return std_mat;
	}

	public void setStd_mat(int std_mat) {
		this.std_mat = std_mat;
	}

	public int getStd_eng() {
		return std_eng;
	}

	public void setStd_eng(int std_eng) {
		this.std_eng = std_eng;
	}

	public String toString() {
		return "StudentVO [std_name=" + std_name + ", std_kor=" + std_kor + ", std_mat=" + std_mat + ", std_eng="
				+ std_eng + "]";
	}

}