import java.io.*;							//Import Statements that are used to import the packages
import java.net.*;							// that are being used in the program
import java.util.Scanner;


class PortListenerSend implements Runnable {		//Implementing Runnable Interface
	int port;										//Declaration of Port
	public String inVal;							//Storing value of Input in String Format
	Boolean flag;                            
	ServerSocket server;							//Declaration of Socket 
	Socket con;								//Declaration of Connection
	BufferedReader br = null;						//Buffer Initialization

	public PortListenerSend(int port) {
		this.port = port;							//Port Value is being Assigned
		flag = true;								//Initial Idle state
		inVal = "Waiting For PEER Connection";	
	}
	
	public void run() {								
		try {										
			server = new ServerSocket(port);		//Declaration of Socket for Port that has been Assigned
			server.setReuseAddress(true);

			while (true) {                          //Listening for Download Request
				con = server.accept();		//Establishing a new Connection
				System.out.println("Connection Received From " + con.getInetAddress().getHostName()+" For Download\n");    				   				
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());		//Input Stream Object
				inVal = (String)in.readObject();			//Stores Input Value in strval

				ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());		//Output Stream Object
				out.flush();								//Clearing the Buffer

				String str="";

				try					
				{
					FileReader fr = new FileReader(inVal);                 //Reads the filename into File reader
					BufferedReader br = new BufferedReader(fr);				//Buffer Reader		
					String value=new String();								
					while((value=br.readLine())!=null)                //Appending the content read from the BufferedReader object until it is null and stores it in str
						str=str+value+"\r\n";                       
					br.close();												//Close buffer
					fr.close();												//Close file reader
				} catch(Exception e){										//Catch regular exception
					System.out.println("Cannot Open File");					
				}

				out.writeObject(str);							//Writes into the Buffer
				out.flush();									//Clearing the Buffer
				in.close();										//Closing the Input Stream	
				con.close();   							//Closing the Connection
			}
		} 

		catch(ClassNotFoundException noclass){                               //To Handle Exception for Data Received in Unsupported or Unknown Formats
			System.err.println("Data Received in Unknown Format");
		}
		catch(IOException ioException){                                      //To Handle Input-Output Exceptions
		} finally {
		}
	}
}

public class PeerServer {						//Client class - named as Peer Server 

	public String CIS_ip = "127.0.0.1";       //IP address of the Central Index Server So as to store the information about the Client
	String registrationmsg,searchfile;
	ObjectOutputStream out;						//Output Stream Object Declaration
	Socket requestSocket;						//Declaration of Socket

	public PeerServer() 						//Constructor
	{					
		System.out.println("Welcome to Peer 1 (Peer ID: 1001) Execution");

		while (true)
		{
			System.out.println("---------------------------------------------------------------------");
			System.out.println("Enter The Option :\n1. Registering the File \n2. Searching the file \n3. Downloading the file \n4. Exit\n");	
			System.out.println("---------------------------------------------------------------------");
			Scanner in = new Scanner(System.in);			
			registrationmsg = in.nextLine();						
			switch(registrationmsg){								
				case "1":										//Registration
					System.out.println("Enter the file name");  
					registrationmsg = in.nextLine();				
					File f = new File(registrationmsg);			//Declaring File Name in File Type
					if(f.exists())                         //Registers only if File Exists in the Directory
					{							
						RegistrationWithIndexServer(registrationmsg);                          //Register Method call is made
						FileDownloadRequest(1001);
					}
					else
						System.out.println("File not found!");
					break;
				case "2":
					SearchFileInServer();                            //Search Method call
					break;
				case "3":
					System.out.println("Exiting.");					
					System.exit(0);   								//Exit the system
				default:
					System.out.println("Invalid Option");
			}
		}
	}

	public static void main(String[] args)   //Declaration of Main function
	{				 
			PeerServer peerserverFrame = new PeerServer();				//New object Declaration
	}
	public void RegistrationWithIndexServer(String fname)                             //Registering with CentralIndxServer Method
	{
		try{
			requestSocket = new Socket(CIS_ip, 2001);           //Creating a Socket to connect to the server
			System.out.println("\nConnected to Register on CentralIndxServer on port 2001\n");
			out = new ObjectOutputStream(requestSocket.getOutputStream());  //To Get Input and Output streams
			StringBuilder sb = new StringBuilder();					//Append Strings
			sb.append("1001 ");										//Append Peer Id
			sb.append(fname);										//Append File Name
			String fs = sb.toString();								//Converting to String
			out.flush();											//Clearing Buffer
			out.writeObject(fs);									//Writing into Buffer
			out.flush();
			System.out.println("Registered Successfully!!\n");	
			
			File file = new File(fs);
			BufferedReader reader = null;
			reader = new BufferedReader(new FileReader(file));
			String text = null;
			Map<Integer, List<Integer>> data_set = new HashMap<Integer, List<Integer>>();
			while ((text = reader.readLine()) != null) {
				String[] text_split = text.split("\\s+");
				List<Integer> val_list = new ArrayList<Integer>();
				val_list.add(Integer.parseInt(text_split[1].trim()));
				val_list.add(Integer.parseInt(text_split[2].trim()));
				data_set.put(Integer.parseInt(text_split[0].trim()), val_list);
			}
		}
		catch(UnknownHostException unknownHost){                                  //To Handle Unknown Host Exception
			System.err.println("Cannot Connect to an Unknown Host!");
		}
		catch(IOException ioException){                                           //To Handle Input-Output Exception
			ioException.printStackTrace();
		} 
		finally{  //Closing connection
			try{
				out.close();
				requestSocket.close();
			   }
			catch(IOException ioException)
			{
				ioException.printStackTrace();
			}
		}

	}
	public void SearchFileInServer()                              //Search on the CentralIndexServer Method
	{
		try{
			System.out.println("Enter the File Name to Search");
			Scanner inFile = new Scanner(System.in);                       //Takes Input from the Peer to Search the Desired File
			searchfile = inFile.nextLine();

			requestSocket = new Socket(CIS_ip, 2002);	  //Creating a socket to connect to the Index server for port 2002																																																			
			System.out.println("\nConnected to Search on CentralIndxServer on port 2002\n"); //To Get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();											//Clearing the Buffer
			out.writeObject(searchfile);                                            //Writes the Search Filename to the Output Stream
			out.flush();											//Clearing the Buffer
			ObjectInputStream in = new ObjectInputStream(requestSocket.getInputStream());
			String strVal = (String)in.readObject();
			if  (strVal.equals("File Not Found\n"))          //  For File Not Found Print Condition 
			{
				System.out.println("FILE Does Not Exist !!\n");
			}
			else {
				System.out.println( "File:'"+searchfile+ "' found at peers:"+strVal+"\n");     
			}		

		}
		catch(UnknownHostException unknownHost){                                           //To Handle Unknown Host Exception
			System.err.println("Cannot Connect to an Unknown Host!");
		}
		catch(IOException ioException){                                                    //To Handle Input-Output Exception
			ioException.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{  //4. Closing Connection
			try{
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}

	
	public void FileDownloadRequest(int peerid)                                //FileDownload Request Thread   
	{
		Thread dthread = new Thread (new PortListenerSend(peerid));
		dthread.setName("AttendFileDownloadRequest");
		dthread.start();
	}
}
