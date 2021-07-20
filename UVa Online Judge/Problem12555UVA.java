import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Problem12555UVA {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int currentCase = 0;
        int totalCases = Integer.parseInt(in.readLine());

        while (currentCase++ < totalCases) {

            String input = in.readLine().trim();
            char[] inputInChars = input.toCharArray();

            int indexFirstChineseChar = 0;
            for (int i = 0; i < inputInChars.length; i++) {
                if (!Character.isDigit(inputInChars[i])) {
                    indexFirstChineseChar = i;
                    break;
                }
            }

            BigDecimal[] weights = new BigDecimal[]{
                    new BigDecimal(input.substring(0, indexFirstChineseChar)),
                    BigDecimal.valueOf(0.5),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO};

            int indexSecondNumber = -1;
            for (int i = indexFirstChineseChar; i < inputInChars.length; i++) {
                if (Character.isDigit(inputInChars[i])) {
                    indexSecondNumber = i;
                    break;
                }
            }


            if (indexSecondNumber > -1) {
                int indexFirstCharFromSecondChineseChar = 0;
                for (int i = indexSecondNumber; i < inputInChars.length; i++) {
                    if (!Character.isDigit(inputInChars[i])) {
                        indexFirstCharFromSecondChineseChar = i;
                        break;
                    }
                }

                weights[2] = new BigDecimal(input.substring(indexSecondNumber, indexFirstCharFromSecondChineseChar));
                weights[3] = BigDecimal.valueOf(0.05);
            }

            BigDecimal totalWeight = weights[0].multiply(weights[1]).add(weights[2].multiply(weights[3]));
            if (totalWeight.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
                System.out.println("Case " + currentCase + ": " + totalWeight.intValue());
            } else {
                System.out.println("Case " + currentCase + ": " + totalWeight.doubleValue());
            }
        }
    }
}
