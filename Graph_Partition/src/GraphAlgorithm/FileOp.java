package GraphAlgorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileOp {
    Graph grap = new Graph(349);
    HashMap<Integer,ArrayList<Integer>> Graph =new HashMap<Integer, ArrayList<Integer>>();
    FileOp(){
        System.out.println("<------------------------------------------------------------------------   Reading the file ------------------------------------------------ ------------------------>");
    }

    public boolean isavailable(int vertex){
        boolean result = Graph.containsKey(vertex);
        return result;
//        if(result){
//            System.out.printf("%s vertex is availabe in the graph",vertex);
//        }
//        else{
//            System.out.printf("%s is not present in the graph !!\n update the graph with the vertex !!\n",vertex);
//        }

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
        System.out.println("Printing the vertex with its edge list");
        for (Integer vertex1:Graph.keySet()) {
            Integer key = vertex1;
            ArrayList<Integer> values = Graph.get(key);
            System.out.printf("%s --> edge list - %s\n",key,values);
        }

    }
    public void ReadFile(){
        Scanner F = null;
        try{
            F = new Scanner(new File("text_file.txt"));
        }
        catch (Exception e){
            System.out.println("Couldn't Find the find the file ");
        }
        while(F.hasNext()){
            int edge1 = Integer.parseInt(F.next());
            int edge2 = Integer.parseInt(F.next());
            addEdge(edge1,edge2);
            //System.out.printf("%s is connected to %s \n",edge1,edge2);

        }

        System.out.println("Printing the graph adjacency List");
        printGraph();
    }
}
