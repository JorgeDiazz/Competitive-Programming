
import java.io.BufferedReader;
import java.io.IOException;


 class Problem11172UVA {
	 
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new java.io.InputStreamReader(System.in));
        
        
        int numCasos = Integer.parseInt(in.readLine());
        while(numCasos-- > 0){
            
        String array[] = in.readLine().split(" ");
        if(array[0].equals(array[1]))
                System.out.println("=");
        else if(Integer.parseInt(array[0]) > Integer.parseInt(array[1]))
                System.out.println(">");
        else System.out.println("<");
        
        }
        
        System.exit(0);
        
    }
    
}
