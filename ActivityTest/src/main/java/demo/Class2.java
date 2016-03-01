package demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Class2{

	/*public static void main(String[] args) {
		String s = "-";
		switch(TimeZone.CST) {
		case EST: s+="e";
		case CST: s+="c";
		case MST: s+="m";
		default: s+="X";
		case PST:s+="p";
		}
		System.out.println(s);
	}*/

	//enum TimeZone{EST,CST,MST,PST}
	
	/*
	static final long tooth = 343L;
	static long dolt(long tooth){
		System.out.println(++tooth+"");
		return ++tooth;
	}
	
	public static void main(String[] args) {
		System.out.println(tooth+" ");
		final long tooth = 340L;
		new Class2().dolt(tooth);
		System.out.println(tooth);
	}*/
	
	public static void main(String[] args) {
		  /*Pattern pattern = Pattern.compile("\\s{2,}");
		  Matcher matcher = pattern.matcher("S  tr i    n     g");
		  System.out.println(matcher.replaceAll(" "));*/
		  String str = "S  t r   i  n    g ";
		  System.out.println(str.replaceAll("\\s{2,}", " "));
	}
}
