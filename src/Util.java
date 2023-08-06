import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	private static final Pattern BN_PATTERN = Pattern.compile("^(\\d\\d)(\\d\\d)(\\d\\d)[/]?(\\d\\d\\d)(\\d?)$");

    public static boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) return false;
        return name.matches("\\w+\\.?");
    }

    public static boolean validateBirthNumber(String birthNumber) {
        if (birthNumber == null || birthNumber.isEmpty()) return false;
        Matcher matcher = BN_PATTERN.matcher(birthNumber);
        boolean valid = matcher.matches();
        if (valid) {
            matcher.reset();
            String yearStr = null, monthStr = null, dayStr = null, extStr = null, cStr = "";
            int year = 0, month = 0, c = 0;
            boolean cParsed = false;
            while (matcher.find()) {
                yearStr = matcher.group(1);
                monthStr = matcher.group(2);
                dayStr = matcher.group(3);
                extStr = matcher.group(4);
                year = Integer.parseInt(yearStr);
                month = Integer.parseInt(monthStr);
                if (matcher.groupCount() > 4 && !matcher.group(5).trim().isEmpty()) {
                    cStr = matcher.group(5);
                    c = Integer.parseInt(cStr);
                    cParsed = true;
                }
            }

            if (!cParsed || (extStr + cStr).equals("9999")) {
                if (!cParsed) {
                    valid = year < 54;
                }
            } else {
                Integer firstNineDigits = Integer.valueOf(yearStr + monthStr + dayStr + extStr);
                int mod = firstNineDigits.intValue() % 11;

                if (mod == 10) {
                    mod = 0;
                }
                valid = (c == mod);
            }
            
            if (valid) {
                year += year < 54 ? 2000 : 1900;
                yearStr = year + "";
                if (month > 70 && year > 2003) {
                    month -= 70;
                } else if (month > 50) {
                    month -= 50;
                } else if (month > 20 && year > 2003) { 
                    month -= 20;
                }
                monthStr = month + "";
                if (monthStr.length() == 1) {
                    monthStr = "0" + monthStr;
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                format.setLenient(false);
                try {
                    format.parse(yearStr + "-" + monthStr + "-" + dayStr);
                } catch (ParseException ex) {
                    valid = false;
                }
            }
        }
        return valid;
    }
}
