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
            System.out.println("1. AKIN.");
            System.out.println("2. IOGP.");
            System.out.println("3. Print partition ");
            System.out.println("4. To exit.");
            System.out.println("*********************************************************************************************\n");
            String option = sc.nextLine();
            if(option.equals("1")){
                System.out.println("Enter the Number of partition: ");
                numOfPartition = sc.nextInt();
                AKIN ak = new AKIN(numOfPartition);
                FO.ReadFile();
                ak.printGraph();
                ak.Update_vertex_list();
                ak.print_vertex_list();
                ak.vertex_hash_map();
                ak.print_vertex_hash();
                ak.partition();
            }
            if(option.equals("2")){
                IOGP iogp =new IOGP();
            }
            if(option.equals("3")){
                AKIN.print_partition_value();

            }

            else if(option.equals("4")){
                System.out.println("Exiting...");
                System.exit(0);
            }

        }



    }
}
