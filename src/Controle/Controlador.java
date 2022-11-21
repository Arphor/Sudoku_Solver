package Controle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Entidades.CompareVertex;
import Entidades.SudokuGraph;
import Entidades.Vertex;

public class Controlador {
    private SudokuGraph g;
    private List<Vertex> emptyGraph = new ArrayList<Vertex>();
    private int expectedtotalColors;
    private int usedtotalColors;
    private List<Integer> usedColors = new ArrayList<Integer>();


    public void readFile(String s){

    }

    public void makeGraph(int array[][]){
        try {
            g = new SudokuGraph(array.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.expectedtotalColors = array.length;
        this.usedtotalColors = array.length;
        for (int i = 0; i < array.length; i++){
            for (int e = 0; e < array[i].length; e++){

                if (!(usedColors.contains(array[i][e])) && array[i][e]!=0){
                    this.usedColors.add(array[i][e]);
                }

                Vertex v = new Vertex(i, e);
                v.setColor(array[i][e]);
                g.setVertice(i, e, v);
                if (array[i][e] == 0){
                    emptyGraph.add(v);
                }

            }
        }
    }

    public void createEdgesRow(int x, int y){
        for (int i = 0; i < g.getDim(); i++){
            if (g.getVertice(x, i) != g.getVertice(x, y)){
                Vertex v = g.getVertice(x, y);
                v.addToAdjList(g.getVertice(x, i));
                g.setVertice(x, y, v);
            }
        }
    }
    public boolean checkRow(int x, int y){
        ArrayList<Integer> valores = new ArrayList<>(); 
        for (int i = 0; i < g.getDim(); i++){
            if (g.getVertice(x, i).getColor() == 0){
                return false;
            }
            if(valores.contains(g.getVertice(x, i).getColor())){
                return false;
            }
            valores.add(g.getVertice(x, i).getColor());
        }
        if(valores.size() > this.expectedtotalColors){
            return false;
        }
        return true;
    }

    public void createEdgesColumn(int x, int y){
        for (int i = 0; i < g.getDim(); i++){
            if (g.getVertice(i, y) != g.getVertice(x, y)){
                Vertex v = g.getVertice(x, y);
                v.addToAdjList(g.getVertice(i, y));
                g.setVertice(x, y, v);
            }
        }
    }
    public boolean checkColumn(int x, int y){
        ArrayList<Integer> valores = new ArrayList<>(); 
        for (int i = 0; i < g.getDim(); i++){
            if (g.getVertice(i, y).getColor() == 0){
                return false;
            }
            if(valores.contains(g.getVertice(i, y).getColor())){
                return false;
            }
            valores.add(g.getVertice(i, y).getColor());
        }
        if(valores.size() > this.expectedtotalColors){
            return false;
        }
        return true;
    }

    public void createEdgesRegion(int x, int y){
        int x_r = x;
        int y_r = y;
        x_r -= x_r%g.getRegionsize();
        y_r -= y_r%g.getRegionsize();

        for (int i = 0; i < g.getRegionsize(); i++){
            for (int e = 0; e < g.getRegionsize(); e++){
                if (g.getVertice((i + x_r), (e + y_r)) != g.getVertice(x, y)){
                    Vertex v = g.getVertice(x, y);
                    v.addToAdjList(g.getVertice(i + x_r, e + y_r));
                    g.setVertice(x, y, v);
                }
            }
        }
    }

    public boolean checkRegion(int x, int y){
        int x_r = x;
        int y_r = y;
        x_r -= x_r%g.getRegionsize();
        y_r -= y_r%g.getRegionsize();
        ArrayList<Integer> valores = new ArrayList<>(); 

        for (int i = 0; i < g.getRegionsize(); i++){
            for (int e = 0; e < g.getRegionsize(); e++){
                if (g.getVertice((i + x_r), (e + y_r)).getColor() == 0){
                    return false;
                }
                if (valores.contains(g.getVertice((i + x_r), (e + y_r)).getColor())){
                    return false;
                }
                valores.add(g.getVertice((i + x_r), (e + y_r)).getColor());
                if(valores.size() > this.expectedtotalColors){
                    return false;
                }
            }
        }
        return true;
    }

    public void createEdges(){
        int d = g.getDim();

        for (int i = 0; i < d; i++){
            for (int e = 0; e < d; e++){
                this.createEdgesColumn(i, e);
                this.createEdgesRegion(i, e);
                this.createEdgesRow(i, e);
            }
        }
    }

    public int[][] print(){
        
        int[][] arr = new int[this.expectedtotalColors][this.expectedtotalColors];
        Vertex[][] ver = g.getVertices();

        for (int i = 0; i < this.expectedtotalColors; i++){
            for (int e = 0; e < this.expectedtotalColors; e++){
                arr[i][e] = ver[i][e].getColor();
            }
        }

        return arr;
    }

    public void setVertice(int i, int e, Vertex v){
        g.setVertice(i, e, v);
    }

    public void DSatur(){

        while (!emptyGraph.isEmpty()){
            //Find next vertex to be paint besed first on saturation and if it ties
            //Uses the degree on the sub graph of not colored vertexs.
            Vertex v = Collections.max(this.emptyGraph, new CompareVertex());

            List<Integer> colorsUnavailable = new ArrayList<>();
            for (Vertex i : v.getAdjList()) {
                if (i.getColor() != 0 && !colorsUnavailable.contains(i.getColor())){
                    colorsUnavailable.add(i.getColor());
                }
            }

            //Find the smaller color that this vertex can use
            int c;
            for ( c=1; c <= usedtotalColors+1; c++){
                if (!colorsUnavailable.contains(c)){
                    break;
                }
            }
            //If it cant find an option inside the already used colors it will take the next one
            //And update the variable so we can see it is not an optimal solution
            if (c <= usedtotalColors){
                v.setColor(c);
            }else{
                v.setColor(c);
                this.usedtotalColors = c;
            }

            //Now we remove our Vertex from the empty list
            emptyGraph.remove(v);

        }

    }

    public int checagem(){
        
        if(this.usedtotalColors > this.expectedtotalColors){
            return -1;
        }
        for(int i=0; i<this.expectedtotalColors; i++){
            for(int j=0;j<this.expectedtotalColors;j++){
                if(!checkRow(i, j) || !checkColumn(i, j) || !checkRegion(i, j)){
                    return 1;
                }
            }
        }

        return 0;
    }
}
