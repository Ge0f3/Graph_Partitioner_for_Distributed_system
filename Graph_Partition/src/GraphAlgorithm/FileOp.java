package GraphAlgorithm;

import java.io.File;
import java.util.Scanner;

public class FileOp {
    Graph grap = new Graph(349);
    FileOp(){
        System.out.println("This is a to do file operation");
    }
    public void ReadFile(){
        Scanner F = null;


        try{
            F = new Scanner(new File("text_file.txt"));
        }
        catch (Exception e){
            System.out.println("Couldn't Find the find the file ");
        }
        while(F.hasNext()){
            String edge1 = F.next();
            String edge2 = F.next();
            int src = Integer.parseInt(edge1);
            int des = Integer.parseInt(edge2);
            grap.addEdge(grap,src,des);
            //System.out.printf("%s is connected to %s \n",edge1,edge2);

        }

        System.out.println("Printing the graph adjacency List");
        grap.printGraph(grap);
    }
}
