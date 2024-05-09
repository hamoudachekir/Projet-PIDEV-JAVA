package service;

import model.Reservation;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class PdfGeneratorService {

    public void generatePdf(String filePath, Reservation reservation, String activity) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                File imageFile = new File("C:/Users/manna/OneDrive/Bureau/ADVENTURE.png");
                PDImageXObject image = PDImageXObject.createFromFileByContent(imageFile, document);

                // Get the image dimensions
                float imageWidth = image.getWidth();
                float imageHeight = image.getHeight();

                // Draw the image at the top-left corner
                contentStream.drawImage(image, 20, PDRectangle.LETTER.getHeight() - 20 - imageHeight, imageWidth, imageHeight);

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                contentStream.newLineAtOffset(100, 750);
                contentStream.showText("Reservation Details");
                contentStream.endText();


                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(100, 700);
                contentStream.setLeading(14);
                contentStream.showText(activity);
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12); // Content font size
                contentStream.newLineAtOffset(100, 680); // Content position
                contentStream.setLeading(14); // Line spacing
                contentStream.showText("Email : "+reservation.getUserEmail());
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12); // Content font size
                contentStream.newLineAtOffset(100, 660); // Content position
                contentStream.setLeading(14); // Line spacing
                contentStream.showText("Tickets : "+reservation.getNbrTickets());
                contentStream.endText();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = reservation.getDate().toLocalDateTime().format(formatter);

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12); // Content font size
                contentStream.newLineAtOffset(100, 640); // Content position
                contentStream.setLeading(14); // Line spacing
                contentStream.showText("Date : "+formattedDate);
                contentStream.endText();
            }



            document.save(filePath);
            System.out.println("PDF created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
