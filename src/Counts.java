import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



public class Counts //test code to count the average sentiment of sentiword
{
	public static void main(String[] args) throws Exception 
	{	
		File subj = new File ("SentiWordNet.txt");
		
		String node = "better";
		String type = "r";
		String sentiment = "";

		node = node.toLowerCase() + "#";
		System.out.println("Check SentiWordNet: " + node + "\t" + type);
			try{
				BufferedReader reader01 = new BufferedReader(new FileReader(subj));
				String text01="";
				int P_count = 0;
				int N_count = 0; 
				int count = 0; 

				while((text01 = reader01.readLine()) != null){
					String[] data = text01.split("\t");
					String sentiWord = data[4];
					String attribute = data[0];					

					if (sentiWord.contains(node) && attribute.equalsIgnoreCase(type)){//){
						//sentiWord.indexOf(node)
						String[] array = sentiWord.split(" ");
						//System.out.println(sentiWord);

						for (int x = 0; x< array.length; x++){
							int y = array[x].length()-1;
							String str = array[x].substring(0, y);
							if (str.equals(node) ){//&& attribute.equals(type)
								float pos = (Float.valueOf(data[2])).floatValue(); 
								float neg = (Float.valueOf(data[3])).floatValue();
								System.out.println(text01);

								if(neg > 0 && pos == 0 || pos < neg){
									N_count+=1;
								}else if(neg == 0 && pos > 0 ||  neg < pos){
									P_count+=1;
								}else if (neg == pos){
									count+=1;
								}				
							}
						}
					}
				}

				System.out.println("Positive: " + P_count + "\tNegative: " + N_count + "\tNeutral: " + count);

			if (P_count > N_count)
				if (P_count > count || (P_count == count && N_count == 0))
					sentiment = "Positive";
				else 
					sentiment = "Neutral";
			else if (count >= 0)
				if (P_count == N_count)
					sentiment = "Neutral";
			else 
				if (N_count > count || (N_count == count && P_count == 0))
					sentiment = "Negative";
				else
					sentiment = "Neutral";

			System.out.println(sentiment);
			reader01.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

}

