package com.restwithspring.restwithspring.Converters;

public class NumberConverter {
	
	public static Double convertToDouble(String stringNumber) {
		if(stringNumber == null) return 0D;
		// BR 10,25 US 10.25
		String number = stringNumber.replaceAll(",", ".");
		if(isNumeric(number))return Double.parseDouble(number);	//converte string para double	
		 return 0D;
	}

	public static boolean isNumeric(String stringNumber) {
		if(stringNumber == null) return false;
		String number = stringNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+"); //verifica se é positivo ou negativo de 0 a 9 e a fração é ? opcional
	}
}
