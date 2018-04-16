import java.io.BufferedReader;			
import java.io.IOException;				//Import Statements that are used to import the packages
import java.io.ObjectInputStream;	    // that are being used in the program
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ServerPortListener implements Runnable {				//Implementing the runnable interface

	ServerSocket server;									//Declaration of Socket
	Socket con;										//Declaration of Connection 
	BufferedReader br = null;								//Initialization of Buffer 
	Boolean flag;											//Used to store the state of Listener
	public String inValue;									//Used to store Input Value 
	int port;												//Declaration of port 
	static int size = 0;									//Initialization of maxsize of the array
	static Start[] ServerIndexArray = new Start[9000];           //ArrayList Initialization

	public ServerPortListener(int port) {					//Constructor
		this.port = port;									//Port Value is being Assigned
		flag = true;										//Initial Idle state
		inValue = "Waiting For PEER Connection";				
	}

	public void run() {										//Run method for the Interface
		if(port==2001)                                      //Listening For Register on port 2001
		{
			try {											
				server = new ServerSocket(2001);			//Declaration of Socket for port 2001
				while (true) {								
					con = server.accept();			//Establishing a new Connection
					System.out.println("Connection Received From " +con.getInetAddress().getHostName()+ " For Registration");    				   				
					ObjectInputStream in = new ObjectInputStream(con.getInputStream());				//Input Stream Object
					inValue = (String)in.readObject();												
					System.out.println(inValue);
					System.out.println("Registered\n");
					String[] var;						        //Declaring Array of strings
					var = inValue.split(" ");			        //Splitting the strval and Storing in var
					int aInt = Integer.parseInt(var[0]);		//Conversion of String to Int
					String ipstrtmp = con.getInetAddress().getHostName();	//Ip Address of the Client is being Received
					for(int x = 1; x < var.length ; x++){
						Start myitem = new Start();								//Declaring Array list
						myitem.filename = var[x];                              //Storing Peer ID, Filename and Ip Address in the ArrayList       
						myitem.peerid = aInt  ;
						myitem.ipAddress = ipstrtmp;
						ServerIndexArray[size] = myitem;
						size++;
					}

					in.close();													//Closing the Input Stream
					con.close();   									    //Closing the Connection
				}
			} 

			catch(ClassNotFoundException noclass)
			{                             //To Handle Exceptions for Data Received in Unsupported/Unknown Formats
				System.err.println("Data Received in Unknown Format");
			}
			catch(IOException ioException)
			{                                     //To Handle Input-Output Exceptions
				ioException.printStackTrace();
			} 
			finally{}

		}
		if(port==2002)                                //Listening for Search on port 2002
		{
			try {
				server = new ServerSocket(2002);		//Declaration of Socket for port 2002
				while (true) {
					con = server.accept();			
					System.out.println("Connection Received From " +con.getInetAddress().getHostName()+ " For Search");    				   				
					ObjectInputStream in = new ObjectInputStream(con.getInputStream());		  					   //Input Stream Object
					inValue = (String)in.readObject();
					String returnvalue = "";        //	Peer-id's are separated by space and are returned for given file
					for (int idx =0; idx < size ;idx++)                                      //Traversing the ArrayList  
					{                
					   if (ServerIndexArray[idx].filename.equals(inValue))         //To Compare the Filename with the Registered filenames in the ArrayList
						{
							returnvalue = returnvalue + ServerIndexArray[idx].peerid + "("+ServerIndexArray[idx].ipAddress +")\n\r "; //Returns the list of Peerid's which has the searched file      
						}	
					} 
					if (returnvalue == "") 				//File not found case
					{
						returnvalue = "File Not Found\n";
					} 
					System.out.println(returnvalue);
					System.out.println("Searched\n");

					ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());		//Output Stream Object
					out.flush();									//Clearing the Buffer
					out.writeObject(returnvalue);                        //Writing the List of peer id's to the Output Stream
					out.flush();									//Clearing the Buffer
					in.close();										//Closing the Input Stream
					out.close();									//Closing the Output Stream
					con.close();   							 //Closing the Connection
				}
			} 

			catch(ClassNotFoundException noclass){                               //To Handle Exceptions for Data Received in Unsupported/Unknown Formats 
				System.err.println("Data Received in Unknown Format");
			}
			catch(IOException ioException){                                       //To Handle Input-Output Exceptions
				ioException.printStackTrace();
			} finally {
			}

		}		
	}
}