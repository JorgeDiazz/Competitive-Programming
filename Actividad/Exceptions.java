package Actividad;

/**
 * Esta clase contiene las posibles excepciones que pueden ocurrir durante la
 * ejecuci�n del programa
 * 
 * @author JorgeD�az
 *
 */
public class Exceptions {

	/**
	 * M�todo que indica si la cadena de texto es num�rica
	 * 
	 * @param word
	 * @return isNumeric
	 */
	public static boolean isNumeric(String word) {
		try {
			Integer.parseInt(word);
			return true;
		} catch (NumberFormatException e) {
			try {
				throw new Exception("La entrada debe ser num�rica.");
			} catch (Exception ex) {
			}
			return false;
		}

	}

	/**
	 * Verifica si los datos se ingresaron correctamente
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean isCorrectData(String[] data) {
		if (data.length < 2 || data.length > 2) {
			try {
				throw new Exception("Debe ingresar un caracter ','");
			} catch (Exception e) {
			}
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Evalua si se sigue pidiendo la entrada
	 * 
	 * @param input
	 * @return next
	 */
	public static boolean validateInput(String input) {
		return !input.equals("0,0");
	}
}
