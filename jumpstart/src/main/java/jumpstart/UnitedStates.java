/*
 * This example is part of the iText 7 tutorial.
 */
package jumpstart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

/**
 * Simple table example.
 */
public class UnitedStates {
	public static final String DATA = "src/main/resources/data/MemberVO.csv";
	public static final String DEST = "results/chapter01/MemberVO3.pdf";
	
	public static void main(String[] args) throws IOException {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new UnitedStates().createPdf(DEST);
	}
	
	public void createPdf(String dest) throws IOException{
		//Initialize PDF writer
		PdfWriter writer = new PdfWriter(dest);
		
		//Initial PDF document
		PdfDocument pdf = new PdfDocument(writer);
		
		//Initial document
//		Document document = new Document(pdf, PageSize.A4.rotate());
		Document document = new Document(pdf, PageSize.A4);
		document.setMargins(20, 20, 20, 20);
		
		PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
		PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		
//		Table table = new Table(new float[]{4, 3, 6, 7, 3, 4, 3, 3, 1});
		Table table = new Table(new float[]{3, 3, 5, 5, 2, 2});
		table.setWidthPercent(100);
		
		BufferedReader br = new BufferedReader(new FileReader(DATA));
		String line = br.readLine();
		
		process(table, line, bold, true);
		
		for(int i = 1; i < table.getNumberOfRows(); i++){
			table.getCell(i, 4).setFont(font).setTextAlignment(TextAlignment.CENTER);
			table.getCell(i, 5).setTextAlignment(TextAlignment.CENTER);
		}
		
		while((line = br.readLine()) != null){
			process(table, line, font, false);
		}
		br.close();
		document.add(table);
		
		//Close document
		document.close();
	}
	
	public void process(Table table, String line, PdfFont font, boolean isHeader){
		StringTokenizer tokenizer = new StringTokenizer(line, ",");
		while(tokenizer.hasMoreTokens()){
			if(isHeader){
				table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
			} else {
                table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
		}
	}
}
