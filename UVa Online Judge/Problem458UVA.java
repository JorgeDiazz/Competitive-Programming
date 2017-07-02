import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem458UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String cadena;
		while ((cadena = in.readLine()) != null) {
			String palabra = "";
			for (char c : cadena.toCharArray())
			palabra = palabra.concat(String.valueOf(Character.valueOf((char) (c - 7))));
			System.out.println(palabra);
		}
		System.exit(0);
	}
/*	
	 DataInputStream in = new DataInputStream(System.in);
     DataOutputStream out = new DataOutputStream(System.out) ;
     int codigo;
     while ((codigo =in.read()) !=-1)
         out.write((Character.isSpace((char)codigo) ? codigo : (codigo-7)));*/

}
	
