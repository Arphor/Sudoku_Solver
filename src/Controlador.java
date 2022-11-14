import java.util.ArrayList;
import java.util.List;

import Entidades.SudokuGraph;
import Entidades.Vertex;

public class Controlador {
    private SudokuGraph g;
    private int totalColors;
    private List<Integer> usedColors = new ArrayList<Integer>();


    public void readFile(String s){

    }

    public void makeGraph(int array[][]){
        try {
            this.g = new SudokuGraph(array.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.totalColors = array.length;
        for (int i = 0; i < array.length; i++){
            for (int e = 0; e < array[i].length; e++){

                if (!usedColors.contains(array[i][e])){
                    usedColors.add(array[i][e]);
                }

                Vertex v = new Vertex(i, e);
                v.setColor(array[i][e]);
                g.setVertice(i, e, v);

            }
        }
    }

    public void colorGraph(){

    }
}
