package basic.vo;

import java.io.Serializable;

public class FileInfoVO implements Serializable{

	// 파일 전송용 VO파일

	private static final long serialVersionUID = 4789120936503781681L;
	
	private String fileName;  // 파일 이름
	private byte[] fileData;  // 파일 내용
	
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
