package Refactor;

public class Exceptions {

	/**
	 *
	 * Metodo encargado de validar si una cadena es numerica
	 *
	 * @param cadena
	 *            Cadena
	 */
	static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Cadena " + cadena + " no es un entero");
		}
	}

	static void verifyRange(String comando) {
		int num = Integer.parseInt(comando);
		// se valida que el espaciado este entre 0 y 5
		if (num < 0 || num > 5) {
			throw new IllegalArgumentException("El espacio entre " + "digitos debe estar entre 0 y 5");
		}
	}

	static void verifyData(String[] parametros, String comando) {

		if (!comando.contains(",")) {
			throw new IllegalArgumentException("Cadena " + comando + " no contiene caracter ,");
		}

		// Valida la cantidad de parametros
		if (parametros.length > 2) {
			throw new IllegalArgumentException("Cadena " + comando + " contiene mas caracter ,");
		}
		// Valida la cantidad de parametros
		if (parametros.length < 2) {
			throw new IllegalArgumentException("Cadena " + comando + " no contiene los parametros requeridos");
		}
	}

	static void verifyRange(int tam) {
		// Verifica que el tamaño esté entre el rango establecido
		if (tam < 1 || tam > 10) {
			throw new IllegalArgumentException("El parametro size [" + tam + "] debe estar entre 1 y 10");
		}
	}

}
