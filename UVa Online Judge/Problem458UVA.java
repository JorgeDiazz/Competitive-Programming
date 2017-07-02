import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


class Problem458UVA {

	public static void main(String[] args) throws IOException {

	DataInputStream in = new DataInputStream(System.in);
     	DataOutputStream out = new DataOutputStream(System.out);
		
     	int codigo;
     	while ((codigo =in.read()) !=-1)
        	out.write((Character.isSpace((char)codigo) ? codigo : (codigo-7)));

}
	
