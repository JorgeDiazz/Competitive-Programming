
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 class LeerDatos {
	public static String dato() {
		String x = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			x = in.readLine();
		} catch (IOException e) {
			System.err.print("Error: " + e.getMessage());
		}
		return x;
	}

	public static int datoInt() {
		try {
			return Integer.parseInt(dato());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static long datoLong() {
		try {
			return Long.parseLong(dato());
		} catch (NumberFormatException e) {
			return 0l;
		}
	}

	public static float datoFloat() {
		try {
			return (new Float(dato())).floatValue();
		} catch (Exception e) {
			return Float.NaN;
		}
	}

	public static double datoDouble() {
		try {
			return (new Double(dato())).doubleValue();
		} catch (Exception e) {
			return Double.NaN;
		}
	}

}
