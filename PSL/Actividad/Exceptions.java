package Actividad;

public class Exceptions {

	public static boolean isNumeric(String word) {
		try {
			Integer.parseInt(word);
			return true;
		} catch (NumberFormatException e) {
			try {
				throw new Exception("La entrada debe ser numérica.");
			} catch (Exception ex) {}
			return false;
		}

	}

	public static boolean isCorrectData(String[] data) {
		if (data.length < 2 || data.length > 2) {
			try {
				throw new Exception("Debe ingresar un caracter ','");
			} catch (Exception e) {}
			return false;
		} else {
			return true;
		}
	}
}
