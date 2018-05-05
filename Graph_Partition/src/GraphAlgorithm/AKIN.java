package GraphAlgorithm;

import java.util.*;
import java.util.HashMap;


public class AKIN extends StreamingGraphPartition{

    AKIN(int numOfPartition){
        this.num_of_partition =numOfPartition;
        initPartitions(numOfPartition);
    }

    FileOp graph = new FileOp();
    HashMap<Integer,ArrayList<Integer>> Graph = graph.ReadFile();
    HashMap<Integer,Long> vertex_hash = new HashMap<Integer, Long>();
    ArrayList<Integer> vertex_list =new ArrayList<Integer>(Graph.size());
    int server_size=10;
    int num_of_partition=server_size;

    //ojbject for utils
    Utils ul = new Utils();


    //creating 10 partition
    static Partition p0= new Partition(0);
    static Partition p1= new Partition(1);
    static Partition p2= new Partition(2);
    static Partition p3= new Partition(3);
    static Partition p4= new Partition(4);
    static Partition p5= new Partition(5);
    static Partition p6= new Partition(6);
    static Partition p7= new Partition(7);
    static Partition p8= new Partition(8);
    static Partition p9= new Partition(9);
    static Partition p10= new Partition(10);


    public void printGraph(){
        System.out.println("Printing the graph adjacency List <<AKIN>>");
        System.out.println("Printing the vertex with its edge list");
        for (Integer vertex1:Graph.keySet()) {
            Integer key = vertex1;
            ArrayList<Integer> values = Graph.get(key);
            System.out.printf("%s --> edge list - %s\n",key,values);
        }

    }

    public void print_vertex_list(){
        for (int i=0 ;i<vertex_list.size();i++){
            System.out.printf("Vertex %s is %s\n",i,vertex_list.get(i));
        }
    }



    public void print_vertex_hash(){
        for(Integer vertex:vertex_hash.keySet()){
            int key = vertex;
            long value = vertex_hash.get(key);
            System.out.printf("The hash value of %s is %s\n",key,value);
        }
    }

    public void Update_vertex_list(){
        for(Integer vertex:Graph.keySet()){
            Integer verter1 = vertex;
            vertex_list.add(verter1);
            System.out.printf("Vertex %s is updated \n",verter1);
        }

    }
    public long get_has_value(int vertex,int num_of_partition){
         long unique = Integer.toUnsignedLong(vertex);
        //int hash_value = unique.hashCode()%num_of_partition;
        long hash_value = ul.modulusHashing(unique,num_of_partition);
        return hash_value;

    }

    public void vertex_hash_map(){
        for(int i=0 ;i<vertex_list.size();i++){
            long vertex_has = get_has_value(vertex_list.get(i),num_of_partition);
            //System.out.printf("The hash value of %s is %s\n",vertex_list.get(i),vertex_has);
            vertex_hash.put(vertex_list.get(i),vertex_has);

        }
        System.out.println("__________________________________Hashing completed__________________________________");
    }

    public void partition(){
        for(int i=0;i<vertex_list.size();i++){
            determine_partition(vertex_list.get(i),num_of_partition);
        }
        System.out.println("__________________________________Partition completed__________________________________");
    }

    public void determine_partition(int vertex,int num_of_partition){

        long i = vertex_hash.get(vertex);
        if(i==0){
            p0.add_vertex(vertex);
        }
        else if(i==1){
            p1.add_vertex(vertex);
        }else if(i==2){
            p2.add_vertex(vertex);
        }else if(i==3){
            p3.add_vertex(vertex);
        }else if(i==4){
            p4.add_vertex(vertex);
        }else if(i==5){
            p5.add_vertex(vertex);
        }else if(i==6){
            p6.add_vertex(vertex);
        }else if(i==7){
            p7.add_vertex(vertex);
        }else if(i==8){
            p8.add_vertex(vertex);
        }else if(i==9){
            p9.add_vertex(vertex);
        }else if(i==10){
            p10.add_vertex(vertex);
        }


    }

    public static void print_partition_value(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the partition number: ");
        String option = sc.nextLine();
        if(option.equals("0")){
            p0.print_vertex();
        }
        else if(option.equals("1")){
            p1.print_vertex();
        }
        else if(option.equals("2")){
            p2.print_vertex();
        }
        else if(option.equals("3")){
            p3.print_vertex();
        }
        else if(option.equals("4")){
            p4.print_vertex();
        }
        else if(option.equals("5")){
            p5.print_vertex();
        }
        else if(option.equals("6")){
            p6.print_vertex();
        }
        else if(option.equals("7")){
            p7.print_vertex();
        }
        else if(option.equals("8")){
            p8.print_vertex();
        }
        else if(option.equals("9")){
            p9.print_vertex();
        }
        else if(option.equals("10")){
            p10.print_vertex();
        }


    }


    public void vertexMigration(int src,int dest){
        ArrayList<Integer> i = Graph.get(src);
        ArrayList<Integer> j = Graph.get(dest);
        System.out.printf("The edge list of %s is %s \nThe edge list of %s is %s\n",src,i,dest,j);
    }


    @Override
    public List<Integer> getPartitionForEdge(Edge edge) {
        return null;
    }

    // For AKIN, no implementation
    @Override
    public Integer tryReassignVertex(Vertex vertex) {
        return null;
    }

    // For AKIN, no implementation
    @Override
    public Map<Integer, List<Edge>> trySplittingEdge(Vertex vertex) {
        return null;
    }
}
