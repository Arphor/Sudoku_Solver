
import java.io.FileWriter;
import java.util.Scanner;

import Controle.Leitura;

import java.io.BufferedWriter;
public class App {
    public static void main(String[] args) throws Exception {
        Leitura leitor = new Leitura();


        Scanner sc=new Scanner(System.in);
        
        String welcome = "Bem vindo ao Sudoku Solver.\n"
                        + "Para prosseguir digite o numero de uma das opcoes e pressione enter:\n"
                        + "Digite 1 para Instrucoes.\n"
                        + "Digite 2 para Selecionar arquivo de leitura.\n"
                        + "Digite 3 para Encerrar o programa.";

        System.out.println(welcome);
        String entrada = sc.nextLine();
        while(!entrada.equals("3") && !entrada.equals("1") && !entrada.equals("2")){
            System.out.println("Entrada invalida, por favor, tente novamente.");
            System.out.println(entrada);
            entrada = sc.nextLine();
        }

        System.out.println("oi");

        while(!entrada.equals("3")){
            if (entrada.equals("1")){
                String instrucoes = "INSTRUCOES";
                System.out.println(instrucoes);
            }

            if (entrada.equals("2")){
                String programa = "PROGRAMA";
                System.out.println(programa);
                leitor.ler();
            }
            entrada = sc.nextLine();
        }
        
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
