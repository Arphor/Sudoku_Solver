package Entidades;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Vertex {

    private int x;
    private int y;
    private int color;
    private LinkedList<Vertex> adjList;

    

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = 0;
        this.adjList = new LinkedList<>();
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public LinkedList<Vertex> getAdjList() {
        return adjList;
    }
    public void addToAdjList(Vertex v) {
        if (!adjList.contains(v)){
            adjList.add(v);
        }
    }

    public int calcSat(){
        ArrayList<Integer> l = new ArrayList<>();

        for (Vertex i : this.adjList){
            if (i.getColor() != 0) {
                if (!l.contains(i.getColor())){
                    l.add(i.getColor());
                }
            }
        }
        return l.size();
    }

    public int calcDegree(){

        int count = 0;

        for (Vertex i : this.adjList){
            if (i.getColor() == 0) {
                count++;
            }
        }

        return count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + color;
        result = prime * result + ((adjList == null) ? 0 : adjList.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (color != other.color)
            return false;
        if (adjList == null) {
            if (other.adjList != null)
                return false;
        } else if (!adjList.equals(other.adjList))
            return false;
        return true;
    }

}

