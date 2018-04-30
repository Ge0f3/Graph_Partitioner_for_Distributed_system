package GraphAlgorithm;

public class Utils {

    public static Long modulusHashing(long id, int numPartitions) {
        return id % numPartitions;
    }
}
