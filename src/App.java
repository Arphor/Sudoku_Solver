
import java.io.PrintStream;
import java.util.Scanner;
import Controle.Leitura;
import java.io.FileOutputStream;
public class App {
    public static void main(String[] args) throws Exception {
        Leitura leitor = new Leitura();

        PrintStream output = new PrintStream(new FileOutputStream("resultados.txt",false));
        output.close();

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
                + "Sudokus_16x16_medio.txt \n"
                + "\n"
                + "O programa vai apenas acrescentar os resultados no mesmo arquivo de saída caso você os execute consecutivamente.\n"
                + "Para um Sudoku ser considerado Resolvido Com Sucesso ele precisa ter sido completamente colorido e se manter respeitando as regras do jogo.";
                System.out.println(instrucoes);
            }

            if (entrada.equals("2")){
                String programa = "Qual arquivo deseja executar?: ";
                System.out.print(programa);
                leitor.ler();
            }
            System.out.println("Digite o numero da próxima ação.");
            entrada = sc.nextLine();
        }

        sc.close();
    }
}
