package GnutellaProject;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class ClientAsServer extends Thread //Created class which extends the thread functionalities
{
	ArrayList<Integer> myArray = new ArrayList<Integer>(); //Creating and arraylist
    int currentPortId; //Initilization of the current portNumber 
    String directoryPath; //Initilization of the path of the directory with the peerid
    int peerid; //Initilization of the peer id 
    static ArrayList<String> transferMsgs; //Creating an arraylist of transfer messages
    String configFile;
	static  Map<Integer, List<Integer>> data_set = new HashMap<Integer, List<Integer>>();

    public ClientAsServer(int curtPeerPortid, String directoryPath, int peerid, String configFile) //Constructor
    {
        this.currentPortId=curtPeerPortid;
        this.directoryPath=directoryPath;
        this.peerid=peerid;
        this.configFile = configFile;
      
    }
    @SuppressWarnings("resource")
	public void run()
    {
        ServerSocket serverSocket=null; //Creating a server socket and initializing it to null
        Socket socket=null;   // Creating a socket and initializing it to null            
        try 
         {
           serverSocket = new ServerSocket(currentPortId); //Creating a server socket with current port id
         } 
         catch (IOException ex) 
         {
            Logger.getLogger(ClientAsServer.class.getName()).log(Level.SEVERE, null, ex);
         }
        while(true)
        {
          try 
           {        	
             File fXmlFile = new File(configFile);
             DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             Document doc = dBuilder.parse(fXmlFile);
             doc.getDocumentElement().normalize();
             NodeList nList = doc.getElementsByTagName("Peers");
            			
             socket = serverSocket.accept();//Connection establishment between neighbour client and peerid server
            
             HandleServer handleServer=new HandleServer(socket, peerid,nList,data_set);
             
             handleServer.start();
             //Starting handleServer class thread where search and downloading of file operations are performed
              
           }
            catch (IOException | ParserConfigurationException | SAXException ex) 
            {
                Logger.getLogger(ClientAsServer.class.getName()).log(Level.SEVERE, null, ex);
            }         
        }//End of while
    }//End of run
}//End of Client as Server Class