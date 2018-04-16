import java.io.*;
import java.util.*;

public class ABC{

public static void main(String[] args){

	try{
	File file = new File("out.moreno_health_health");
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