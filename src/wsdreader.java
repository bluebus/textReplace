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

public class wsdreader{
	public static void main (String[] args) throws Exception {

		File subj = new File ("sampleresult_noStem.txt");

		try {			
			BufferedReader reader01 = new BufferedReader(new InputStreamReader(new FileInputStream(subj), "UTF-8"));
			String text01 = "";
			String text02 = "";
			Set<String> list = new HashSet<String>();
			int count = 0;

			File txt = new File ("samplefinal_noStem.txt");
			if (txt.exists())
				txt.delete();
			PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(txt, true)));

			while ((text01 = reader01.readLine()) != null){
				count ++;
				String a = text01.replaceAll("\\s+", " ");
				String b = a.trim();
				//System.out.println(b);

				if (!b.isEmpty()){
					if (b.contains("Results after disambiguation") && count <5){

					}
					else if (b.contains("Results after disambiguation.")){
						System.out.println(text02);
						output.println(text02);
						text02 = "";

					}
					else{
						String[] abc = b.split(" ");
						String text03 = "";

						if (abc.length>3)
							text03 = abc[1] + "#" + abc[2] + "#" + abc[3];
						else
							text03 = abc[0];

						text02 = text02 + text03 + " ";
					}
				}
			}
			System.out.println(text02);
			output.println(text02);

			output.close();
			reader01.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}

	}
}