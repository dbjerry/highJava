package basic.vo;

import java.io.Serializable;

public class FileInfoVO implements Serializable{

	// ���� ���ۿ� VO����

	private static final long serialVersionUID = 4789120936503781681L;
	
	private String fileName;  // ���� �̸�
	private byte[] fileData;  // ���� ����
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
	
}
