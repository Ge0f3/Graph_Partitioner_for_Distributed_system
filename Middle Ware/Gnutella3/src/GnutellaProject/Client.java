package GnutellaProject;
import java.util.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Dheeraj
 */
public class Client
{  
   
    private static String peerName;		//Used for storing Peer Names			
	private static String neighbours;   //Used for storing the Neighbours of the peer
	private static int curtPeerPortid;  //Used for storing the current Peer's Port
	static  Map<Integer, List<Integer>> data_set = new HashMap<Integer, List<Integer>>();
	static NodeList nList;
	static int peerid=3;
   public static void main(String args[]) throws ClassNotFoundException
    {
       String path=null;   //Path of directory of the peers containing the files;
        
        int originateId;// Peer ID
       File fXmlFile;
       String diffConfig;
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Reads text from a character-input stream, buffering characters so as 
                                   
       //to provide for the efficient reading of characters, arrays, and lines. 
       																			 // creating bufferreader to take inputs
        try {
        	 
        	int choice;
        	  
           diffConfig = "C:/Users/Dheeraj Cidda/eclipse-workspace/Gnutella3/configStar.xml";
        	 
        		 
        	 fXmlFile = new File(diffConfig);
        	 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); //DocumentBuilderFactory is a Protected constructor to 
 			 	
        	 DocumentBuilder dBuilder; 
        	 dBuilder = dbFactory.newDocumentBuilder();
        	 Document doc = dBuilder.parse(fXmlFile);
        	 doc.getDocumentElement().normalize();
        	             
            System.out.println("Gnutella style peer-to-peer star and mesh topologies");
            System.out.print("peerid:" + peerid);
            originateId=peerid;//stores peerid
            System.out.println("peer "+peerid+" neighbours and their respective port numbers are: ");
            
            //Returns a NodeList of all the Elements in document order with a given tag name and are contained in the document
            nList = doc.getElementsByTagName("Peers");
            
            for (int i = 0; i < nList.getLength(); i++) 
    		{
          	 Node nNode = nList.item(i); // storing each item of nodeList in nNode
    	          if (nNode.getNodeType() == Node.ELEMENT_NODE)  //Node.ELEMENT_NODE is An Element node such as <p> or <div>.
    		      {
    			     Element element = (Element) nNode;//The Element object represents an element in an XML document.Elements may contain attributes, other elements,
    			     //or text.If an element contains text, the text is represented in a text-node. Here we type cast into type element since nNode is of type Node
    			   
    			    //storing attributes of a peer
    			    peerName = element.getElementsByTagName("PeerName").item(0).getTextContent();  //gets the servername  from the element
    		        
    			   if(peerName.equalsIgnoreCase("peer"+peerid)) //we check the peer name regardless of the case of the text with the user input
    			    {
    				  curtPeerPortid = Integer.parseInt(element.getElementsByTagName("PeerPort").item(0).getTextContent()); // gets the port address from the element
    				  neighbours = element.getElementsByTagName("PeerNeighbours").item(0).getTextContent(); //gets the neighbours from the element
    				  String[] peers=null; //Creating a string array to store the neighbours of the peer
    		          if(neighbours!=null) //if the neighbours are not null
    		            {
    		               peers=neighbours.split(","); //separating neighbours using "," and stored in an array
    		               for(int j=0;j<peers.length;j++)  //looping through the values of the peers
    		                {
    		                  System.out.print(peers[j]);  // printing out the peerid
    		                  System.out.print(" ");
    		                  int id=Integer.parseInt(peers[j]); // Converting the value of the peers to integer
    		                  int parseInt = getPort(id,nList);  // creating a variable for storing the port number which is returned by the function getPort()
    		                  System.out.println(parseInt); //displaying neighbours port-numbers
    		                }//end of for i.e., looping of the peers
    		            }//end of if for checking neighbours equal to null or not
    		         }//end of if for checking the peer name
    		      }//end of if for checking the Element node 
    		   }//end of for in looping through the nList
            
                      
               path = "C:/Users/Dheeraj Cidda/eclipse-workspace/Gnutella3/Peer"+Integer.toString(peerid);
              
               new ClientAsServer(curtPeerPortid,path,peerid,diffConfig).start(); 
               //Starting ClientAsServer thread so that it will serve other peer requests while requesting from this peer       
             
               String filename = "partition"+Integer.toString(peerid)+".txt";
               download(peerid, filename, path);
               storeData(data_set, path+"/"+filename, peerid);
               
               Map.Entry<Integer, List<Integer>> entry = data_set.entrySet().iterator().next();
               Integer key= entry.getKey();
               List<Integer> value=entry.getValue();
               List<Integer> adjList=new ArrayList<Integer>();
               adjList=entry.getValue();//new ArrayList<Integer>();
               
               int v2 = 5;
              System.out.println("Do you want to perform BFS");
              
              choice = Integer.parseInt(br.readLine());
              if(choice==1)
               {
            	  BFS(key,v2,peerid,originateId,adjList);
               }   
                  
        } //End of the Try Block
            catch (IOException| ParserConfigurationException | SAXException  ex) 
            {   
              //A Logger object is used to log messages for a specific system or application component	
              Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } // End of catch Block
        
    }//End of the Main
  
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
   public static void BFS(Integer key, int v2, int peerid,int originateId,List<Integer> adjList) {
		// TODO Auto-generated method stub
	   Map.Entry<Integer, List<Integer>> entry = data_set.entrySet().iterator().next();
       //Integer key= entry.getKey();
	   System.out.println();
       List<Integer> value= data_set.get(key);
       int j=0;
       List<Integer> notFoundindataset = new ArrayList<Integer>();
       List<Integer> visited = new ArrayList<Integer>();
       
	   
		 for(j=0;j<value.size();j++)
		   {
			   if(value.get(j)==v2)
			   {
				   System.out.println("Found the Vetex and BFS is done");
			   }
			   else
			   {
				 //System.out.println(value.get(j));
	  	  			try {
	  			        if(data_set.containsKey(value.get(j)))
			            {
	  			           //System.out.println(value.get(j));	
	  			        	 adjList.addAll(data_set.get(value.get(j)));
	  			        	 visited.add(value.get(j));
			            System.out.println(visited.get(j));  
			            }
	  			       
	  			        else
	  			        	{
	  			        	notFoundindataset.add(value.get(j));
	  			        	//System.out.println("Network Call");
	  			        	ArrayList<CreateConnection> connectionThreads = new ArrayList<CreateConnection>(); //Array list for creating the threads to run peers 
	  			            ArrayList<Thread> listofThreads = new ArrayList<Thread>();    //Array List for storing all the threads        

	  			        	 String[] npeers=neighbours.split(","); //separating neighbours using "," and stored in an array
	  			               for(int p=0;p<npeers.length;p++)    //looping through the values of the peers
	  			              {
	  			       	    int peer=Integer.parseInt(npeers[p]); // Converting the value of the peers to integer
	  			       	    int neighbourPortNumber = getPort(peer,nList);  // creating a variable for storing the port number which is returned by the function getPort()
	  			              //System.out.println("Port in search function: " + neighbourPortNumber);
	  			              CreateConnection createConnection = new CreateConnection(neighbourPortNumber,adjList,value.get(j),peerid,v2,originateId);
	  			              //Establishing connection to CreateConnection class
	  			              
	  			              Thread thread = new Thread(createConnection); //Creating single thread for each neighbour so that we can run them independently
	  			             
	  			              thread.start(); //Starting thread for each neighbour
	  			              listofThreads.add(thread);  //Storing thread instances in threadList  
	  			              connectionThreads.add(createConnection); //Storing connectionEstablishment instances in connectionThreads
	  			              
	  			            }
	  			               break;
	  			               
	  			        	}
	  	  			}
	  	  			catch(Exception ex)
	  	  			{
	  	  				System.out.println(ex);
	  	  			}
	  	  		}
			}
		
		
				   
		   
}
	      
		
	
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static int getPort(int id, NodeList nList) //GET PORT FUNCTION
	{
    	int neighbourport = 0; //Used for storing the port Number
     	for (int a = 0; a < nList.getLength(); a++) //Looping through the nList which contains the XML elements
		  {
        	Node pNode = nList.item(a); // storing each item of nodeList in nNode
 	        if (pNode.getNodeType() == Node.ELEMENT_NODE)  //Node.ELEMENT_NODE is An Element node such as <p> or <div>.
			  {
				Element pelement = (Element) pNode;//The Element object represents an element in an XML document.Elements may contain attributes, other elements,
			     //or text.If an element contains text, the text is represented in a text-node. Here we type cast into type element since pNode is of type Node
 			   
				peerName = pelement.getElementsByTagName("PeerName").item(0).getTextContent(); //Getting the peer name and storing it
				if(("peer"+id).equalsIgnoreCase(peerName)) //Checks if the peer"1" equals to peerName i.e., peer1 etc.. 
				  {
				    neighbourport = Integer.parseInt(pelement.getElementsByTagName("PeerPort").item(0).getTextContent()); //Stores the portnumber of the peerid
				  }//end of if for checking peer name
			   }//end of if for checking the ELEMENT
		  }//end of the for loop through the nList
 	  //System.out.println("Neighbours Port of" +id + "is : "+neighbourport); 
		return neighbourport; // returning the portnumber
	}//end of the GET PORT FUNCTION
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void download(int peerid, String downloadFile, String path) throws ClassNotFoundException, IOException
    {   
		InputStream is = null;
        OutputStream os = null;
     try 
      {
        is = null;
        os = null;
        is = new FileInputStream(downloadFile);
        os = new FileOutputStream(path+"/partition"+Integer.toString(peerid)+".txt");
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0)
            os.write(buffer, 0, length);
    	 
      } // end of try block
       catch (IOException ex) 
        {
         Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }//end of catch block
     finally {
  	   is.close();
  	   os.close();
     }
    }//END OF DOWNLOAD FUNCTION
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public static void storeData(Map<Integer, List<Integer>> data_set, String file, int peerid) throws IOException{
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;
			while((text = reader.readLine())!= null) {
				String[] text_split = text.split("\\s+");
				int key = Integer.parseInt(text_split[0].trim());
				int value = Integer.parseInt(text_split[1].trim());
				if(data_set.get(key) != null) {
					List<Integer> abc = data_set.get(key);
					abc.add(value);
					data_set.put(key, abc);
				}
				else {
					List<Integer> abc = new ArrayList<Integer>();
					abc.add(value);
					data_set.put(key, abc);
				}
			}
		}
		catch(Exception e) {
		}
		finally {
			reader.close();
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public static int myPeer()
	{
		return peerid;
	}
}  
