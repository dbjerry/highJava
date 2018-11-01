package jumpstart;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

public class Certificate {
			
	public static final String DEST = "results/chapter01/Certificate2.pdf";
    public static final String IMAGE = "src/main/resources/img/ddit.jpg";
	public static final String FONT = "src/main/resources/fonts/NanumBarunGothic.ttf";
    
	public static final String TITLE = "수   료   증\n";
	public static final String NAME = "이   름 : ";
	public static final String PROGRAM = "과   정 : ";
	public static final String CERDATE = "수료일 : ";
	public static final String COMMENT = "위 학생은 본 과정(1264시간/158일)을\n성실히 수행하여 마쳤고 지식산업에 이바지 할 수 있는 수준에 도달하였기에 이 증서를 수여함.";
	public static final String ACADEMY = "대 덕 인 재 개 발 원";
	
	
    public static void main(String[] args) throws Exception {
    	
    	File file= new File(DEST);
		file.getParentFile().mkdirs();
		new Certificate().makePdf(DEST);
	}

	protected void makePdf(String dest) throws Exception {
    	Database db = new Database();
		
    	String name = JOptionPane.showInputDialog("출력할 이름을 입력해주세요.");
		String stuName = db.getStudentName(name);
		String stuProg = db.getStudentProgram(name);
		String stuDate = db.getStudentDate(name);
    	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String date = sdf.format(new Date());
		
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));

		// Note that it is not necessary to create new PageSize object,
        // but for testing reasons (connected to parallelization) we call constructor here
//		PageSize pageSize = new PageSize(PageSize.A4).rotate();
		PageSize pageSize = new PageSize(PageSize.A4);
		Document doc = new Document(pdfDoc, pageSize);
		
		
		PdfFont freeUnicode = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H, true);
		doc.setFont(freeUnicode);
		
		PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
		ImageData image = ImageDataFactory.create(IMAGE);
		
		canvas.saveState();
		
		PdfExtGState state = new PdfExtGState();
		state.setFillOpacity(0.3f);
		canvas.setExtGState(state);
		canvas.addImage(image, 85, 400, (float)((pageSize.getWidth())/1.5), false);
		canvas.restoreState();
		
		doc.add(new Paragraph(TITLE).setFontSize(50).setTextAlignment(TextAlignment.CENTER));
		doc.add(new Paragraph("\n").setFontSize(30).setTextAlignment(TextAlignment.CENTER));
		doc.add(new Paragraph(NAME + stuName).setFontSize(23).setTextAlignment(TextAlignment.LEFT));
		doc.add(new Paragraph(PROGRAM + stuProg).setFontSize(23).setTextAlignment(TextAlignment.LEFT));
		doc.add(new Paragraph(CERDATE + stuDate + "\n").setFontSize(23).setTextAlignment(TextAlignment.LEFT));
		doc.add(new Paragraph("\n").setFontSize(20).setTextAlignment(TextAlignment.CENTER));
		doc.add(new Paragraph(COMMENT).setFontSize(27).setTextAlignment(TextAlignment.CENTER));
		doc.add(new Paragraph("\n").setFontSize(30).setTextAlignment(TextAlignment.CENTER));
		doc.add(new Paragraph(date).setFontSize(25).setTextAlignment(TextAlignment.CENTER));
		doc.add(new Paragraph(ACADEMY).setFontSize(25).setTextAlignment(TextAlignment.CENTER));
		doc.close();
		
	}
}


class Database{
	private List<StudentVO> studentList = new ArrayList<StudentVO>();
	
	{
	StudentVO student1 = new StudentVO();
	student1.setClassNum(203);
	student1.setName("김종훈");
	student1.setProgram("[NCS] 응용SW엔지니어링 실무과정(4기)");
	student1.setDate("2019년 01월 18일");
	studentList.add(student1);

	StudentVO student2 = new StudentVO();
	student2.setClassNum(203);
	student2.setName("김지태");
	student2.setProgram("[NCS] 응용SW엔지니어링 실무과정 (4기)");
	student2.setDate("2019년 01월 18일");
	studentList.add(student2);

	StudentVO student3 = new StudentVO();
	student3.setClassNum(203);
	student3.setName("김효민");
	student3.setProgram("[NCS] 응용SW엔지니어링 실무과정 (4기)");
	student3.setDate("2019년 01월 18일");
	studentList.add(student3);
	
	StudentVO student4 = new StudentVO();
	student4.setClassNum(203);
	student4.setName("박진");
	student4.setProgram("[NCS] 응용SW엔지니어링 실무과정 (4기)");
	student4.setDate("2019년 01월 18일");
	studentList.add(student4);
	
	StudentVO student5 = new StudentVO();
	student5.setClassNum(204);
	student5.setName("이영만");
	student5.setProgram("[NCS] 응용SW엔지니어링 실무과정 (3기)");
	student5.setDate("2018년 12월 14일");
	studentList.add(student5);
	}
	
	public String getStudentName(String name){
		
		for(StudentVO student : studentList){
			if(student.getName().equals(name)){
				return student.getName();
			}else{
				continue;
			}
		}
		return null;
	}
	
	public String getStudentDate(String name){
		
		for(StudentVO student : studentList){
			if(student.getName().equals(name)){
				return student.getDate();
			}else{
				continue;
			}
		}
		return null;
	}
	
	public String getStudentProgram(String name){
		
		for(StudentVO student : studentList){
			if(student.getName().equals(name)){
				return student.getProgram();
			}else{
				continue;
			}
		}
		return null;
	}
}


class StudentVO{
	
	private Integer classNum;
	private String name;
	private String program;
	private String date;
	
	public StudentVO() {}

	public int getClassNum() {
		return classNum;
	}
	
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProgram() {
		return program;
	}
	
	public void setProgram(String program) {
		this.program = program;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
}
