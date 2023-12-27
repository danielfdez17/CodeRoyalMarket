package business;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import business.syntaxChecker.SyntaxChecker;

public class SyntaxCheckerTest {
	
	@Test
	public void isNif() {
		String nif = "12345678D";
		assertTrue(SyntaxChecker.isNif(nif));
		nif = "12345678d";
		assertFalse(SyntaxChecker.isNif(nif));
		assertFalse(SyntaxChecker.isNif(null));
		nif = "";
		assertFalse(SyntaxChecker.isNif(nif));
		nif = "         ";
		assertFalse(SyntaxChecker.isNif(nif));
	}
	
	@Test
	public void isName() {
		String name = "Pepe";
		assertTrue(SyntaxChecker.isName(name));
		name += " Ortiz";
		assertTrue(SyntaxChecker.isName(name));
		name = "9";
		assertFalse(SyntaxChecker.isName(name));
		assertFalse(SyntaxChecker.isName(null));
		name = "    ";
		assertFalse(SyntaxChecker.isName(name));
	}
	
	@Test
	public void isCity() {
		String name = "Madrid";
		assertTrue(SyntaxChecker.isCity(name));
		name += " Capital";
		assertTrue(SyntaxChecker.isCity(name));
		name = "9";
		assertFalse(SyntaxChecker.isCity(name));
		assertFalse(SyntaxChecker.isCity(null));
		name = "    ";
		assertFalse(SyntaxChecker.isCity(name));
	}
	
	@Test
	public void isPhoneNumber() {
		String phoneNumber = "123456789";
		assertTrue(SyntaxChecker.isPhoneNumber(phoneNumber));
		phoneNumber += "0";
		assertFalse(SyntaxChecker.isPhoneNumber(phoneNumber));
		assertFalse(SyntaxChecker.isPhoneNumber(null));
		assertFalse(SyntaxChecker.isPhoneNumber(""));
		assertFalse(SyntaxChecker.isPhoneNumber("   "));
	}
	

}
