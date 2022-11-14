package Entidades;
import java.util.Arrays;
import java.lang.Math;
import java.lang.Exception;

public class SudokuGraph {
    
    private Vertex[][] vertices;
    private int totalVertices = 0;
    private int dim = 0;
    private int regionsize = 0;

    public SudokuGraph(int dim) throws Exception{
        this.dim = dim;
        this.totalVertices = dim*dim;
        vertices = new Vertex[dim][dim];

        double r = Math.sqrt(dim);
        if ((r%1) != 0){
            throw new Exception("Dimension error");
        }
        this.regionsize = (int) r;

        for (int i = 0; i < dim; i++){
            for (int e = 0; e < dim; e++){
                vertices[i][e] = new Vertex(i, e);
            }
        }
    }

    public Vertex[][] getVertices() {
        return this.vertices;
    }

    public int getTotalVertices() {
        return this.totalVertices;
    }

    public Vertex getVertice(int x, int y){
        return this.vertices[x][y];
    }

    public void setVertice(int x, int y, Vertex v){
        this.vertices[x][y] = v;
    }

    public void createEdgesRow(int x, int y){
        for (int i = 0; i < dim; i++){
            if (vertices[x][i] != vertices[x][y]){
                vertices[x][y].addToAdjList(vertices[x][i]);
            }
        }
    }

    public void createEdgesColumn(int x, int y){
        for (int i = 0; i < dim; i++){
            if (vertices[i][y] != vertices[x][y]){
                vertices[x][y].addToAdjList(vertices[i][y]);
            }
        }
    }

    public void createEdgesRegion(int x, int y){
        for (int i = 0; i < dim; i++){
            if (vertices[i][y] != vertices[x][y]){
                vertices[x][y].addToAdjList(vertices[i][y]);
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(vertices);
        result = prime * result + totalVertices;
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
        SudokuGraph other = (SudokuGraph) obj;
        if (!Arrays.deepEquals(vertices, other.vertices))
            return false;
        if (totalVertices != other.totalVertices)
            return false;
        return true;
    }
    
}
