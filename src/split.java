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



public class split //blank
{
	public static void main(String[] args) throws Exception 
	{	
		File subj = new File ("tweets_sentiment02.txt");
		File old = new File("_sumResult_0502.csv");
		File txt = new File ("newData01.txt");

		if (txt.exists())
			txt.delete();

		try {			
			BufferedReader reader01 = new BufferedReader(new InputStreamReader(new FileInputStream(subj), "UTF-8"));
			BufferedReader reader02 = new BufferedReader(new InputStreamReader(new FileInputStream(old), "UTF-8"));
			PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(txt, true)));
			String text01, text02;

			while ((text01 = reader01.readLine()) != null) {
				String[] text = text01.split("\t");
				String sent = text[0];
				String id = text[1];
				String content = text[2];
				
				while ((text02 = reader02.readLine()) != null) {
					
				}
				
			}
			output.close();
			reader01.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}		
	}

}

