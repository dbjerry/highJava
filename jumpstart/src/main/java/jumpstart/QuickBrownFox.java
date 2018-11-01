/*
 * This example is part of the iText 7 tutorial.
 */
package jumpstart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

/**
 * Simple image example.
 */
public class QuickBrownFox {
	
	public static final String DOG = "src/main/resources/img/dog.jpg";
	public static final String FOX = "src/main/resources/img/fox.jpg";
	public static final String MINIONS = "src/main/resources/img/images.jpg";
	
	public static final String DEST = "results/chapter01/quick_brown_fox_minions.pdf";
	
	public static void main(String[] args) throws IOException {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new QuickBrownFox().createPdf(DEST);
		
	}
	
	public void createPdf(String dest) throws IOException{
		
		//Initialize PDF writer
		PdfWriter writer = new PdfWriter(dest);
		
		//Initial PDF document
		PdfDocument pdf = new PdfDocument(writer);
		
		//Initialize document
		Document document = new Document(pdf);
		
		//Compose paragraph
		Image fox = new Image(ImageDataFactory.create(FOX));
		Image dog = new Image(ImageDataFactory.create(DOG));
		Image minions = new Image(ImageDataFactory.create(MINIONS));
		Paragraph p = new Paragraph("The quick brown")
				.add(fox)
				.add("jumps over the lazy")
				.add(dog)
				.add(minions);
		
		//Add Paragraph to document
		document.add(p);
		
		//Close document
		document.close();
		
	}
}
