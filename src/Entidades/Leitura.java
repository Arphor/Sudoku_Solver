package Entidades;

import Entidades.Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Leitura {
    private int quantidade;
    private int falhas;
    private int nao_otimo;
    private int sucessos;
    private int totaltime;

    public void ler() {
        Scanner ler = new Scanner(System.in);
        int quantidaT = 0;
        ArrayList<ArrayList<Integer>> lista = new ArrayList<>();
    
        String nome = ler.nextLine();

        try {
          FileReader arq = new FileReader("arquivos/" + nome);
          BufferedReader lerArq = new BufferedReader(arq);

          String linha;
          while ((linha = lerArq.readLine()) != null) {
              quantidaT = Integer.parseInt(linha);
              linha = lerArq.readLine();
              lista.clear();
              final long startTime = System.currentTimeMillis();
              while(linha != null && !linha.isEmpty()){
                String[] textoSeparado = linha.split(",");
                ArrayList<Integer> row = new ArrayList<>();
                for(int n=0; n<textoSeparado.length; n++){
                    row.add(Integer.parseInt(textoSeparado[n])) ;
                }
                lista.add(row);
                
                linha = lerArq.readLine();
              }

              int[][] tabela = lista.stream().map(l -> l.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
              Controlador c = new Controlador();
              
              c.makeGraph(tabela);
              c.createEdges();
              final long startTime2 = System.currentTimeMillis();
              c.DSatur();
              final long endTime = System.currentTimeMillis();
              System.out.println(Arrays.deepToString(tabela));
              tabela = c.print();
              System.out.println(Arrays.deepToString(tabela));
              if(c.checagem()){
                  System.out.println("OK!");
              }
          }
          
    
          arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
              e.getMessage());
        }
    
        System.out.println();
      }

      public void outSudokus(int arr[][], int arr_result[][], int number, int tempo, int b) throws IOException{

        PrintStream output = new PrintStream(new FileOutputStream("resultados.txt",true));

        String s = "Sudoku número: " + number;
        output.printf(s + "%n");
        System.out.println(s);

        output.printf("%-35s %-8s %n", "Original:", "Final:");
        System.out.printf("%-35s %-8s %n", "Original:", "Final:");
        

        String linha1;
        String linha2;
        for (int i = 0; i < arr.length; i++){
            linha1 = Arrays.toString(arr[i]);
            linha2 = Arrays.toString(arr_result[i]);
            output.printf("%-35s %-8s %n", linha1, linha2);
            System.out.printf("%-35s %-8s %n", linha1, linha2);
        }

        output.append("\n");
        System.out.println("\n");
        if (b == -1){
          output.println("Prorama NAO foi bem sucedido em preencher o sudoku.\nUtilizou mais numeros/cores do que o ideal.\n");
          System.out.println("Prorama NAO foi bem sucedido em preencher o sudoku.\nUtilizou mais numeros/cores do que o ideal.");
        }

        if (b == 1){
          output.append("Prorama NAO foi bem sucedido em preencher o sudoku.\nO sudoku final não condiz com as condições para uma resposta aceitavel.\n");
          System.out.println("Prorama NAO foi bem sucedido em preencher o sudoku.\nO sudoku final não condiz com as condições para uma resposta aceitavel.");
        }

        if(b == 0){
          output.append("Programa foi bem sucedido em preencher o sudoku.\n");
          System.out.println("Programa foi bem sucedido em preencher o sudoku.");
        }

        output.close();
      }

      public void printResults() throws FileNotFoundException{

        PrintStream output = new PrintStream(new FileOutputStream("resultados.txt",true));

        output.append("O arquivo chegou ao fim. Aqui estão os resultados:\n");
        output.append("\n");
        System.out.println("O arquivo chegou ao fim. Aqui estão os resultados:");
        System.out.println();
        output.append("Numero Total de Sudokus Processados: " + quantidade + "\n");
        System.out.println("Numero Total de Sudokus Processados: " + quantidade);
        output.append("Tempo Total de Execução: " + totaltime + "\n");
        System.out.println("Tempo Total de Execução: " + totaltime);
        output.append("Número Total de Sudokus Resolvis com Sucesso: " + sucessos + "\n");
        System.out.println("Número Total de Sudokus Resolvis com Sucesso: " + sucessos);


      }
}
