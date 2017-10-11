import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Problem482UVA {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int numCases = Integer.parseInt(in.readLine());
		StringBuilder output = new StringBuilder();
		while (numCases-- > 0) {
			in.readLine();

			HashMap<Integer, String> map = new HashMap<>();
			String[] dataIndex = in.readLine().split(" "), data = in.readLine().split(" ");

			for (int i = 0; i < data.length; i++)
				map.put(Integer.parseInt(dataIndex[i]), data[i]);

			for (int i = 1; i <= data.length; i++)
				output.append(map.get(i) + "\n");
			output.append("\n");

		}
		
		

		System.out.println(output.delete(output.length() - 2, output.length()));

	}
}
