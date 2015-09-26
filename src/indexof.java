import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;


public class indexof{
	
	public static ArrayList<String> subject()    {
		ArrayList<String> Array = new ArrayList<String>();
		String text;
		
		try {	 
			BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Sentiment/workspace/textProcess/lib/text/subject.txt"));
					
			while ((text = reader.readLine()) != null) {//read subject			
				Array.add(text);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return(Array);
	}
	public static Map<Integer, String> map = new HashMap<>();
	public static ArrayList<String> sentence = new ArrayList<String>();
	
	public static String deletek(String str){
		ArrayList<String> Subjects = subject();
		String line[] = {};
		String text="";
				
		line = str.split(" ");
		sentence = new ArrayList<String>(Arrays.asList(line));
		
		for (int i=0; i<sentence.size(); i++){
			for (int a=0; a<Subjects.size();a++){
				String subj = Subjects.get(a);
				String target = sentence.get(i);
				if (target.equalsIgnoreCase(subj)){
					map.put(i, target);
					sentence.remove(i);
				}				
			}
			text = text +sentence.get(i) + " ";
		}
		System.out.println("After remove: "+ sentence);
		
		return text;
	}
	
	public static String insertk(String str){
		int length = 0;
		Iterator<Map.Entry<Integer, String>> entries = map.entrySet().iterator();
		String text ="";

		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			int key = Integer.valueOf(entry.getKey().toString());					
			String value = entry.getValue().toString();
			key += length;
			sentence.add(key, value);
			length += 1;
		}	
		
		for (int i=0; i<sentence.size(); i++){
			text = text +sentence.get(i) + " ";
		}
		
		System.out.println("After add in: " + sentence);
		return text;
	}
	
	
	public static void main(String[] arg){
		String str = "Unifi u r so suck , i wan potong Unifi , celcom u shit , no more umobile broadband";		
		System.out.println(map);		
		str = deletek(str);
		System.out.println(str);
		str = insertk(str);
		System.out.println(str);
		System.out.println(map);
		map.clear();
		
		String str01 = "Unifi u so suck , but i like hypptv , celcom ? nvm umobile broadband la";
		System.out.println(map);		
		str = deletek(str01);
		System.out.println(str01);
		str = insertk(str01);
		System.out.println(str01);
		System.out.println(map);
		map.clear();
	
	}
	
}