package com.udemy.tdd;

import java.io.IOException;

public class ISBNValidator {

	
	public static void main(String args[]){
		
		checkISBN("9876543212");
		
		System.out.println("Press Enter to continue...");
	    try {
			System.in.read();
		} catch (IOException e) {e.printStackTrace();}
	    		System.out.println("Finished.");
		
	}
	
	public static boolean checkISBN(String isbn) {
		
		if (isbn.length() != 10)throw new NumberFormatException("ISBN muss 10 Zeichen haben");
		
	
		int total = 0;
		
		for(int i=0; i<10;i++){
			
			if(!Character.isDigit(isbn.charAt(i))){
				
				if (i == 9 && isbn.charAt(i) == 'X'){ 
					total +=10;
				}	
				else{
					throw new NumberFormatException("ISBN muss aus Ziffern bestehen");
				}
			}
			else{	
			total += Character.getNumericValue(isbn.charAt(i))*(10-i);
			}
		}
		
		
		if(total % 11 == 0)	{
			System.out.println("passt");
			return true;
		}
		else {
			System.out.println("passt ned");
		}
			return false;
	}
}

