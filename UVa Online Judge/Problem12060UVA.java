import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

class Problem12060UVA {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int count = 1;
		String input;
		StringTokenizer token;
		while ((input = in.readLine()).charAt(0) != '0') {

			token = new StringTokenizer(input);

			int totalNumbers = Integer.parseInt(token.nextToken());
			int[] numbers = new int[totalNumbers];

			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = Integer.parseInt(token.nextToken());
			}

			int num = Arrays.stream(numbers).sum();
			int den = totalNumbers;

			System.out.println("Case " + (count++) + ":");

			int gcd = BigInteger.valueOf(num).gcd(BigInteger.valueOf(den)).intValue();
			num /= gcd;
			den /= gcd;

			float intValue = (float) num / (float) den;
			boolean negative = intValue < 0;
			intValue = Math.abs(intValue);

			num %= den;

			if (num == 0) {
				System.out.println(getFormatIntValue((int) intValue, negative, true));
			} else {
				buildFraction(getFormatIntValue((int) intValue, negative, false), Math.abs(num), den, negative);
			}
		}
	}

	private static void buildFraction(String strIntValue, int num, int den, boolean negative) {

		String strNum = String.valueOf(num);
		String strDen = String.valueOf(den);

		int totalLines = Math.max(strNum.length(), strDen.length());
		String lines = "----------------------";

		System.out.printf("%" + (strIntValue.length() + totalLines) + "s\n", num);
		System.out.println(strIntValue + lines.substring(0, totalLines));
		System.out.printf("%" + (strIntValue.length() + totalLines) + "s\n", den);
	}

	private static String getFormatIntValue(float intValue, boolean negative, boolean zeroPermited) {
		return (negative ? "- " : "") + (intValue == 0 && !zeroPermited ? "" : (int) intValue);
	}
}
