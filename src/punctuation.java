import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class punctuation{
	static textProcess t = new textProcess();
	public static void main(String[] args){
		String line = "@Digi";
		System.out.println(line);
		String output = t.upperCase(line, "Digi");
		System.out.println(output);		
	}
}