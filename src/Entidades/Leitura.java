package Entidades;

import Entidades.Controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Leitura {

    private long startTime;
    private long startTime2;
    private long endTime;

    private Double tempoTotal;
    private Double tempoTSolucao;

    public Double timeSolu(){
      tempoTSolucao = ((double)this.endTime - (double)this.startTime2)/1000000000; 
      NumberFormat format = new DecimalFormat("###.#######");
      String f = format.format(tempoTSolucao);
      System.out.println("TESTE: " + f);
      return tempoTSolucao;
    }

    public Double timeTotal(){
      tempoTotal = ((double)this.endTime - (double)this.startTime)/1000000000;
      NumberFormat format = new DecimalFormat("###.#######");
      String f = format.format(tempoTotal);
      System.out.println("TESTE: " + f);
      return tempoTotal;    
    }

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
              this.startTime = System.nanoTime();
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
              int[][] tabelaSolu = tabela;
              
              c.makeGraph(tabela);
              c.createEdges();
              this.startTime2 = System.nanoTime();
              c.DSatur();
              this.endTime = System.nanoTime();
              //System.out.println(Arrays.deepToString(tabela));
              tabelaSolu = c.print();
              //System.out.println(Arrays.deepToString(tabela));
              for(int i=0; i<tabela.length;i++){
                System.out.print(Arrays.toString(tabela[i]));
                System.out.print("          ");
                System.out.println(Arrays.toString(tabelaSolu[i]));
              }
              if(c.checagem()){
                System.out.println("OK!");
              }else{
                System.out.println("Erro!");
              }
              System.out.println("Tempo Total: " + timeTotal());
              System.out.println("Tempo Solução: " + timeSolu());
          }
          
    
          arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
              e.getMessage());
        }
    
        System.out.println();
      }
}
