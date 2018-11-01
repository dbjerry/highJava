package jumpstart;

/*
This file is part of the iText (R) project.
Copyright (c) 1998-2017 iText Group NV
Authors: iText Software.

For more information, please contact iText Software at this address:
sales@itextpdf.com
*/
/*
* This example was written by Bruno Lowagie
* in the context of the book: iText 7 building blocks
*/
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.IOException;

/**
* @author Bruno Lowagie (iText Software)
*/
public class Korean_Unicode {

	public static final String DEST = "results/chapter01/korean_unicode.pdf";
	public static final String FONT = "src/main/resources/fonts/NanumBarunGothic.ttf";
	
//	public static final String KOREAN = "\ud558\uc774\ub4dc, \uc9c0\ud0ac, \ub098";
	public static final String KOREAN = "안녕하세요, 김지태입니다.\n대덕인재개발원을 다니고 있습니다.\n인재가 되기 위해 노력하고 있습니다.\n감사합니다.";
	
	public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Korean_Unicode().createPdf(DEST);
    }
	
	public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);

        // Add content
        PdfFont freeUnicode = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H, true);
        
        document.add(new Paragraph().setFont(freeUnicode)
                .add(KOREAN).add(" by Robert Louis Stevenson"));

        //Close document
        document.close();
    }
}

