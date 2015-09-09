package com.test;

import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		for(int i = 1; i < 5 ; i++){
			for(int spaces = i; spaces <= 5;spaces++){
				System.out.print(" ");
			}
			for(int j = i ; j > 0 ; j--){
				System.out.print("*");
				System.out.print(" ");
			}
			System.out.println();
		}
		
		printEquilateralTriangle();
	}
	
	public static void printEquilateralTriangle(){
		int userRowNumber = 5;
		int height = 10;
		int number_of_stars = height;
		String charToPrint;
		for(int rows = 1; rows <= height; rows++){
			charToPrint = "*";
			if(rows == userRowNumber){
				charToPrint = "x";
			}
			for(int spaces = 1; spaces <= number_of_stars; spaces++){
				System.out.print(" ");
			}
			for(int star = 1; star <= rows; star++){
				System.out.print(charToPrint);
				System.out.print(" ");
			}
			System.out.println("");
			number_of_stars = number_of_stars -1;
		}
	}
	
}
