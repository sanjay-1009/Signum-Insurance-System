package service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFExtractorService {

    public String extractText(String pdfPath) {

        try {

            File file = new File(pdfPath);

            if (!file.exists()) {

                System.out.println("PDF File Not Found.");

                return null;
            }

            PDDocument document =
                    Loader.loadPDF(file);

            PDFTextStripper stripper =
                    new PDFTextStripper();

            String text =
                    stripper.getText(document);

            document.close();

            return text;

        }

        catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }

}