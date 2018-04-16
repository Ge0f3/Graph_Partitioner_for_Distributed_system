import java.io.*;							//Import Statements that are used to import the packages
import java.net.*;							// that are being used in the program
import java.util.Scanner;


class PortListenerSend2 implements Runnable {		//Implementing Runnable Interface
	int port;										//Declaration of Port
	public String inVal;							//Storing value of Input in String Format
	Boolean flag;                            
	ServerSocket server;							//Declaration of Socket 
	Socket con;								//Declaration of Connection
	BufferedReader br = null;						//Buffer Initialization
	
	public PortListenerSend2(int port) {
		this.port = port;							//assign current port to port listener
		flag = true;								//Initial Idle state
		inVal = "Waiting For PEER Connection";	
	}
	
	public void run() {							
		try {										
			server = new ServerSocket(port);		//Declaration of Socket for Port that has been Assigned

			while (true) {                           //Listening for Download Request
				con = server.accept();		  //Establishing a new Connection
				System.out.println("Connection Received From " + con.getInetAddress().getHostName()+" For Download\n");    				   				
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());		//Input Stream Object
				inVal = (String)in.readObject();			//Stores Input Value in strval

				ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());		//output stream object
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

		catch(ClassNotFoundException noclass){                              //To Handle Exception for Data Received in Unsupported or Unknown Formats
			System.err.println("Data Received in Unknown Format");
		}
		catch(IOException ioException){                                    //To Handle Input-Output Exceptions
		} finally {
		}
	}
}

public class PeerServer2 {						//Client class - named as Peer Server2

	public String CIS_ip = "127.0.0.1";        //IP address of the Central Index Server So as to store the information about the Client
	String registrationmsg,searchfile;
	ObjectOutputStream out;						//Output Stream Object Declaration
	Socket requestSocket;						//Declaration of Socket

	public PeerServer2() {						//constructor

		System.out.println("Welcome to Peer 3 (Peer ID: 1003) Execution");

		while (true){
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
					AttendFileDownloadRequest(1001);
				}
				else
					System.out.println("File not found!");
				break;
				case "2":
					SearchFileInServer();                            //Search Method call
					break;
				case "3":
					FileDownloadFromPeerServer();                       //Download Method call
					break; 
				case "4":
					System.out.println("Exiting.");					
					System.exit(0);   								//Exit the system
				default:
					System.out.println("Invalid Option");
			}
		}
	}

	public static void main(String[] args)	 //Declaration of Main function
	{				
		PeerServer2 peerserver2Frame = new PeerServer2();			//New object Declaration

	}
	public void RegistrationWithIndexServer(String fname)                             //Register with CentralIndxServer Method
	{
		try{
			requestSocket = new Socket(CIS_ip, 2001);           //Creating a Socket to connect to the server
			System.out.println("\nConnected to Register on CentralIndxServer on port 2001\n");
			out = new ObjectOutputStream(requestSocket.getOutputStream());  //To Get Input and Output streams
			StringBuilder sb = new StringBuilder();					//Append Strings
			sb.append("1003 ");										//Append Peer Id
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
		catch(UnknownHostException unknownHost){                                             //To Handle Unknown Host Exception
			System.err.println("Cannot Connect to an Unknown Host!");
		}
		catch(IOException ioException){                                                      //To Handle Input-Output Exception
			ioException.printStackTrace();
		} 
		finally{    //Closing connection
			try{
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}

	}
	public void SearchFileInServer()                              //Search on the CentralIndexServer Method
	{
		try{
			System.out.println("Enter the File Name to Search");
			Scanner inFile = new Scanner(System.in);                                        //Takes Input from the Peer to search the desired file
			searchfile = inFile.nextLine();
			requestSocket = new Socket(CIS_ip, 2002);		 //Creating a socket to connect to the Index server for port 2002																																																																						
			System.out.println("\nConnected to Search on CentralIndxServer on port 2002\n");   //To Get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();											//Clearing the Buffer
			out.writeObject(searchfile);                                            //Writes the Search Filename to the Output Stream
			out.flush();											//Clearing the Buffer
			ObjectInputStream in = new ObjectInputStream(requestSocket.getInputStream());
			String strVal = (String)in.readObject();
			if  (strVal.equals("File Not Found\n"))   //  For File Not Found Print Condition 
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
		finally{ //Closing connection
			try{
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}

	public void writetoFile(String s)
	{
		try
		{  	String fname = searchfile;        //To Append String s to Existing File
			FileWriter filewrite = new FileWriter(fname,true);
			filewrite.write(s);                                      //Write to file, the contents
			filewrite.close();
		} catch(Exception e){
			System.out.println("Cannot Open File");      // To Mask Print on Console
		}
	}

	public void FileDownloadFromPeerServer()                            //Download Function Method 
	{	System.out.println("Enter Peer id:");                       
		Scanner inputpeerId = new Scanner(System.in);                  //Takes input form the user of the 4Digit Peer ID format 
		String peerid = inputpeerId.nextLine();
		System.out.println("Enter pear IP Address to download file:");	//enter ip address of the peer
		String ipadrs = inputpeerId.nextLine();
		System.out.println("Enter the File Name to be Downloaded:");      
		searchfile = inputpeerId.nextLine();                              //Takes from user the desired filename to be downloaded
		int peerid1 = Integer.parseInt(peerid);					//convert peer id into integer
		try{
			requestSocket = new Socket(ipadrs, peerid1);      //Creating a socket to connect to the Index server
			System.out.println("\nConnected to peerid : "+peerid1+"\n"); 			//To Get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();			   										//Clearing the Buffer
			out.writeObject(searchfile);
			out.flush();													//Clearing the Buffer
			ObjectInputStream in = new ObjectInputStream(requestSocket.getInputStream());
			String strVal = (String)in.readObject();
			System.out.println( searchfile+": Downloaded\n");
			writetoFile(strVal);
		}
		catch(UnknownHostException unknownHost){                                             //To Handle Unknown Host Exception
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){                                                      //To Handle Input-Output Exception

			ioException.printStackTrace();
			System.err.println("FILE not Found at the Following PEER !!");      
			System.err.println("Please enter a valid PEER ID!");      // To Inform User and Avoid StackTrace Print on Console 
			FileDownloadFromPeerServer();                    // Calling Download Function Again to enable user to enter valid Filename and Port Number
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{ // Closing Connection
			try{
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	public void AttendFileDownloadRequest(int peerid)                                //FileDownload Request Thread   
	{
		Thread dthread = new Thread (new PortListenerSend2(peerid));
		dthread.setName("AttendFileDownloadRequest");
		dthread.start();
	}
}
