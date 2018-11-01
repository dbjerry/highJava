package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class PdfTest {
	public static void main(String[] args) {
		//문서를 만들고 페이지를 추가하는 작업
		PDDocument dc = new PDDocument();
		PDPage page = new PDPage();
		dc.addPage(page);
		
		//폰트를 설정
		try {
			InputStream fontStream = new FileInputStream("c:/Windows/Fonts/malgun.TTF");
			PDType0Font font = PDType0Font.load(dc, fontStream);
			
			// 시작을 위한 스트림 생성
			PDPageContentStream contentStream = new PDPageContentStream(dc, page);
			
			contentStream.beginText();
			contentStream.setFont(font, 14);
			contentStream.newLineAtOffset(100, 750);
			contentStream.setLeading(15f);
			contentStream.showText("테스트 테스트 테스트");
			contentStream.newLine();
			contentStream.showText("줄바꿈 테스트");
			contentStream.endText();
			contentStream.close();
			
			dc.save("test.pdf");
			dc.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}
