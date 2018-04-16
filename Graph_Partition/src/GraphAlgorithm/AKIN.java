package GraphAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;


public class AKIN {
    LinkedList<Integer> pratition;
    Map<Integer,Integer> Graph = new HashMap<Integer,Integer>();
    ArrayList<Integer> partition = new ArrayList<Integer>();
    AKIN(){
        System.out.println("Class for AKIN Algorithm");
    }
    boolean not_exists(int vertex,ArrayList partition){
        boolean exist=true;

        return exist;
    }
    void DPartition(int vertex,int Partition_size){
        int i =vertex;
        if(partition.contains(i)){
            if(not_exists(vertex,partition)){
                partition.add(vertex);
            }
        }

    }
    boolean isFull(ArrayList partition){
        if (){
            return true;
        }
        else{
            return false;
        }
    }
    void partition(int src,int destination,ArrayList partition){
        int i = Graph.get(src);
        int j = Graph.get(destination);
        ArrayList t ;
        if(){
            int max_score = 0;

            for (Object part:partition) {
                int x =Graph.get(i);
                if(x>max_score && isFull(partition)){
                    max_score =x;
                    t.add(part);
                }

            }

        }
        if(t.isEmpty()){

        }

    }
}
