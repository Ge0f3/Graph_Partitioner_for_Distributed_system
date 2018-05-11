package GnutellaProject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.w3c.dom.NodeList;


import java.lang.String;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class HandleServer extends Thread
{
    ObjectOutputStream objectOS;  //ObjectOutputStream - The objects can be read (reconstituted) using an ObjectInputStream
    ObjectInputStream objectIS;//ObjectInputStream is used to recover those objects previously serialized.
    Socket socket;  //Initializing a socket variable
    String fileName; //Initializing a filename
    String path;  //Initializing a path
    int peerid;//Initializing peed id
   ArrayList<Integer> myArray = new ArrayList<Integer>();   //Creating and arraylist
    NodeList nList;// = new NodeList();
    ArrayList<CreateConnection> threadListCE = new ArrayList<CreateConnection>();  //Creating an arraylist of type Create Connection
    ArrayList<String> transferMsgs;
    int originateId;//Creating an arraylist
   
    public HandleServer(Socket socket,int peerid,NodeList nList) //Constructor
    {
        this.socket=socket;
        
        this.peerid=peerid;
        this.nList=nList;
     
        originateId= Client.myPeer();
        System.out.println(Client.myPeer());
        //Storing data
    }
    public void run()
    {
      try 
       {
         FileInfo msg;  //Creating a varaible msg of type FileInfo
         List<Integer> adjList = new ArrayList<Integer>();
         int v1,v2;
         objectOS = new ObjectOutputStream(socket.getOutputStream()); //Returns an output stream for this socket
         objectIS = new ObjectInputStream(socket.getInputStream()); //Returns an input stream for this socket
         synchronized(this) //Only one thread can execute inside a Java code block synchronized
         {
           msg = (FileInfo) objectIS.readObject();  
           adjList = msg.adjList;
           v1= msg.vetrex1;
           v2 = msg.vertex2;
           
           if(msg.originateId == peerid)
           {
        	   System.out.println("Search is Done");
           }
           else
           {
        	   System.out.println("Perform BFS on"+ msg.vetrex1 +","+ msg.vertex2);
        	  
        	   Client.BFS(msg.vetrex1,msg.vertex2,peerid,msg.originateId,adjList);
           }
        
           }//end of synchronization
         }//end of try block
         catch (IOException ex) 
          {
            Logger.getLogger(HandleServer.class.getName()).log(Level.SEVERE, null, ex);
          } 
         catch (ClassNotFoundException ex) 
          {
            Logger.getLogger(HandleServer.class.getName()).log(Level.SEVERE, null, ex);
          }
             }//end of run function
}//end of HandleServer Class