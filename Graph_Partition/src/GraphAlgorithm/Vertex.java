package GraphAlgorithm;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vertex {

    long vertexId;

    Set<Integer> outgoingNeighbors = new HashSet<Integer>();
    Set<Integer> incomingNeighbors = new HashSet<Integer>();

    boolean isReference;

    int partitionId=0;



    public Vertex(long vertexId) {
        this.vertexId = vertexId;
    }

    public Vertex(long vertexId, boolean isReference) {
        this.vertexId = vertexId;
        this.isReference = isReference;
    }

    public long getVertexId() {
        return vertexId;
    }

    public void setVertexId(long vertexId) {
        this.vertexId = vertexId;
    }

    public Set<Integer> getOutgoingNeighbors() {
        return outgoingNeighbors;
    }

    public void setOutgoingNeighbors(Set<Integer> outgoingNeighbors) {
        this.outgoingNeighbors = outgoingNeighbors;
    }

    public Set<Integer> getIncomingNeighbors() {
        return incomingNeighbors;
    }

    public void setIncomingNeighbors(Set<Integer> incomingNeighbors) {
        this.incomingNeighbors = incomingNeighbors;
    }

    public boolean isReference() {
        return isReference;
    }

    public void setReference(boolean reference) {
        isReference = reference;
    }

    public int getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(int partitionId) {
        this.partitionId = partitionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return vertexId == vertex.vertexId
               // && isReference == vertex.isReference
               ;
    }

    @Override
    public int hashCode() {
        //return Objects.hash(vertexId,  isReference);
        return Objects.hash(vertexId);
    }
}
