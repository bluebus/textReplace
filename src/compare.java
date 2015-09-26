import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class compare{
	public static void main(String[] args) throws Exception 
	{	
		File subj = new File ("tweets_sentiment02.txt");
		File txt = new File ("C:/Users/Sentiment/workspace/parser/0627_sumResult_newSentiWord_limit5.csv");
		File write = new File("0627_compare_newSentiWord_limit5.csv");
		File error = new File("error_0627.txt");

		if (write.exists())
			write.delete();

		if(error.exists())
			error.delete();

		try {			
			BufferedReader reader01 = new BufferedReader(new InputStreamReader(new FileInputStream(subj), "UTF-8"));
			BufferedReader reader02 = new BufferedReader(new InputStreamReader(new FileInputStream(txt), "UTF-8"));
			PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(write, true)));
			String text01, text02;			
			output.println("Result,Sentiment_Parser, Sentiment_Tagged, ID, Text");
			ArrayList<String> id1 = new ArrayList<String>();
			ArrayList<String> id2 = new ArrayList<String>();
			ArrayList<String> sent1 = new ArrayList<String>();
			ArrayList<String> sent2 = new ArrayList<String>();
			ArrayList<String> content = new ArrayList<String>();

			while ((text01 = reader01.readLine()) != null) {
				String[] str = text01.split("\t");
				String sentiment = str[0];
				sent1.add(sentiment);
				String id = str[1];
				id1.add(id);

			}
			
			while ((text02 = reader02.readLine()) != null) {
				String[] str2 = text02.split(",");
				String sent = str2[0];
				sent2.add(sent);
				String ids = str2[1];					
				id2.add(ids);
				String text = str2[2];								
				content.add(text);				
			}

			for (int i=0; i<id1.size(); i++){
				for (int m=0; m < id2.size(); m++){
					if (id1.get(i).equals(id2.get(m))){
						if (sent2.get(m).equalsIgnoreCase(sent1.get(i)))
							output.println("1," + sent2.get(m) + "," + sent1.get(i) + "," + id1.get(i) + "," + content.get(m));
						else
							output.println("0," + sent2.get(m) + "," + sent1.get(i) + "," + id1.get(i) + "," + content.get(m));
					}
				}
			}
			output.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}