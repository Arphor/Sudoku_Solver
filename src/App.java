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
            {7,2,3,0,0,0,1,5,9},
            {6,0,0,3,0,2,0,0,8},
            {8,0,0,0,1,0,0,0,2},
            {0,7,0,6,5,4,0,2,0},
            {0,0,4,2,0,7,3,0,0},
            {0,5,0,9,3,1,0,4,0},
            {5,0,0,0,7,0,0,0,3},
            {4,0,0,1,0,3,0,0,6},
            {9,3,2,0,0,0,7,1,4}
        };
        
        final long startTime = System.currentTimeMillis();
        Controlador c = new Controlador();
        c.makeGraph(arr);
        c.createEdges();
        c.DSatur();

        final long endTime = System.currentTimeMillis();

        System.out.println("Hello, World!");

        arr = c.print();

        System.out.println(Arrays.deepToString(arr));
        System.out.println("Total execution time: " + (endTime - startTime) + " milliseconds");

    }
}
