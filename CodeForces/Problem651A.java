import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem651A {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(in.readLine(), " ");
		System.out.println(getTime(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()), 0));
	}

	private static int getTime(int a1, int a2, int time) {
		if(a1 < 0 || a2 < 0) return time - 1;
		if(a1 == 0 || a2 == 0) return time;
		if(a1 < a2) return getTime(a1 + 1, a2 - 2, time + 1);
		return getTime(a1 - 2, a2 + 1, time + 1);
	}

}
