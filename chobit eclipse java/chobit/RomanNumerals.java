package chobit;

public class RomanNumerals {
	public static String toRoman(int n) {
		String[] romanNumerals = { "M", "CM", "D", "CD", "C", "XC", "L", "X", "IX", "V", "I" };
		int[] romanNumeralNums = { 1000, 900, 500, 400, 100, 90, 50, 10, 9, 5, 1 };
		String finalRomanNum = "";

		for (int i = 0; i < romanNumeralNums.length; i++) {
			int currentNum = n / romanNumeralNums[i];
			if (currentNum == 0) {
				continue;
			}

			for (int j = 0; j < currentNum; j++) {
				finalRomanNum += romanNumerals[i];
			}

			n = n % romanNumeralNums[i];
		}
		return finalRomanNum;
	}

	public static int romanConvert(String roman) {
		int decimal = 0;

		String romanNumeral = roman.toUpperCase();
		for (int x = 0; x < romanNumeral.length(); x++) {
			char convertToDecimal = roman.charAt(x);

			switch (convertToDecimal) {
			case 'M':
				decimal += 1000;
				break;

			case 'D':
				decimal += 500;
				break;

			case 'C':
				decimal += 100;
				break;

			case 'L':
				decimal += 50;
				break;

			case 'X':
				decimal += 10;
				break;

			case 'V':
				decimal += 5;
				break;

			case 'I':
				decimal += 1;
				break;
			}
		}
		if (romanNumeral.contains("IV")) {
			decimal -= 2;
		}
		if (romanNumeral.contains("IX")) {
			decimal -= 2;
		}
		if (romanNumeral.contains("XL")) {
			decimal -= 10;
		}
		if (romanNumeral.contains("XC")) {
			decimal -= 10;
		}
		if (romanNumeral.contains("CD")) {
			decimal -= 100;
		}
		if (romanNumeral.contains("CM")) {
			decimal -= 100;
		}
		return decimal;
	}
}
