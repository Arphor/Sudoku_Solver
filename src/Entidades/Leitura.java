package Entidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Leitura {
    public void ler() {
        Scanner ler = new Scanner(System.in);
        int quantidaT = 0;
        int[] lista;
    
        String nome = ler.nextLine();

        try {
          FileReader arq = new FileReader("arquivos/" + nome);
          BufferedReader lerArq = new BufferedReader(arq);

          String linha;
          while ((linha = lerArq.readLine()) != null) {
              quantidaT = Integer.parseInt(linha);
              linha = lerArq.readLine();
              while(linha != null && !linha.isEmpty()){
                //System.out.println(linha);
                String[] textoSeparado = linha.split(",");
                lista = new int[textoSeparado.length];
                for(int n=0; n<textoSeparado.length; n++){
                    lista[n] = Integer.parseInt(textoSeparado[n]);
                }
                System.out.println(quantidaT);
                System.out.println(Arrays.toString(lista));
                linha = lerArq.readLine();
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
