package GraphAlgorithm;

import java.util.*;

public class Partition {
    int vertex_size;
    int edge_size;
    ArrayList<Integer> VertexListP =new ArrayList<Integer>();
    Set<Vertex> vertexSet = new HashSet<>();
    Set<Edge> edgeSet = new HashSet<>();


    int partition_id;

    //contrtucor
    Partition(int partition_id){
        this.partition_id=partition_id;
        System.out.printf("Partition %s created !\n",partition_id);
    }

    //check if the vertex is in the partition

    public boolean in_partition(Vertex vertex){
        return vertexSet.contains(vertex);
    }

    public void print_vertex(){
        for(int i=0;i<VertexListP.size();i++){
            System.out.printf("Vertex is %s\n",VertexListP.get(i));
        }
    }

    public void add_vertex(int vertex){
        VertexListP.add(vertex);
    }

    public int getVertex_size() {
        return vertex_size;
    }


    public void setVertex_size(int vertex_size) {
        this.vertex_size = vertex_size;
    }

    public int getEdge_size() {
        return edge_size;
    }

    public void setEdge_size(int edge_size) {
        this.edge_size = edge_size;
    }





    public void addVerterx_toP(int vertex){
        //This function will get the vertex from the partition size and include in the partition hashmap
        VertexListP.add(vertex);

    }
}
