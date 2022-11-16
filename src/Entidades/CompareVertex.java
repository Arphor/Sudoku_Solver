package Entidades;
import java.util.Comparator;

public class CompareVertex implements Comparator<Vertex> {

    @Override
    public int compare(Vertex o1, Vertex o2) {
        if (o1.calcSat() > o2.calcSat()){
            return 1;
        }
        if (o1.calcSat() == o2.calcSat() && o1.calcDegree() > o2.calcDegree()){
            return 1;
        }
        return -1;
    }
    
    
}
