package business;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import business.sintaxChecker.SintaxChecker;

public class SintaxCheckerTest {
	
	@Test
	public void isNif() {
		String nif = "12345678D";
		assertTrue(SintaxChecker.isNif(nif));
		nif = "12345678d";
		assertFalse(SintaxChecker.isNif(nif));
		assertFalse(SintaxChecker.isNif(null));
		nif = "";
		assertFalse(SintaxChecker.isNif(nif));
		nif = "         ";
		assertFalse(SintaxChecker.isNif(nif));
	}
	
	@Test
	public void isName() {
		String name = "Pepe";
		assertTrue(SintaxChecker.isName(name));
		name += " Ortiz";
		assertTrue(SintaxChecker.isName(name));
		name = "9";
		assertFalse(SintaxChecker.isName(name));
		assertFalse(SintaxChecker.isName(null));
		name = "    ";
		assertFalse(SintaxChecker.isName(name));
	}
	
	@Test
	public void isCity() {
		String name = "Madrid";
		assertTrue(SintaxChecker.isCity(name));
		name += " Capital";
		assertTrue(SintaxChecker.isCity(name));
		name = "9";
		assertFalse(SintaxChecker.isCity(name));
		assertFalse(SintaxChecker.isCity(null));
		name = "    ";
		assertFalse(SintaxChecker.isCity(name));
	}
	
	@Test
	public void isPhoneNumber() {
		String phoneNumber = "123456789";
		assertTrue(SintaxChecker.isPhoneNumber(phoneNumber));
		phoneNumber += "0";
		assertFalse(SintaxChecker.isPhoneNumber(phoneNumber));
		assertFalse(SintaxChecker.isPhoneNumber(null));
		assertFalse(SintaxChecker.isPhoneNumber(""));
		assertFalse(SintaxChecker.isPhoneNumber("   "));
	}
	

}
