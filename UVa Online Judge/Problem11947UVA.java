import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Problem11947UVA {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int currentCase = 0;
        int numCases = Integer.parseInt(in.readLine());

        while (currentCase++ < numCases) {
            String firstDayLastMenstrualCycle = in.readLine();
            String dateOfBirth = getDateOfBirth(firstDayLastMenstrualCycle);
            String zodiacSign = getZodiacSign(Integer.parseInt(dateOfBirth.substring(0, 2)), Integer.parseInt(dateOfBirth.substring(3, 5)));

            System.out.println(currentCase + " " + dateOfBirth + " " + zodiacSign);
        }
    }

    private static String getZodiacSign(int month, int day) {
        switch (month) {
            case 1:
                return day < 21 ? "capricorn" : "aquarius";
            case 2:
                return day < 20 ? "aquarius" : "pisces";
            case 3:
                return day < 21 ? "pisces" : "aries";
            case 4:
                return day < 21 ? "aries" : "taurus";
            case 5:
                return day < 22 ? "taurus" : "gemini";
            case 6:
                return day < 22 ? "gemini" : "cancer";
            case 7:
                return day < 23 ? "cancer" : "leo";
            case 8:
                return day < 22 ? "leo" : "virgo";
            case 9:
                return day < 24 ? "virgo" : "libra";
            case 10:
                return day < 24 ? "libra" : "scorpio";
            case 11:
                return day < 23 ? "scorpio" : "sagittarius";
            default:
                return day < 23 ? "sagittarius" : "capricorn";
        }
    }

    private static String getDateOfBirth(String firstDayLastMenstrualCycle) {
        Calendar gregorianCalendar = new GregorianCalendar(Integer.parseInt(firstDayLastMenstrualCycle.substring(4)),
                Integer.parseInt(firstDayLastMenstrualCycle.substring(0, 2)) - 1,
                Integer.parseInt(firstDayLastMenstrualCycle.substring(2, 4)));

        gregorianCalendar.add(Calendar.DAY_OF_MONTH, 280);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/YYYY");
        return simpleDateFormat.format(gregorianCalendar.getTime());
    }
}
