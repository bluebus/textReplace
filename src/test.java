import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class test{
	public static void main (String[] args) throws Exception {

		File subj = new File ("file02_noStem.txt");

		try {			
			BufferedReader reader01 = new BufferedReader(new InputStreamReader(new FileInputStream(subj), "UTF-8"));
			String text01 = "";
			Set<String> list = new HashSet<String>();
			
			File txt = new File ("input_noStem.txt");
			if (txt.exists())
				txt.delete();
			PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(txt, true)));

			while ((text01 = reader01.readLine()) != null){
				String text02 = text01.replaceAll("_", " ");
				String text03 = text02.replaceAll("\\s+", " ");
				output.println(text03);
			}
				
			
			output.close();
			reader01.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}

	}
}