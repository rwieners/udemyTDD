package com.udemy.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

public class ISBNValidatorTest {

	@Test
	public final void checkAValidISBN() {	
	ISBNValidator validator = new ISBNValidator();
	
	boolean result = validator.checkISBN("0140449116");
	assertTrue("first value: ", result);
	
	result = validator.checkISBN("0140177396");
	assertTrue("second value: ", result);
	}
	
	@Test
	public void ISBNNumberIsEndingInAXAreValid(){
		ISBNValidator validator = new ISBNValidator();
		boolean result = validator.checkISBN("012000030X");
		assertTrue(result);
	}
	
	
	@Test
	public final void checkAInValidISBN() {	
	ISBNValidator validator = new ISBNValidator();
	
	boolean result = validator.checkISBN("0140449117");
	assertFalse(result);
	}
	
	@Test(expected = NumberFormatException.class)
	public void nineDigitISBNAreNotAllowed(){
		ISBNValidator validator = new ISBNValidator();
		validator.checkISBN("123456789");
	}
	
	@Test(expected = NumberFormatException.class)
	public void nonNumericISBNAreNotAllowed(){
		ISBNValidator validator = new ISBNValidator();
		validator.checkISBN("Helloworld");
	}
	

}
