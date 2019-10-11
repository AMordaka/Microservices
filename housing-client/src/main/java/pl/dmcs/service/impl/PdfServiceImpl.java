package pl.dmcs.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import pl.dmcs.model.Charge;
import pl.dmcs.model.Local;
import pl.dmcs.service.ChargeService;
import pl.dmcs.service.PdfService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {

    private final ChargeService chargeService;

    public PdfServiceImpl(ChargeService chargeService) {
        this.chargeService = chargeService;
    }

    @Override
    public void generatePdf(Local local) {
        List<Charge> charges = chargeService.getChargesFromLocal(local);
        Charge lastCharge = charges.get(charges.size() - 1);
        try {
            Document pdf = new Document();
            StringBuilder builder = new StringBuilder("/home/arek/Desktop/");
            builder.append("Rachunek za mieszkanie: ");
            builder.append(local.getId());
            builder.append(lastCharge.getId());
            PdfWriter.getInstance(pdf, new FileOutputStream(builder.toString()));
            pdf.open();
            pdf.add(new Paragraph(local.toString()));
            PdfPTable table = new PdfPTable(6);
            table.addCell("Prad");
            table.addCell("Gaz");
            table.addCell("Zimna woda");
            table.addCell("Ciepla woda");
            table.addCell("Scieki");
            table.addCell("Fundusz");
            table.addCell(lastCharge.getElectricity().toString());
            table.addCell(lastCharge.getGas().toString());
            table.addCell(lastCharge.getColdWater().toString());
            table.addCell(lastCharge.getHotWater().toString());
            table.addCell(lastCharge.getSewage().toString());
            table.addCell(lastCharge.getFoundRenovation().toString());
            pdf.add(table);
            pdf.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
