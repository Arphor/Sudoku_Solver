
import java.io.FileWriter;

import Controle.Leitura;

import java.io.BufferedWriter;
public class App {
    public static void main(String[] args) throws Exception {
        Leitura leitor = new Leitura();

        BufferedWriter output;
        output = new BufferedWriter(new FileWriter("resultados.txt", true));

        output.newLine();

        output.close();
        
        //System.out.println("Digite o nome do arquivo dentro da pasta de arquivos no formato arquivo.txt");
        //leitor.ler();

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

        leitor.outSudokus(arr, arr, 0, 0, 1);
        leitor.printResults();


        /*
        final long startTime = System.currentTimeMillis();
        Controlador c = new Controlador();
        c.makeGraph(arr);
        c.createEdges();
        c.DSatur();
        final long endTime = System.currentTimeMillis();*/

        //System.out.println("Hello, World!");

        //arr = c.print();

        //System.out.println(Arrays.deepToString(arr));
        //System.out.println("Total execution time: " + (endTime - startTime) + " milliseconds");

    }
}
