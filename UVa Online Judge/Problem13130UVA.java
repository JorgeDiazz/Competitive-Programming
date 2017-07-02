
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem13130UVA {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int numCasos =Integer.parseInt(in.readLine());
        while (numCasos-- > 0) {
            String cadena = in.readLine();
            String array[] = cadena.split(" ");
            int count = 0;
            for (int i = 0; i < array.length- 1; i++) {
                if (Integer.parseInt(array[i]) + 1 == Integer.parseInt(array[i + 1])) {
                    count++;
                } else {
                    break;
                }
            }
            System.out.println(count == array.length-1 ? "Y" : "N");
        }
        System.exit(0);
    }

}
