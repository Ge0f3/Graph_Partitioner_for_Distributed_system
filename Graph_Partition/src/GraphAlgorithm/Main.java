package GraphAlgorithm;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    Map<String,String> graph =new HashMap<String, String>();

    Main(){
        System.out.println("<------------------------------------------------------------------------   Graph Partitioning Algorithm ------------------------------------------------ ------------------------>");

    }




    public static void main(String[] args) {
        // write your code here
        Scanner sc=new Scanner(System.in);
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Main M = new Main();
        FileOp FO=new FileOp();
        int numOfPartition=10;


        while(true){
            System.out.println("*********************************************************************************************");
            System.out.println("Type the action number as following:");
            System.out.println("1. File operation.");
            System.out.println("2. AKIN.");
            System.out.println("3. IOGP.");
            System.out.println("4. Print partition ");
            System.out.println("5. To exit.");
            System.out.println("*********************************************************************************************\n");
            String option = sc.nextLine();
            if(option.equals("1")){
                FO.ReadFile();
                FO.printEdgeList();
            }
            else if(option.equals("2")){FO.ReadFile();
                System.out.println("Enter the Number of partition: ");
                numOfPartition = sc.nextInt();
                AKIN ak = new AKIN(numOfPartition);
                ak.Partition();

//                ak.printGraph();
//                ak.Update_vertex_list();
//                ak.print_vertex_list();
//                ak.vertex_hash_map();
//                ak.print_vertex_hash();
//                ak.partition();

            }
            else if(option.equals("3")){
                IOGP iogp =new IOGP();
            }
            else if(option.equals("4")){
                AKIN.printPartitionValues();

            }

            else if(option.equals("5")){
                System.out.println("Exiting...");
                System.exit(0);
            }

        }



    }
}
