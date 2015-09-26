import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class newTest{
	public String upperCase(String token, String keyword){//capitalize Subject
		int x = token.length();
		
		if (token.equalsIgnoreCase(keyword)){//if the token same as the Subject
			token = token.substring(0, 1).toUpperCase() + token.substring(1);
		}

		String token01 = token.substring(1, x);//else if there is '#' in front of Subject
		if (token.contains("#") && token01.equalsIgnoreCase(keyword)){
			token = token01.substring(0, 1).toUpperCase() + token01.substring(1);
		}
		
		if (token.length() > 2){//else there is 's followed by Subject
			String token02 = token.substring(0, x-2);
			if (token.contains("'s") && token02.equalsIgnoreCase(keyword)){
				token = token.substring(0, 1).toUpperCase() + token.substring(1);
			}
		}
		
		if (token.contains("@")){//else if the token contain a '@' and Subject
			int index = token.indexOf("@");
			String a = token.substring(0, index);
			String b = token.substring(index+1 , x);
			
			if(a.equalsIgnoreCase(keyword)){
				token = a.substring(0, 1).toUpperCase() + a.substring(1) + " @" + b;
			}
			else if (b.equalsIgnoreCase(keyword)){
				token = a + "@ " + b.substring(0, 1).toUpperCase() + b.substring(1);
			}
		}
		
		return token;
	}
	
	public static String lowerCase(String token, String keyword){//capitalize Subject
		int x = token.length();
		
		if (token.equalsIgnoreCase(keyword)){//if the token same as the Subject
			token = keyword;
		}

		String token01 = token.substring(1, x);//else if there is '#' in front of Subject
		if (token.contains("#") && token01.equalsIgnoreCase(keyword)){
			token = keyword;
		}
		
		if (token.length() > 2){//else there is 's followed by Subject
			String token02 = token.substring(0, x-2);
			if (token.contains("'s") && token02.equalsIgnoreCase(keyword)){
				token = keyword;
			}
		}
		
		if (token.contains("@")){//else if the token contain a '@' and Subject
			int index = token.indexOf("@");
			String a = token.substring(0, index);
			String b = token.substring(index+1 , x);
			
			if(a.equalsIgnoreCase(keyword)){
				token = keyword + " @" + b;
			}
			else if (b.equalsIgnoreCase(keyword)){
				token = a + "@ " + keyword;
			}
		}
		
		return token;
	}
	
	public static void main(String[] arg) throws IOException{
		String a = "New UniFi service";
		String[] b = a.split(" ");
		String c = "";
		
		for (int i=0; i<b.length; i++){
			b[i] = lowerCase(b[i], "Unifi");
			c = c  + " " + b[i];			
		}
		
		System.out.println(c);
	}
}