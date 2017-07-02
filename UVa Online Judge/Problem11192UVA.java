
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem11192UVA {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            
            String caso = in.readLine();
            int num = Character.getNumericValue(caso.charAt(0));
            if (num == 0) break;
            
            String cadena = caso.substring(2, caso.length()), nueva = "";
            num = cadena.length() / num;
            
            for (int i = 0, j = num - 1, count = 1; j < cadena.length(); j--) {
                nueva = nueva.concat(String.valueOf(cadena.charAt(j)));
                if (j == i) {
                    i += num;
                    count++;
                    j = num * count;
                }
            }
            System.out.println(nueva);
        }
        System.exit(0);
    }

}
