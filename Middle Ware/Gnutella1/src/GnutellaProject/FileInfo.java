package GnutellaProject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dheeraj
 */
@SuppressWarnings("serial") //Indicates that the named compiler warnings should be suppressed in the annotated element 
                            //(and in all program elements contained in the annotated element). 

public class FileInfo implements Serializable 
{
	public int originateId;
    public int fromPeerId;
    public int vetrex1,vertex2;
    public List<Integer> adjList = new ArrayList<Integer>();
	

    
}
