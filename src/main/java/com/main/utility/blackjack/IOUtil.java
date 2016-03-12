package com.main.utility.blackjack;

import java.util.List;
import java.util.Scanner;

public class IOUtil {
	
	public List<String> inputString(int count) 
	{
		Scanner inputscan=new Scanner(System.in); 
		List<String> inputdata = null;
			System.out.println("Enter "+ count +" Names");
			for(int t=0;t<count;t++)
				{inputdata.add(inputscan.nextLine());}
			return inputdata;
}}
