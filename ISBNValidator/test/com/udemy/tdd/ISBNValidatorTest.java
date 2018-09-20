package com.udemy.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

public class ISBNValidatorTest {

	@Test
	public final void checkAValidISBN() {	
	ISBNValidator validator = new ISBNValidator();
	boolean result = validator.checkISBN(140449116);
	assertTrue(result);
	}
	
	@Test
	public final void checkAInValidISBN() {	
	ISBNValidator validator = new ISBNValidator();
	boolean result = validator.checkISBN(140449117);
	assertFalse(result);
	}
	

}

// 0140449116
// 0140177396