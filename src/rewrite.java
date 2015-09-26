import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;



public class rewrite //test code to split down result set into folders
{
	public static void main(String[] args) throws Exception 
	{	
		File subj = new File ("twitter_Positive.txt");
		int count = 0;
		
		try {			
			BufferedReader reader01 = new BufferedReader(new InputStreamReader(new FileInputStream(subj), "UTF-8"));
			//PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(txt, true)));
			
			String text01, text02;
			//ArrayList<String> sentiment = new ArrayList<String>();
			
			while ((text01 = reader01.readLine()) != null) {			
				/*String[] array = text01.split("\t");
				String content = "";
				for (int i=1; i<array.length; i++){
					content = content + array[i];
				}*/
				
				//String name = content.replaceAll("[^0-9 A-Z a-z]", "");		
				//integer.add(count);
								
				/*if (text01.contains("neutral")){	
					File txt = new File ("1twitter//neutral//" + count + ".txt");
					if (txt.exists())
						txt.delete();
					PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(txt, true)));
					output.print(text01);
					output.close();*/
					
					
				/*}else if (text01.contains("negative")){
					File txt = new File ("1twitter//negative//" + count + ".txt");
					if (txt.exists())
						txt.delete();
					PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(txt, true)));
					output.print(text01);
					output.close();*/
					
				/*}else if (text01.contains("positive")){	*/			
					File txt = new File ("1twitter//positive//" + count + ".txt");
					if (txt.exists())
						txt.delete();
					PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(txt, true)));
					output.println(text01);
					output.close();
				/*}
				else
					System.out.println("Error: " + id);*/
				
				count++;
			}
			
			
			System.out.println(count);
			
			reader01.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

}

