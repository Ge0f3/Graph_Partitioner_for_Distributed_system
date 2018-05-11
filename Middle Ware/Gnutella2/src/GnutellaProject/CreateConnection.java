package GnutellaProject;
import java.util.*;
import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;
import java.net.Socket;

/**
*
* @author Dheeraj
*/
class CreateConnection extends Thread //Created class which extends the thread functionalities
{
    int portNumber;   //Initilization of the portNumber 
    ArrayList<Integer> myArray = new ArrayList<Integer>(); //Creating and arraylist  
    List<Integer> adjList; //Inititlization of the downloadfile
    Integer key;    //Initializastion of MessageId
    int fromPeerid,v2;
    int originateId;//Initialization of the fromPeerid
    
    public CreateConnection(int portNumber, List<Integer> adjList, Integer key,int fromPeerid,Integer v2,int originate) //CreateConnection Constructor
    {
        this.portNumber=portNumber;
        this.adjList=adjList;
        this.key=key;
        this.fromPeerid=fromPeerid;
        this.v2=v2;
        this.originateId = originate;
    }
   
	@SuppressWarnings("unchecked") //Indicates that the named compiler warnings should be suppressed in the annotated element 
       //(and in all program elements contained in the annotated element). 
	public void run()
    {
      Socket socket; //Initializing a socket variable
      ObjectOutputStream objectOS; //ObjectOutputStream - The objects can be read (reconstituted) using an ObjectInputStream
      ObjectInputStream objectIS;//ObjectInputStream is used to recover those objects previously serialized. 
       //Other uses include passing objects between hosts using a socket stream   
        try {
        	System.out.println("Calling portNumber"+ portNumber);
            socket = new Socket("localhost",portNumber); //Socket created with hostaddress and portnumber
            //ConnectionCreation and ClientAsServer are connected
            objectOS = new ObjectOutputStream(socket.getOutputStream());  //Returns an output stream for this socket
            objectIS = new ObjectInputStream(socket.getInputStream()); //Returns an input stream for this socket
           
            FileInfo message=new FileInfo(); //Creating an object for the File Info Class
            message.vetrex1=key;
            message.vertex2=v2;
            
            message.fromPeerId=fromPeerid;
            message.adjList = adjList;
            message.originateId = this.originateId;
            //Storing details in message
            
            objectOS.writeObject(message); //Writing msg object to ClientAsServer
            
            myArray= (ArrayList<Integer>) objectIS.readObject(); //Reading object from ClientAsServer 
        } //end of try block
         catch (IOException ex) 
         {
            Logger.getLogger(CreateConnection.class.getName()).log(Level.SEVERE, null, ex);
         }
         catch (ClassNotFoundException ex) 
          {
            Logger.getLogger(CreateConnection.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    public ArrayList<Integer> getArray()
    {
        return myArray;    //returns myArray with details of all peers that contains requested file 
    }    
}//END OF CREATECONNECTION CLASS
