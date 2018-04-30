package GraphAlgorithm;

import java.util.List;
import java.util.Map;

public class IOGP extends StreamingGraphPartition{
    IOGP(){
        System.out.println("This is a class for IOGP implementation !");
    }


    @Override
    public List<Integer> getPartitionForEdge(Edge edge) {
        return null;
    }

    @Override
    public Integer tryReassignVertex(Vertex vertex) {
        return null;
    }

    @Override
    public Map<Integer, List<Edge>> trySplittingEdge(Vertex vertex) {
        return null;
    }
}
