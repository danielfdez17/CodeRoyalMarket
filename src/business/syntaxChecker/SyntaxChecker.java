package business.syntaxChecker;

public class SyntaxChecker {
	
	private static final int LENGTH = 9;
	
	public static boolean isNif(String nif) {
		if (nif == null || nif.length() != LENGTH || nif.isEmpty()) 
			return false;
		boolean isNif = true;
		for (int i = 0; i < LENGTH - 1 && isNif; ++i) {
			isNif = Character.isDigit(nif.charAt(i));
		}
		return isNif && 
				Character.isLetter(nif.charAt(LENGTH - 1)) && 
				Character.isUpperCase(nif.charAt(LENGTH - 1));
	}

	public static boolean isName(String name) {
		if (name == null || name.isEmpty() || name.length() == 0) 
			return false;
		boolean isName = true;
		int whiteSpaces = 0;
		for (int i = 0; i < name.length() && isName; ++i) {
			isName = Character.isLetter(name.charAt(i)) ||
					Character.isWhitespace(name.charAt(i));
			if (Character.isWhitespace(name.charAt(i)))
				++whiteSpaces;
		}
		return isName && whiteSpaces != name.length();
	}

	public static boolean isCity(String city) {
		if (city == null || city.isEmpty() || city.length() == 0) 
			return false;
		boolean isCity = true; int whiteSpaces = 0;
		for (int i = 0; i < city.length() && isCity; ++i) {
			isCity = Character.isLetter(city.charAt(i)) || 
					Character.isWhitespace(city.charAt(i));
			if (Character.isWhitespace(city.charAt(i)))
				++whiteSpaces;
		}
		return isCity && whiteSpaces != city.length();
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.length() != LENGTH || phoneNumber.isEmpty()) 
			return false;
		boolean isPhone = true;
		for (int i = 0; i < LENGTH && isPhone; ++i) {
			isPhone = Character.isDigit(phoneNumber.charAt(i));
		}
		return isPhone;
	}
}