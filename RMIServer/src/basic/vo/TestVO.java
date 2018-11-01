package basic.vo;
/*
	RMI���� ������ ���޿����� ����� ��ü��
	��Ʈ��ũ�� ���ؼ� ���޵Ǿ�� �ϱ� ������ ����ȭ�� �ʿ��ϴ�.
	�׷��� Serializable�� ������ ���·� �����.
*/

import java.io.Serializable;

public class TestVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String testId;
	private int testNum;
	
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public int getTestNum() {
		return testNum;
	}
	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}
	
	
}
