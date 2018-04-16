package GraphAlgorithm;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    Scanner sc=new Scanner(System.in);
    Map<String,String> graph =new HashMap<String, String>();

    Main(){
        System.out.println("This is the main function");
//        String option = " ";
//        while(true){
//            if(option.equals("3")){
//                System.out.println("Exiting the program !!!");
//                System.exit(0);
//            }
//            System.out.printf("Your option is %s \n",option);
//            System.out.println("Graph Partition Algorithms \nEnter your choice \n1.AKIN \n2.IOGP\n3.To Exit");
//            option = sc.nextLine();
//
//
//        }

    }




    public static void main(String[] args) {
	// write your code here
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Main M = new Main();
        FileOp FO=new FileOp();
        FO.ReadFile();




    }
}
