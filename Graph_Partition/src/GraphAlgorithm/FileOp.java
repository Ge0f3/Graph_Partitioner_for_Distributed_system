package GraphAlgorithm;

import java.io.File;
import java.util.*;

public class FileOp {
    Graph grap = new Graph(349);
    HashMap<Integer,ArrayList<Integer>> Graph =new HashMap<Integer, ArrayList<Integer>>();
    static Set<Edge> edgeList = new HashSet<Edge>();

    FileOp(){
        System.out.println("<------------------------------------------------------------------------   Reading the file ------------------------------------------------ ------------------------>");
    }

    //function to read the file and store the vertex and its edge list in a hashmap
    public HashMap<Integer, ArrayList<Integer>> ReadFile(){
        Scanner F = null;
        try{
            F = new Scanner(new File("text_file.txt"));
        }
        catch (Exception e){
            System.out.println("Couldn't Find the find the file ");
        }
        while(F.hasNext()){

            int edge1 = Integer.parseInt(F.next());
            Vertex V1= new Vertex(edge1);
            int edge2 = Integer.parseInt(F.next());
            Vertex V2= new Vertex(edge2);
            Edge E =new Edge(V1,V2);
            edgeList.add(E);
            addEdge(edge1,edge2);

        }
        return Graph;

    }
    //print the edges in the edgelist
    public void printEdgeList(){
        System.out.println("<------------------------------------------------------------------------   Printing edge List ------------------------------------------------------------------------>");
        for (Edge elem:edgeList) {
            System.out.printf("Vertex %s -- Vertex %s \n",elem.src.vertexId,elem.dest.vertexId);
        }

    }

    public GraphAlgorithm.Graph getGrap() {
        return grap;
    }

    public void setGrap(GraphAlgorithm.Graph grap) {
        this.grap = grap;
    }

    public HashMap<Integer, ArrayList<Integer>> getGraph() {
        return Graph;
    }

    public void setGraph(HashMap<Integer, ArrayList<Integer>> graph) {
        Graph = graph;
    }

    public static Set<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(Set<Edge> edgeList) {
        this.edgeList = edgeList;
    }


    public HashMap SenderGraph(){

        return Graph;
    }
    public boolean isavailable(int vertex){
        boolean result = Graph.containsKey(vertex);
        return result;

    }


    public void addEdge(int src,int dest){

        if(isavailable(src)){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp = Graph.get(src);
            temp.add(dest);
            Graph.put(src,temp);
        }
        else {
            ArrayList<Integer> edges =new ArrayList<Integer>();
            edges.add(dest);
            Graph.put(src,edges);
        }

    }
    public void printGraph(){
        System.out.println("Printing the graph adjacency List");
        System.out.println("Printing the vertex with its edge list");
        for (Integer vertex1:Graph.keySet()) {
            Integer key = vertex1;
            ArrayList<Integer> values = Graph.get(key);
            System.out.printf("%s --> edge list - %s\n",key,values);
        }

    }



}
