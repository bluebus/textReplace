import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class relation{
	public static void main(String[] arg){
		File file = new File("0814_result.txt");
		File newFile = new File ("relation.csv");
		
		if (newFile.exists())
			newFile.delete();
		
		try{
			BufferedReader reader01 = new BufferedReader(new FileReader(file));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(newFile, true)));
			
			String line = "";
			while((line = reader01.readLine()) != null){
				if (line.contains("Positive")|| line.contains("Negative"))
					if (!line.startsWith("Positive") && !line.startsWith("Negative"))
						out.println(line);
				
			}
			out.close();
			reader01.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}