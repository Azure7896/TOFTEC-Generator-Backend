package com.toftec.toftecgenerator.service;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.toftec.toftecgenerator.model.Termination;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class TerminationPdfService {

    private final WordConverter wordConverter;

    public TerminationPdfService(WordConverter wordConverter) {
        this.wordConverter = wordConverter;
    }

    public String createTermination(Termination termination) throws IOException {


        String filePath = "C:\\orion\\Wypowiedzenie" + termination.getFirstName() + termination.getLastName() + ".pdf";

        PdfWriter pdfWriter = new PdfWriter(filePath);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        FontProgram fontProgram = FontProgramFactory.createFont("ArialUnicodeMS.ttf");
        PdfFont font = PdfFontFactory.createFont(fontProgram, PdfEncodings.CP1257);


        pdfDocument.setDefaultPageSize(PageSize.A4);


        Paragraph date = new Paragraph(wordConverter.deletePostalCode(termination.getCompanyCityWithPostalCode()) + ", dnia " + termination.getDate());

        date.setFixedPosition(310, 805, 200).setFontSize(12f).setTextAlignment(TextAlignment.RIGHT).setFont(font);

        Paragraph userData = new Paragraph(termination.getFirstName() + " " + termination.getLastName() + "\n" + termination.getAddress() + "\n" + termination.getCityWithPostalCode());
        userData.setFixedPosition(50, 730, 200).setFontSize(12f).setTextAlignment(TextAlignment.LEFT).setFont(font);

        Paragraph companyData = new Paragraph(termination.getCompanyName() + "\n" + termination.getCompanyAddress() + "\n"
                + termination.getCompanyCityWithPostalCode());
        companyData.setFixedPosition(350, 625, 200).setFontSize(12f).setTextAlignment(TextAlignment.RIGHT).setFont(font);

        Paragraph title = new Paragraph("Wypowiedzenie umowy o pracę");
        title.setFontSize(15f).setBold().setMarginTop(195f).setTextAlignment(TextAlignment.CENTER).setFont(font);

        Paragraph terminationBody = new Paragraph("Niniejszym wypowiadam umowę o pracę zawartą dnia 08.04.2022 pomiedzy " + termination.getCompanyName() + " a " +
                wordConverter.convertFirstName(termination.getFirstName()) + " z zachowaniem okresu wypowiedzenia wynoszacego jeden miesiac.");
        terminationBody.setFontSize(12f).setTextAlignment(TextAlignment.LEFT).setFont(font);

        document.add(date);
        document.add(userData);
        document.add(companyData);
        document.add(title);
        document.add(terminationBody);

        document.close();
        return filePath;
    }
}