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
import java.text.ParseException;
import java.util.Random;


@Service
public class TerminationPdfService {

    private final WordConverterService wordConverterService;

    public TerminationPdfService(WordConverterService wordConverterService) {
        this.wordConverterService = wordConverterService;
    }

    public String createTermination(Termination termination) throws IOException, ParseException {

        Random randomNumber = new Random();

        String filePath = termination.getFirstName() + termination.getLastName() + randomNumber.nextInt(10000000) + ".pdf";

        PdfWriter pdfWriter = new PdfWriter(filePath);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);




        FontProgram fontProgram = FontProgramFactory.createFont("ArialUnicodeMS.ttf");
        PdfFont font = PdfFontFactory.createFont(fontProgram, PdfEncodings.CP1257);


        pdfDocument.setDefaultPageSize(PageSize.A4);


        Paragraph date = new Paragraph(wordConverterService.deletePostalCode(termination.getCompanyCityWithPostalCode()) + ", dnia " + wordConverterService.convertDate(termination.getTerminationDocumentDate()));

        date.setFixedPosition(310, 805, 200).setFontSize(11f).setTextAlignment(TextAlignment.RIGHT).setFont(font);

        Paragraph userData = new Paragraph(termination.getFirstName() + " " + termination.getLastName() + "\n" + termination.getAddress() + "\n" + termination.getCityWithPostalCode());
        userData.setFixedPosition(50, 730, 200).setFontSize(11f).setTextAlignment(TextAlignment.LEFT).setFont(font);

        Paragraph companyData = new Paragraph(termination.getCompanyName() + "\n" + termination.getCompanyAddress() + "\n"
                + termination.getCompanyCityWithPostalCode());
        companyData.setFixedPosition(350, 625, 200).setFontSize(11f).setTextAlignment(TextAlignment.RIGHT).setFont(font);

        Paragraph title = new Paragraph("Wypowiedzenie umowy o pracę");
        title.setFontSize(15f).setBold().setMarginTop(195f).setTextAlignment(TextAlignment.CENTER).setFont(font);

        String terminationPeriod = null;

        if (termination.getTerminationPeriod().equals("twoWeeks")) {
            terminationPeriod = "dwa tygodnie.";
        } else if (termination.getTerminationPeriod().equals("oneMonth")) {
            terminationPeriod = "jeden miesiąc.";
        } else if (termination.getTerminationPeriod().equals("threeMonths")) {
            terminationPeriod = "trzy miesiące.";
        }

        Paragraph terminationBody = new Paragraph("Niniejszym wypowiadam umowę o pracę zawartą dnia " + wordConverterService.convertDate(termination.getEmploymentContractDate()) +
                " pomiedzy " + termination.getCompanyName() + " a " + termination.getInstrumentalCaseFirstName() + " " + termination.getInstrumentalCaseLastName() +
                " z zachowaniem okresu wypowiedzenia wynoszącego " + terminationPeriod);

        terminationBody.setFontSize(11f).setTextAlignment(TextAlignment.LEFT).setFont(font);

        Paragraph companySignature = new Paragraph("Potwierdzam odebranie wypowiedzenia\n" + "...................................\n" + "(podpis pracodawcy lub osoby upoważnionej)");
        companySignature.setFontSize(11f).setFixedPosition(50, 330, 200).setTextAlignment(TextAlignment.CENTER).setFont(font);

        Paragraph employeeSignature = new Paragraph("Z poważaniem,\n" + "...................................\n" + "Podpis pracownika" );
        employeeSignature.setFontSize(11f).setFixedPosition(350, 430, 200).setTextAlignment(TextAlignment.CENTER).setFont(font);


        document.add(date);
        document.add(userData);
        document.add(companyData);
        document.add(title);
        document.add(terminationBody);
        document.add(employeeSignature);
        document.add(companySignature);

        document.close();

        return filePath;
    }
}