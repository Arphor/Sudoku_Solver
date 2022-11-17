import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import Entidades.Vertex;
import Entidades.Leitura;
public class App {
    public static void main(String[] args) throws Exception {
        Leitura leitor = new Leitura();
        
        System.out.println("Digite o nome do arquivo dentro da pasta de arquivos no formato arquivo.txt");
        leitor.ler();

    }
}
