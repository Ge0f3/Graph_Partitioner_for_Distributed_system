class Start{										//Registration Information is Stored in this Class
	String filename;								//Declaration of FileName given as input by the user           
	int peerid;										//Declaration of PeerId 
	String ipAddress;								//Declaration of Ip Address of the Peer
}

public class CentralIndxServer 
   {					//Declaration of class where Server Initialization is done 

	public CentralIndxServer() 
	{					//Constructor
		RegisterRequestThread();                    //RegisterRequest Threads 
		SearchRequestThread();						// SearchRequest Threads
	}

	public static void main(String[] args) 			 //Declaration of Main Function
	{ 	   
		int port = 0;							// Initialization of the Port		
		System.out.println("Welcome to the Central Index Server Interface");	
		CentralIndxServer mainFrame = new CentralIndxServer();					//Declaring Object for the Central Index Server
	}																			
	
	public void RegisterRequestThread()											//Declaration of Function for Registration  
	{										
		Thread rthread = new Thread (new ServerPortListener(2001));       //Register Request Thread of the type ServerPortListener with specified port number
		rthread.setName("Listen For Register");									//Assigning the name for Thread
		rthread.start();														//Starting the Thread
	}
	
	public void SearchRequestThread()											//Declaration of Function for Searching
	{											 
		Thread sthread = new Thread (new ServerPortListener(2002));        //Search Request Thread of the type ServerPortListener with specified port number
		sthread.setName("Listen For Search");									//Assigning the name for the Thread 
		sthread.start();														//Starting the Thread
	}
}

