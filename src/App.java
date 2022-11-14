import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class App {
    public static void main(String[] args) throws Exception {


        File file = new File("arquivos/Sudokus_Pattern_Easy.pdf");

        try (PDDocument document = PDDocument.load( file )) {
            System.out.print("oioioioi");

            if (!document.isEncrypted()) {
                System.out.print("oioioioi");
			
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);

                String lines[] = pdfFileInText.split("\\r?\\n");
                for (String line : lines) {
                    System.out.println(line);
            }



        System.out.println("Hello, World!");
        }
    }
    }
}
