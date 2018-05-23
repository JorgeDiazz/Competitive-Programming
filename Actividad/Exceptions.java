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
	 * @throws Exception
	 */
	public static boolean isNumeric(String word) throws Exception {
		try {
			Integer.parseInt(word);
			return true;
		} catch (NumberFormatException e) {
			throw new Exception("La entrada debe ser num�rica.");
		}

	}

	/**
	 * Verifica si los datos se ingresaron correctamente
	 * 
	 * @param data
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isCorrectData(String[] data) throws Exception {
		if (data.length > 2 || data.length < 2) {
			throw new Exception("Debe ingresar los datos en el formato '##,##'.");
		} else {
			int firstValue = Integer.parseInt(data[0]);
			if (firstValue < 1 || firstValue > 10) {
				throw new Exception("Size debe estar entre 1 y 10.");
			}

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
