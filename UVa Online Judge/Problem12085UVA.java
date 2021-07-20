import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem12085UVA {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int numCase = 1;
        String input;
        while (!(input = in.readLine()).equals("0")) {
            System.out.println("Case " + (numCase++) + ":");

            int totalNums = Integer.parseInt(input) - 1;

            long minPhoneNumber = Long.parseLong(in.readLine());
            long previousPhoneNumber = minPhoneNumber;
            boolean resultPrinted = false;
            long currentPhoneNumber;

            while (totalNums-- > 0) {
                currentPhoneNumber = Long.parseLong(in.readLine());
                if (previousPhoneNumber + 1 == currentPhoneNumber) {
                    resultPrinted = false;
                } else {
                    printAnswer(minPhoneNumber, previousPhoneNumber);
                    minPhoneNumber = currentPhoneNumber;
                    resultPrinted = true;

                    if (totalNums == 0) {
                        System.out.println("0" + minPhoneNumber);
                    }
                }

                previousPhoneNumber = currentPhoneNumber;

            }

            if (!resultPrinted) {
                printAnswer(minPhoneNumber, previousPhoneNumber);
            }

            System.out.println();
        }

    }

    private static void printAnswer(long minPhoneNumber, long previousPhoneNumber) {
        if (minPhoneNumber < previousPhoneNumber) {
            System.out.println("0" + minPhoneNumber + "-" + getLastNumbersDiff(minPhoneNumber, previousPhoneNumber));
        } else {
            System.out.println("0" + minPhoneNumber);
        }
    }

    private static String getLastNumbersDiff(long prevNumber, long nextNumber) {
        String firstNumber = String.valueOf(prevNumber);
        String secondNumber = String.valueOf(nextNumber);

        String lastNumbersDiff = "";
        for (int i = 0; i < firstNumber.length(); i++) {
            if (firstNumber.charAt(i) != secondNumber.charAt(i)) {
                lastNumbersDiff = secondNumber.substring(i);
                break;
            }
        }

        return lastNumbersDiff;
    }
}