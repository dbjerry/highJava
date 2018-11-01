/*
 
    This file is part of the iText (R) project.
    Copyright (c) 1998-2016 iText Group NV
 
*/
/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/21731027/backgroundimage-in-landscape-and-cover-whole-pdf-with-itextsharp
 */
package jumpstart;

import java.io.File;
import java.io.FileNotFoundException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class BackgroundImage {
	public static final String DEST = "results/chapter01/backgroundimage.pdf";
    public static final String IMAGE = "src/main/resources/img/ddit.jpg";
 
    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BackgroundImage().manipulatePdf(DEST);
    }
    
	private void manipulatePdf(String dest) throws Exception {
    	PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
    	// Note that it is not necessary to create new PageSize object,
        // but for testing reasons (connected to parallelization) we call constructor here
    	
    	PageSize pageSize = new PageSize(PageSize.A4).rotate();
    	Document doc = new Document(pdfDoc, pageSize);
    	PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
    	canvas.addImage(ImageDataFactory.create(IMAGE), pageSize, false);
    	doc.add(new Paragraph("DDIT"));
    	
    	doc.close();
	}
}
