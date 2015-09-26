import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class multipleSubject{
	
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
	
	static class data {
	    String a;
	    String b;
	    int c;

	    //public data(String a, String b, int c) {
	    public data(String a, String b, int c) {
	        this.a = a;
	        this.b = b;
	        this.c = c;
	    }
	}

	
	public static void main(String[] arg){

		ArrayList<String> subject = subject();
		String line = "Maxis is better then Unifi . How about Celcom and Digi ?";
		//String[] text = line.split(" ");
		Map<String, Integer> map = new HashMap<String, Integer>();
		ArrayList<data> keyword = new ArrayList<data>();		
		ArrayList<String> sentence = new ArrayList<String>();
		
		
		
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		String source = line;
		iterator.setText(source);
		int start = iterator.first();
		for (int end = iterator.next(); end != BreakIterator.DONE;start = end, end = iterator.next()) {
			sentence.add(source.substring(start,end));
		}
		
		System.out.println(sentence);
		
		boolean found = false;
		int position = 0;
		
		for(int d = 0; d<sentence.size(); d++){
			String sline = sentence.get(d);
			sline = sline.replaceAll("[^0-9 a-z A-Z \\s+ , ']", "");
			String[] text = sline.split(" ");
			String object = "";
			//position = Arrays.asList(line).indexOf(keyword);
			for (int i=0; i< subject.size(); i++){
				object = subject.get(i);
				position = Arrays.asList(text).indexOf(object);
				if (position >=0 ){					
					map.put(object,  position);
					keyword.add(new data(object, sline, position));
					found = true;
				}			
			}
		}
		

		System.out.println(map);
		//System.out.println(keyword);
		
	}
}