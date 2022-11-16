package Entidades;

import Entidades.Controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Leitura {
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
}
