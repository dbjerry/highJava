/*
 
    This file is part of the iText (R) project.
    Copyright (c) 1998-2016 iText Group NV
 
*/
/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/27241731/change-background-image-in-itext-to-watermark-or-alter-opacity-c-sharp-asp-net
 */
package jumpstart;

import java.io.File;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class BackgroundTransparent {
	public static final String DEST = "results/chapter01/backgroundimage2.pdf";
    public static final String IMAGE = "src/main/resources/img/ddit.jpg";
    
    public static void main(String[] args) throws Exception {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new BackgroundTransparent().manipulatePdf(DEST);
		
	}

	protected void manipulatePdf(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		
		// Note that it is not necessary to create new PageSize object,
        // but for testing reasons (connected to parallelization) we call constructor here
//		PageSize pageSize = new PageSize(PageSize.A4).rotate();
		PageSize pageSize = new PageSize(PageSize.A4);
		Document doc = new Document(pdfDoc, pageSize);
		
		PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
		ImageData image = ImageDataFactory.create(IMAGE);
		
		canvas.saveState();
		
		PdfExtGState state = new PdfExtGState();
		state.setFillOpacity(0.3f);
		canvas.setExtGState(state);
		canvas.addImage(image, 85, 400, (float)((pageSize.getWidth())/1.5), false);
		canvas.restoreState();
		
		doc.add(new Paragraph("Certificate"));
		doc.close();
		
	}
}

