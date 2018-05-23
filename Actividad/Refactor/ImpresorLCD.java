package Refactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static Refactor.Exceptions.*;

public class ImpresorLCD {

	// Puntos fijos
	private final int[][] pf;
	// Matriz numeros LCD
	private String[][] matrizImpr;
	// Caracteres que se usan
	static final String CARACTER_VERTICAL = "|";
	static final String CARACTER_HORIZONTAL = "-";
	static final String POSICION_X = "X";
	static final String POSICION_Y = "Y";
	// Size de los digitos
	private int size;
	// Datos ingresados
	private int filasDig;
	private int columDig;
	// Filas y columnas totales
	private int totalFilas;
	private int totalColum;

	public ImpresorLCD() {
		// Inicializa variables
		this.pf = new int[5][2];
	}

	/**
	 *
	 * Metodo encargado de añadir una linea a la matriz de Impresion
	 *
	 * @param matriz
	 *            Matriz Impresion
	 * @param punto
	 *            Punto Pivote
	 * @param posFija
	 *            Posicion Fija
	 * @param size
	 *            Tamaño Segmento
	 * @param caracter
	 *            Caracter Segmento
	 */
	private void adicionarLinea(String[][] matriz, int[] punto, String posFija, int size, String caracter) {

		if (posFija.equals(POSICION_X)) {
			for (int y = 1; y <= size; y++) {
				int valor = punto[1] + y;
				matriz[punto[0]][valor] = caracter;
			}
		} else {
			for (int i = 1; i <= size; i++) {
				int valor = punto[0] + i;
				matriz[valor][punto[1]] = caracter;
			}
		}
	}

	/**
	 *
	 * Metodo encargado de un segmento a la matriz de Impresion
	 *
	 * @param segmento
	 *            Segmento a adicionar
	 */
	private void adicionarSegmento(int segmento) {

		switch (segmento) {
		case 1:
			adicionarLinea(this.matrizImpr, this.pf[0], POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		case 2:
			adicionarLinea(this.matrizImpr, this.pf[1], POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		case 3:
			adicionarLinea(this.matrizImpr, this.pf[4], POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		case 4:
			adicionarLinea(this.matrizImpr, this.pf[3], POSICION_Y, this.size, CARACTER_VERTICAL);
			break;
		default:
			adicionarLinea(this.matrizImpr, this.pf[segmento - 5], POSICION_X, this.size, CARACTER_HORIZONTAL);

		}
	}

	/**
	 *
	 * Metodo encargado de definir los segmentos que componen un digito y a partir
	 * de los segmentos adicionar la representacion del digito a la matriz
	 *
	 * @param numero
	 *            Digito
	 */
	private void adicionarDigito(int numero) {

		// Establece los segmentos de cada numero
		List<Integer> segList = new ArrayList<>();

		switch (numero) {
		case 1:
			for (int i = 3; i <= 4; i++)
				segList.add(i);
			break;
		case 2:
			for (int i = 2; i <= 7; i++)
				segList.add(i);
			segList.remove(2);
			break;
		case 3:
			for (int i = 3; i <= 7; i++)
				segList.add(i);
			break;
		case 4:
			segList.add(1);
			for (int i = 3; i <= 4; i++)
				segList.add(i);
			segList.add(6);
			break;
		case 5:
			segList.add(1);
			for (int i = 4; i <= 7; i++)
				segList.add(i);
			break;
		case 6:
			for (int i = 1; i <= 7; i++)
				segList.add(i);
			segList.remove(2);
			break;
		case 7:
			for (int i = 3; i <= 5; i++)
				segList.add(i);
			break;
		case 8:
			for (int i = 1; i <= 7; i++)
				segList.add(i);
			break;
		case 9:
			for (int i = 1; i <= 7; i++)
				segList.add(i);
			segList.remove(1);
			break;
		case 0:
			for (int i = 1; i <= 7; i++)
				segList.add(i);
			segList.remove(5);
			break;
		}

		segList.forEach(seg -> adicionarSegmento(seg));
	}

	/**
	 *
	 * Metodo encargado de imprimir un numero
	 *
	 * @param size
	 *            Tamaño Segmento Digitos
	 * @param numeroImp
	 *            Numero a Imprimir
	 * @param espacio
	 *            Espacio Entre digitos
	 */
	private void imprimirNumero(int size, String numeroImp, int espacio) {
		int pivotX = 0;
		char[] digitos;

		this.size = size;

		// Calcula el numero de filas cada digito
		this.filasDig = (2 * this.size) + 3;

		// Calcula el numero de columna de cada digito
		this.columDig = this.size + 2;

		// Calcula el total de filas de la matriz en la que se almacenaran los digitos
		this.totalFilas = this.filasDig;

		// Calcula el total de columnas de la matriz en la que se almacenaran los
		// digitos
		this.totalColum = (this.columDig * numeroImp.length()) + (espacio * numeroImp.length());

		// crea matriz para almacenar los numero a imprimir
		this.matrizImpr = new String[this.totalFilas][this.totalColum];

		// Inicializa matriz
		for (int i = 0; i < this.totalFilas; i++) {
			Arrays.fill(this.matrizImpr[i], " ");
		}

		// crea el arreglo de digitos
		digitos = numeroImp.toCharArray();

		for (char digito : digitos) {

			int numero = Character.getNumericValue(digito);

			// Calcula puntos fijos
			this.pf[0][1] = pivotX;

			this.pf[1][0] = this.filasDig >> 1;
			this.pf[1][1] = pivotX;

			this.pf[2][0] = this.filasDig - 1;
			this.pf[2][1] = pivotX;

			this.pf[3][0] = this.columDig - 1;
			this.pf[3][1] = (this.filasDig >> 1) + pivotX;

			this.pf[4][1] = (this.columDig - 1) + pivotX;

			pivotX += this.columDig + espacio;

			adicionarDigito(numero);
		}

		// Imprime matriz
		for (int i = 0; i < this.totalFilas; i++) {
			Arrays.stream(this.matrizImpr[i]).forEach(System.out::print);
			System.out.println();
		}
	}

	/**
	 *
	 * Metodo encargado de procesar la entrada que contiene el size del segmento de
	 * los digitos y los digitos a imprimir
	 *
	 * @param comando
	 *            Entrada que contiene el size del segmento de los digito y el
	 *            numero a imprimir
	 * @param espacioDig
	 *            Espacio Entre digitos
	 */
	public void procesar(String comando, int espacioDig) {

		int tam = 0;
		String[] parametros;

		// Se hace el split de la cadena
		parametros = comando.split(",");
		// Se validan los datos ingresados
		verifyData(parametros, comando);

		// Valida que el parametro size sea un numerico
		if (isNumeric(parametros[0]) && isNumeric(parametros[1])) {
			tam = Integer.parseInt(parametros[0]);
			// se valida que el size este entre 1 y 10
			verifyRange(tam);
		}

		// Realiza la impresion del numero
		imprimirNumero(tam, parametros[1], espacioDig);
	}

}