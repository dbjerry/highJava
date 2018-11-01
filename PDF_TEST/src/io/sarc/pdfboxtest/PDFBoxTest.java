package io.sarc.pdfboxtest;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFBoxTest {
	public static void main(String[] args) {
		createEmptyDocument();
	}

	private static void createEmptyDocument() {
		try(final PDDocument document = new PDDocument()) {
			final PDPage newDocument = new PDPage();
			document.addPage(newDocument);
			final PDPageContentStream contentStream = new PDPageContentStream(document, newDocument);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.COURIER_BOLD, 12);
			contentStream.newLineAtOffset(150, 750);
			contentStream.showText("Hello World");
			contentStream.endText();
			contentStream.close();
			document.save("HelloWorld.pdf");
			System.out.println("Created successfully!");
		} catch (IOException e) {
			System.out.println("Fail to create PDF while trying to create blank document - " + e);
		}
		
	}
	
}
