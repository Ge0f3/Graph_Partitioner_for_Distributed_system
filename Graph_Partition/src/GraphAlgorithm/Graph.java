package GraphAlgorithm;

import java.util.LinkedList;

public class Graph {
    int v;
    LinkedList<Integer> adjlistArray[];
    Graph(int v){
        this.v=v;

        //defining the array size of the adjacency list
        adjlistArray = new LinkedList[v];

        //create a new list for each vertex
        for(int i=0;i<v;i++){
            adjlistArray[i]=new LinkedList<>();
        }

    }
    public static void addEdge(Graph graph,int src,int dest){
        //add an edge from source to destination
        graph.adjlistArray[src].addFirst(dest);
        //since its undirected have it added from destination and source to
        graph.adjlistArray[dest].addFirst(src);
    }
    public static void  printGraph(Graph graph){
        for(int j=0;j<graph.v;j++){
            System.out.println("Adjacency list of vertex "+ j);
            //System.out.print("head");
            for(Integer pCrawl: graph.adjlistArray[j]){
                System.out.print(" -> "+pCrawl);
            }
            System.out.println("\n");
        }
    }
}
