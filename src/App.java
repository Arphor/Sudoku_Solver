
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

        while(!entrada.equals("3")){
            if (entrada.equals("1")){
                String instrucoes = "INSTRUCOES:\n"
                + "Após selecionar a opção 2 digite o nome do arquivo, incluindo o .txt, que deseja executar.\n"
                + "\n"
                + "Nomes de arquivos: \n"
                + "Sudokus_4x4_facil.txt \n"
                + "Sudokus_4x4_dificil.txt \n"
                + "Sudokus_9x9_facil.txt \n"
                + "Sudokus_9x9_medio.txt \n"
                + "Sudokus_9x9_dificil.txt \n"
                + "Sudokus_16x16_facil.txt \n"
                + "Sudokus_16x16_medio.txt \n";
                System.out.println(instrucoes);
            }

            if (entrada.equals("2")){
                String programa = "Qual arquivo deseja executar?: ";
                System.out.print(programa);
                leitor.ler();
            }
            entrada = sc.nextLine();
        }
    }
}
