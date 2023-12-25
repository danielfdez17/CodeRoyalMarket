package business.sintaxChecker;

public class SintaxChecker {
	
	private static final int LENGTH = 9;
	
	public static boolean isNif(String nif) {
		if (nif.length() != LENGTH) return false;
//		int i = 0; 
		boolean exit = false;
		for (int i = 0; i < LENGTH - 1 && !exit; ++i) {
			exit = Character.isDigit(nif.charAt(i));
		}
//		while (i < LENGTH - 1 && !exit) {
//			exit = Character.isDigit(nif.charAt(i));
//			++i;
//		}
		return !exit && Character.isLetter(nif.charAt(LENGTH - 1));
	}

	public static boolean isName(String name) {
		if (name.isEmpty() || name.length() == 0) return false;
		boolean isName = true;
		for (int i = 0; i < name.length() && isName; ++i) {
			isName = Character.isLetter(name.charAt(i)) || Character.isWhitespace(name.charAt(i));
		}
		return !isName;
	}

	public static boolean isCity(String city) {
		if (city.isEmpty() || city.length() == 0) return false;
		boolean isCity = true;
		for (int i = 0; i < city.length() && isCity; ++i) {
			isCity = Character.isLetter(city.charAt(i)) || Character.isWhitespace(city.charAt(i));
		}
		return !isCity;
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		if (phoneNumber.length() != LENGTH) return false;
		boolean isPhone = true;
		for (int i = 0; i < LENGTH && isPhone; ++i) {
			isPhone = !Character.isDigit(phoneNumber.charAt(i));
		}
		return isPhone;
	}
}