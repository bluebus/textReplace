import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class textProcess{

	public textProcess(){
	}

	public Long getTime(){
		java.util.Date date= new java.util.Date();
		Long time = date.getTime();
		return time;
	}

	public String RemoveUrl(String commentstr){//remove url		
		commentstr = commentstr.replace("(http://", " http://");		
		String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d+:#%/;$()~_?\\+-=\\\\\\.&]*)";
		Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(commentstr);
		int i=0;
		while (m.find()) {
			commentstr=commentstr.replace(m.group(i),"").trim();
			i++;
		}
		return commentstr;
	}

	public String removeSpaces(String str){//remove repeated character
		StringBuilder sb = new StringBuilder(str);
		int i=2;
		while (i < str.length()){
			if (str.charAt(i-2) == str.charAt(i) && str.charAt(i-1) == str.charAt(i)){
				sb.deleteCharAt(i);
				str = sb.toString();
				if (i>2)
					i = i-1;
			}
			else
				i++;			
		}
		return str;
	}

	public static String unaccent(String src) {
		return Normalizer.normalize(src, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public String removeEmoticon(String str){	
		ArrayList<String> emoticon = emoticon();
		for (int i = 0; i<emoticon.size(); i++){
			str = str.replace(emoticon.get(i), "");
		}		
		return str;
	}


	public String splitWP(String str){//split punctuation apart frm words
		String newline = "";	

		if (str.startsWith("@") && str.length() > 1){			
			int i = str.length()-1;
			String s = Character.toString(str.charAt(i));

			if (!Character.isDigit(str.charAt(i)) && !Character.isLetter(str.charAt(i))){
				newline = str.substring(0, i) + " " + s;
			}

			return newline;
		}
		else{
			for (int i=0; i<str.length(); i++){
				String s = Character.toString(str.charAt(i));			

				if (i == 0)
					newline = newline + s;

				else if (i<str.length()-1){								
					if (!Character.isDigit(str.charAt(i)) && !Character.isLetter(str.charAt(i))){//if not character nor digit
						if (Character.isDigit(str.charAt(i-1)) && Character.isDigit(str.charAt(i+1))){//if front and back character is digit
							if (s.contains(".") || s.contains("/"))
								newline = newline + s;
							else 
								newline = newline + " " + s;
						}				
						else if (Character.isLetter(str.charAt(i-1)) && Character.isLetter(str.charAt(i+1))){//if front and back character is letter
							if (s.contains("@") || s.contains("'") || s.contains("_"))
								newline = newline+ s;
							else 
								newline = newline + " " + s;
						}	
						else if (!Character.isDigit(str.charAt(i-1)) && !Character.isLetter(str.charAt(i-1)))
							if (!Character.isDigit(str.charAt(i)) && !Character.isLetter(str.charAt(i)))
								newline = newline + s;
							else 
								newline = newline + " " + s;
						else
							newline = newline + " " + s;
					}
					else{//if is character or digit
						//check for char at front
						String x = Character.toString(str.charAt(i-1));
						if (!Character.isDigit(str.charAt(i-1)) && !Character.isLetter(str.charAt(i-1))){
							if (x.matches("[@ # _]"))
								newline = newline + s;
							else if (x.matches(".") && i>1 && Character.isDigit(str.charAt(i-2)) && Character.isDigit(str.charAt(i)))
								newline = newline + s;
							else if (x.matches("'") && i>1 && Character.isLetter(str.charAt(i-2)) && Character.isLetter(str.charAt(i)))
								newline = newline + s;
							else
								newline = newline + " " + s;
						}
						else
							newline = newline + s;
					}
				}

				else{  
					if (!Character.isDigit(str.charAt(i)) && !Character.isLetter(str.charAt(i))){//if not character nor digit
						//check for char at front
						if(!Character.isDigit(str.charAt(i-1)) && !Character.isLetter(str.charAt(i-1)))
							newline = newline + s;	
						else
							newline = newline + " " + s;
					}
					else{//if is character or digit
						//check for char at front
						String x = Character.toString(str.charAt(i-1));
						if (!Character.isDigit(str.charAt(i-1)) && !Character.isLetter(str.charAt(i-1))){
							if (x.matches("[@ #]"))
								newline = newline + s;
							else
								newline = newline + " " + s;
						}
						else
							newline = newline + s;
					}
				}
			}		
			newline = newline.replaceAll("\\s+ ", " ");

			return newline;
		}
	}

	public String replaceC (String str){//replace some special character
		String text = str.replaceAll("\n", " ");
		String text01 = text.replace("&gt;", ">");
		String text02 = text01.replace("&lt;", "<");
		String text03 = text02.replace("&amp;", " and ");
		String text04 = text03.replace("[", "(");
		String text05 = text04.replace("]", ")");
		String text06 = text05.replace("\"", "");
		return text06;
	}

	public String replaceW (String token, String key){//replace token if equal ignore case to keyword
		if (token.equalsIgnoreCase(key)){
			token = key;
		}		
		return token;
	}

	public String lowerCase (String token){//if all CAP word, change to lower case
		boolean upperCase = token.equals(token.toUpperCase());
		if (upperCase)
			token = token.toLowerCase();

		return token;
	}

	public String upperCase(String token, String keyword){//capitalize Subject
		int x = token.length();
		String token01 = token.substring(1, x);//else if there is '#' in front of Subject
		
		if (token.equalsIgnoreCase(keyword)){//if the token same as the Subject
			//token = token.substring(0, 1).toUpperCase() + token.substring(1);
			System.out.println("Case 1");
			token = keyword;			
		}		
		else if (token.contains("#") && token01.equalsIgnoreCase(keyword)){
			//token = token01.substring(0, 1).toUpperCase() + token01.substring(1);
			System.out.println("Case 2");
			token = keyword;
		}
		else if (token.length() > 2){//else there is 's followed by Subject
			String token02 = token.substring(0, x-2);
			if (token.contains("'s") && token02.equalsIgnoreCase(keyword)){
				//token = token.substring(0, 1).toUpperCase() + token.substring(1);
				System.out.println("Case 3");
				token = keyword;
			}

			else if (token.contains("@")){//else if the token contain a '@' and Subject
				int index = token.indexOf("@");
				String a = token.substring(0, index);
				String b = token.substring(index+1 , x);

				if(a.equalsIgnoreCase(keyword)){
					//token = a.substring(0, 1).toUpperCase() + a.substring(1) + " @" + b;
					System.out.println("Case 4");
					token = keyword + " @" + b;
				}
				else if (b.equalsIgnoreCase(keyword)){
					//token = a + "@ " + b.substring(0, 1).toUpperCase() + b.substring(1);
					System.out.println("Case 5");
					token = a + "@ " + keyword;
				}
			}	
		}
		return token;
	}

	public String capitalize (String token){
		for (int v=0; v<subject().size();v++){//capitalize the name of subject
			String key = subject().get(v);	
			if (token.contains(key))
				token = upperCase(token, key);				
		}
		return token;
	}

	public Map<Integer, String> map = new TreeMap<Integer, String>();	

	public String deletek(StringTokenizer str){		
		ArrayList<String> Subjects = subject();
		String text ="";
		int i=0;
		boolean check = false;

		while(str.hasMoreTokens()){			
			String target = str.nextToken();
			for (int a=0; a<Subjects.size();a++){
				String subj = Subjects.get(a);				
				if (target.equalsIgnoreCase(subj)){
					map.put(i, target);
					check = true;
				}
			}			
			if (!check)
				text = text + target + " ";
			i++;
		}	
		return text;
	}

	public String insertk(String str){
		if (map.isEmpty())
			return str;
		else{
			Iterator<Map.Entry<Integer, String>> entries = map.entrySet().iterator();
			String text ="";
			ArrayList<String> sentence = new ArrayList<String>();

			String[] line = str.split(" ");
			sentence = new ArrayList<String>(Arrays.asList(line));

			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				int key = Integer.valueOf(entry.getKey().toString());					
				String value = entry.getValue().toString();
				sentence.add(key, value);
			}	

			for (int i=0; i<sentence.size(); i++){
				text = text +sentence.get(i) + " ";
			}
			sentence.clear();
			map.clear();
			return text;
		}
	}

	public ArrayList<String> intTerm()    {
		ArrayList<String> Array = new ArrayList<String>();
		String text;

		try {	 
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader("lib/text/internet_terms.txt"));

			while ((text = reader.readLine()) != null) {//read internet terms				
				Array.add(text);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return(Array);
	}

	public ArrayList<String> emoticon()    {
		ArrayList<String> Array = new ArrayList<String>();
		String text;

		try {	 
			//BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("lib/text/emoticons.txt"), "UTF-8"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("lib/text/emoticons.txt"), "UTF-8"));
			while ((text = reader.readLine()) != null) {//read internet terms				
				Array.add(text);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return(Array);
	}

	public ArrayList<String> subject()    {
		ArrayList<String> Array = new ArrayList<String>();
		String text;

		try {	 
			BufferedReader reader = new BufferedReader(new FileReader("lib/text/subject.txt"));

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

	public ArrayList<String> sWord_en()    {
		ArrayList<String> Array = new ArrayList<String>();
		String text;

		try {	 
			BufferedReader reader = new BufferedReader(new FileReader("lib/text/shortform_eng.txt"));

			while ((text = reader.readLine()) != null) {//read subject			
				String[] str = text.split("="); 
				Array.add(str[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return(Array);
	}

	public ArrayList<String> oWord_en()    {
		ArrayList<String> Array = new ArrayList<String>();
		String text;

		try {	 
			BufferedReader reader = new BufferedReader(new FileReader("lib/text/shortform_eng.txt"));

			while ((text = reader.readLine()) != null) {//read subject			
				String[] str = text.split("="); 
				Array.add(str[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return(Array);
	}

	public ArrayList<String> oExps()    {
		ArrayList<String> Array = new ArrayList<String>();
		String text;

		try {	 
			BufferedReader reader = new BufferedReader(new FileReader("lib/text/expansion.txt"));

			while ((text = reader.readLine()) != null) {//read subject			
				String[] str = text.split("="); 
				Array.add(str[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return(Array);
	}

	public ArrayList<String> sExps()    {
		ArrayList<String> Array = new ArrayList<String>();
		String text;

		try {	 
			BufferedReader reader = new BufferedReader(new FileReader("lib/text/expansion.txt"));

			while ((text = reader.readLine()) != null) {//read subject			
				String[] str = text.split("="); 
				Array.add(str[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return(Array);
	}


	//method of parser
	public HashMap<String, String> newSentiWord (){
		HashMap<String, String> newSentiWord = new HashMap<String, String>();
		String text = "";
		try{
			BufferedReader reader = new BufferedReader(new FileReader("lib/text/SentiWord.txt"));

			while ((text = reader.readLine()) != null) {//read subject			
				String[] str = text.split("="); 
				newSentiWord.put(str[0], str[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		

		return newSentiWord;
	}

	public String checkType(String attribute){
		String type="";

		if (attribute.equals("JJ") || attribute.equals("JJR") || attribute.equals("JJS") )
			type = "a";
		else if (attribute.equals("NN") || attribute.equals("NNS") || attribute.equals("NNP") || attribute.equals("NNPS"))
			type = "n";
		else if (attribute.equals("VB") || attribute.equals("VBG") || attribute.equals("VBD") || attribute.equals("VBN") || attribute.equals("VBP") || attribute.equals("VBZ"))
			type = "v";
		else if (attribute.equals("RB") || attribute.equals("RBR") || attribute.equals("RBS"))
			type = "r";
		else 
			type = attribute;

		return type;
	}

	public boolean sentiWord (String str){
		if (str.equalsIgnoreCase("a") || str.equalsIgnoreCase("n") || str.equalsIgnoreCase("r") || str.equalsIgnoreCase("v"))
			return true;
		else 
			return false;
	}
}