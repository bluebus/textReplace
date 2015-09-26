import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class reprint{
	public static void main (String[] args) throws Exception {

		File subj = new File ("sampleresult.txt");

		try {			
			BufferedReader reader01 = new BufferedReader(new InputStreamReader(new FileInputStream(subj), "UTF-8"));
			String text01 = "";
			Set<String> list = new HashSet<String>();
			
			File txt = new File ("samplefinal.txt");
			if (txt.exists())
				txt.delete();
			PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(txt, true)));

			while ((text01 = reader01.readLine()) != null){
				list.add(text01);
			}
			
			for (String unique : list)
				output.println(unique);
			
			output.close();
			reader01.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}

	}
}