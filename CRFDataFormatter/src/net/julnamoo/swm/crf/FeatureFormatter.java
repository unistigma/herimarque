package net.julnamoo.swm.crf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FeatureFormatter {

	public static void main(String[] args) throws IOException
	{
//		String yes = "S";
//		String no = "X";

		measurePerformance();
	}
	
	private static void formatting() throws IOException
	{
		
		File data = new File("autospace.train.txt");
		BufferedReader br = new BufferedReader(new FileReader(data));
	
		File formatted = new File("formatted.train.txt");
		PrintWriter writer = new PrintWriter(formatted);
		
		String line = br.readLine();
		while(line != null)
		{
			System.out.println("analysis " + line);
			String[] tokens = line.split(" ");
			
			if(tokens.length > 0)
			{
				for(String token : tokens)
				{
					if(token.length() > 0)
					{
						//마지막 문자 전까지 X 출력
						int i = 0;
						for(i = 0; i < token.length()-1; ++i)
						{
							writer.print(token.charAt(i));
//							System.out.print(token.charAt(i));
							writer.println(" X");
//							System.out.println(" X");
						}
						//the last character with S and the new line
						writer.print(token.charAt(i));
//						System.out.print(token.charAt(i));
						writer.println(" S");
//						System.out.println(" S");
					}
				}
			}
			
			line = br.readLine();
		}
		
		br.close();
		writer.close();
	}
	
	private static void measurePerformance() throws IOException
	{
		File result = new File("result.txt");
		BufferedReader br = new BufferedReader(new FileReader(result));
		
		String line = br.readLine();
		
		// 
		int accord = 0; //둘 다 S 
		int r_val = 0; // 답 만 S
		int p_val = 0; // 예측만 S
		while(line != null)
		{
			//0 : char, 1 : 답, 2 : 예측
			String[] tokens = line.split("\t");
			
			if(tokens.length == 3)
			{
				if(tokens[1].equals("S"))
				{
					++r_val;
				}
				
				if(tokens[2].equals("S"))
				{
					++p_val;
				}
				
				if(tokens[1].equals("S") && tokens[1].equals(tokens[2]))
				{
					++accord;
				}
			}
			
			line = br.readLine();
		}
		
		float precision = accord/(float) r_val;
		float recall = accord/(float) p_val;

		System.out.println("Number of coordinate S : " + accord);
		System.out.println("Number of real S : " + r_val);
		System.out.println("Number of predict S : " + p_val);
		System.out.println("Precision(cs/rs) = " + precision);
		System.out.println("Recall(cs/ps) = " + recall);
	}
}
