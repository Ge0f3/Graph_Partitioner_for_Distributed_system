package GraphAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class StreamingGraphPartition {

    static List<Partition> allPartitions = new ArrayList<>();

    public List<Partition> getAllPartitions() {
        return allPartitions;
    }

    public void setAllPartitions(List<Partition> allPartitions) {
        this.allPartitions = allPartitions;
    }


    public int getPartitionForVertex(Vertex vertex) {
        return Utils.modulusHashing(vertex.getVertexId(),
                this.getAllPartitions().size()).intValue();
    }

    /**
     *
     * @param edge
     * @return 3 integers, [partition id of src, partition id of dest, partition id of edge]
     */

    public abstract List<Integer> getPartitionForEdge(Edge edge);

    /**
     * Evaluate the edge counters of this vertex and calculate ra_score
     * if worth moving, then move the vertex,
     * otherwise , return null;
     * @param vertex
     * @return
     */
    public abstract Integer tryReassignVertex(Vertex vertex);

    /**
     * For IOGP only:
     * Try to check if we have to perform edge splitting on the given vertex.
     * If not, return null
     * if it does, we return the specified data HashMap, key it the partition ID, and value is the list of edges
     * which should be migrated to that partition.
     *
     * For AKIN, simply return null;
     * @param vertex
     * @return
     */
    public abstract Map<Integer, List<Edge>> trySplittingEdge(Vertex vertex);

    //function to create partition based on input -> numPartitions
    public void initPartitions(int numPartitions){
        for(int  i = 0; i < numPartitions; i++){
            allPartitions.add(new Partition(i));
        }
    }
}
