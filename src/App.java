import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import Entidades.Vertex;

public class App {
    public static void main(String[] args) throws Exception {

        int arr[][] = {
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0}
        };
        
        Controlador c = new Controlador();
        c.makeGraph(arr);
        c.createEdges();
        c.DSatur();

        System.out.println("Hello, World!");

        arr = c.print();

        System.out.println(Arrays.deepToString(arr));

    }
}
