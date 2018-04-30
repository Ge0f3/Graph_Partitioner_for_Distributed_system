package GraphAlgorithm;

import java.util.Objects;

public class Edge {

    Vertex src;
    Vertex dest;

    public Edge(Vertex src, Vertex dest) {
        this.src = src;
        this.dest = dest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(src, edge.src) &&
                Objects.equals(dest, edge.dest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dest);
    }
}
