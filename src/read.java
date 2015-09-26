import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;



public class read //test code to split down result set into folders
{


	public static void main(String[] args) throws Exception 
	{	
		ArrayList<String> oripv = new ArrayList<String>();

		File subj = new File ("1112_emoticon.txt");
		File vb = new File ("emoticon_list.txt");
		String text01;

		if (vb.exists())
			vb.delete();

		try{
			BufferedReader reader01 = new BufferedReader(new InputStreamReader(new FileInputStream(subj), "UTF-8"));
			PrintWriter emo = new PrintWriter(new BufferedWriter(new FileWriter(vb, true)));
			ArrayList<String> array = new ArrayList<String>();
			int a = 1;

			while ((text01 = reader01.readLine()) != null) {
				System.out.println(text01);
				String text = text01.replaceAll("[\\s]", "");

				if (array.isEmpty()){
					array.add(text);
					System.out.println("added - " + a);
				}
				else{
					boolean check = false;
					
					for (int i = 0; i<array.size(); i++){	
						if (!check){
							if (text.equalsIgnoreCase(array.get(i))){
								check = true;
							}
							else check = false;
						}
					}
					
					if (!check){
						a+=1;
						array.add(text);
						System.out.println("added - " + a);
					}
						
				}
			}			

			for (int i=0; i<array.size(); i++){
				emo.println(array.get(i));
			}

			emo.close();

		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

