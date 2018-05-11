class JavaCmd
{
    public static void main(String[] args)
    {
        try
        {
            // Just one line and you are done ! 
            // We have given a command to start cmd
            // /K : Carries out command specified by string
			String PATH_OF_FILE = "C:/Users/Dheeraj Cidda/Desktop/TTU/Advance Operating Systems/Programming Assignments/Gnutella-Style---Decentralized-Peer-to-Peer-File-Sharing-System-master/PA2_Decentralized_P2P_line/part_data";
           Runtime.getRuntime().exec("cmd /c cd "+PATH_OF_FILE+" & start cmd.exe /K \"dir && ping localhost\"");
 
        }
        catch (Exception e)
        {
            System.out.println("HEY Buddy ! U r Doing Something Wrong ");
            e.printStackTrace();
        }
    }
}
