import java.io.*;
import java.util.*;

class StoreData{
	public static void main(String[] args){
		
		String PATH_OF_FILE = "C:/Users/Dheeraj Cidda/Desktop/TTU/Advance Operating Systems/Programming Assignments/Gnutella-Style---Decentralized-Peer-to-Peer-File-Sharing-System-master/PA2_Decentralized_P2P_line/part_data";
		
		int peerno = 0;
			
	try{
			File file = new File(PATH_OF_FILE+"/par"+peerno+".txt");
			BufferedReader reader = null;
			reader = new BufferedReader(new FileReader(file));
			String text = null;
			Map<Integer, List<Integer>> data_set = new HashMap<Integer, List<Integer>>();
			while ((text = reader.readLine()) != null) {
				String[] text_split = text.split("\\s+");
				List<Integer> val_list = new ArrayList<Integer>();
				for(int i=0; i<text_split.length; i++)
					val_list.add(Integer.parseInt(text_split[i].trim()));
				data_set.put(Integer.parseInt(text_split[0].trim()), val_list);
			}
			for(Integer key: data_set.keySet()){
				List<Integer> abc = data_set.get(key);
				System.out.print(key + " ");
				for(int i=0; i<abc.size();i++)
					System.out.print(abc.get(i) + " ");
				System.out.println();
			}
	}
	catch(Exception e){
		System.out.println(e);
	}
	
	
	
	}
}