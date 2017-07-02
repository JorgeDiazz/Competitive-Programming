
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

 class Problem10038UVA {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        String cadena;
        while ((cadena = in.readLine())!= null) {
            cadena = cadena.trim();
            String casos[] = cadena.split(" ");
            if(!casos[0].equals("")){
            int cantNums = Integer.parseInt(casos[0]);
            int array[] = new int[cantNums];
            for (int i = 0; i < cantNums; i++) 
                array[i] = Integer.parseInt(casos[i+1]);
            for (int i = 0; i < array.length - 1; i++) 
                array[i] = Math.abs(array[i] - array[i + 1]);
            array[array.length - 1] = 1000000000;
            Arrays.sort(array);
            boolean jolly = true;
            for (int i = 0; i < array.length - 2; i++) {
                if (array[i] + 1 != array[i + 1]) {
                    jolly = false;
                    break;
                }
            }
            System.out.println(jolly && (array[0] == 1 || array.length == 1)
                    ? "Jolly" : "Not jolly");
            }
        }
        System.exit(0);
    }
}
