package Controle;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Leitura {
    public int quantidade=0;
    private int falhas=0;
    private int nao_otimo=0;
    public int sucessos=0;
    public long totaltime = 0;

    private long startTime;
    private long startTime2;
    private long endTime;

    private Double tempoTotal;
    private Double tempoTSolucao;

    public Double timeSolu(){
      tempoTSolucao = ((double)this.endTime - (double)this.startTime2); 
      NumberFormat format = new DecimalFormat("###.#######");
      //String f = format.format(tempoTSolucao);
      //System.out.println("TESTE: " + f);
      return tempoTSolucao;
    }
    //Contagem do tempo
    public Double timeTotal(){
      tempoTotal = ((double)this.endTime - (double)this.startTime);
      NumberFormat format = new DecimalFormat("###.#######");
      //String f = format.format(tempoTotal);
      //System.out.println("TESTE: " + f);
      return tempoTotal;    
    }
    public boolean verificarTabela(int array[][]){
      for(int i=0; i<array.length;i++){
        if(array.length != array[i].length){
          return false;
        }
      }
      return true;
    }

    public void ler() {
        Scanner ler = new Scanner(System.in);
        int quantidaT = 0;
        this.sucessos=0;
        this.falhas=0;
        this.nao_otimo=0;
        this.totaltime=0;
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
              startTime = System.nanoTime();
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

              int[][] tabelaResultado = tabela;
              if(verificarTabela(tabela)){
              
              c.makeGraph(tabela);
              c.createEdges();
              startTime2 = System.nanoTime();
              c.DSatur();
              endTime = System.nanoTime();
              tabelaResultado = c.print();

              outSudokus(tabela, tabelaResultado, quantidaT, endTime, c.checagem());
              //Contagem do tempo
              this.totaltime += (endTime - startTime);
              
              


            }else{
              System.out.println("Problema na tabela: " + quantidaT);
              this.falhas++;
            }
          }
          
          
    
          arq.close();
          this.quantidade = quantidaT;
          printResults();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
              e.getMessage());
        }
    
        
      }

      public void outSudokus(int arr[][], int arr_result[][], int number, long tempo, int b) throws IOException{

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
          this.nao_otimo++;
          output.println("Prorama NAO foi bem sucedido em preencher o sudoku.\nUtilizou mais numeros/cores do que o ideal.\n");
          System.out.println("Prorama NAO foi bem sucedido em preencher o sudoku.\nUtilizou mais numeros/cores do que o ideal.");
        }

        if (b == 1){
          this.falhas++;
          output.append("Prorama NAO foi bem sucedido em preencher o sudoku.\nO sudoku final não condiz com as condições para uma resposta aceitavel.\n");
          System.out.println("Prorama NAO foi bem sucedido em preencher o sudoku.\nO sudoku final não condiz com as condições para uma resposta aceitavel.");
        }

        if(b == 0){
          this.sucessos++;
          output.append("Programa foi bem sucedido em preencher o sudoku.\n");
          System.out.println("Programa foi bem sucedido em preencher o sudoku.");
        }

        output.close();
      }

      public void printResults() throws FileNotFoundException{

        if (quantidade != 0){

        

          PrintStream output = new PrintStream(new FileOutputStream("resultados.txt",true));
          NumberFormat format = new DecimalFormat("###.#######");
          //String f = format.format(tempoTotal/1000000000);
          //System.out.println(f);
          


          String s = "\n"
                      + "O arquivo chegou ao fim. Aqui estão os resultados:\n"
                      + "\n"
                      + "Numero Total de Sudokus Processados: " + quantidade + "\n"
                      + "Tempo Total de Execução: " + totaltime/1000000 + " milisegundos\n"
                      + "Numero Total de Sudokus Resolvidos com Sucesso: " + sucessos + "\n"
                      + "Numero de Sudokus Coloridos, mas com numero errado de cores: " + nao_otimo + "\n"
                      + "Numero de Sudokus que o programa falhou em colorir: " + falhas + "\n"
                      + "\n"
                      + "Media de tempo de execução por sudoku: " + ((totaltime/1000000)/quantidade) + " milesegundos\n"
                      + (((sucessos+nao_otimo)/quantidade)*100) + "% de Sudokus coloridos com sucesso" + "\n"
                      + (((sucessos)/quantidade)*100) + "% de Sudokus coloridos com seu numero cromatico" + "\n";

          output.append(s);
          System.out.print(s);

          output.close();
        }



      }
}
