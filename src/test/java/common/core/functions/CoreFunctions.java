package common.core.functions;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import java.util.HashMap;
import java.util.Iterator;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Ostermiller.util.RandPass;

public class CoreFunctions {

    public CoreFunctions() {}

    /**
     * Generator for Strings
     * @param lang
     * @param length
     * @return
     */

    public static String generateString(String lang, int length) {
        RandPass stringGenerator;
        if(lang.equals("numbers")) {
            stringGenerator = new RandPass(new char[]{'1','2','3','4','5','6','7','8','9'});
        } else if(lang.equals("specChars")) {
            stringGenerator = new RandPass(new char[]{  '-','+','/','~','!','@','#','$','%','^','&',
                                                        '*','(',')','_',':','<','>','?','.','[',']'});
        } else if(lang.equals("mix")) {
            stringGenerator = new RandPass(com.Ostermiller.util.RandPass.NUMBERS_AND_LETTERS_ALPHABET);
        } else if(lang.equals("sms")) {
            String sms = String.valueOf(com.Ostermiller.util.RandPass.NUMBERS_AND_LETTERS_ALPHABET);
            sms += " _;:<>|!@#$%^&*()+=-\\/'?,.[]{}";
            stringGenerator = new RandPass(sms.toCharArray());
        } else {
            stringGenerator = new RandPass(com.Ostermiller.util.RandPass.LETTERS_ALPHABET);
        }

        String resultString = stringGenerator.getPass(length);
        resultString = resultString.substring(0,1).toUpperCase() + resultString.substring(1);

        return resultString;
    }

    public static Object getKeyByValue(HashMap map, Object value) {
        return getKeyByValue(map, value, "");
    }

    public static Object getKeyByValue(HashMap map, Object value, String keyStartsWith) {
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if((value != null ? value.equals(entry.getValue()) : entry.getValue() == null) && (entry.getKey() == null ||
                    entry.getKey().toString().toLowerCase().startsWith(keyStartsWith.toLowerCase())))

                return entry.getKey();
        }

        return null;
    }

    public static double roundDouble(String value) {
        return roundDouble(value, 2);
    }

    public static double roundDouble(String value, int decimalPlace) {
        if(value.contains(",") && value.contains("."))
            value = value.replace(",", "");
        value = value.replace(",", ".").replaceAll("[^0-9.-E]", "");

        return roundDouble(Double.parseDouble(value), decimalPlace);
    }

    public static double roundDouble(double value) {
        return roundDouble(value, 2);
    }

    public static double roundDouble(double value, int decimalPlace) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(decimalPlace, 4);
        value = bd.doubleValue();

        return value;
    }

    public static String formatDouble(String amount) {
        return formatDouble(roundDouble(amount));
    }

    public static String formatDouble(String amount, String mask) {
        String mask_parts[] = mask.split("\\.");
        if(mask_parts.length > 1)
            return formatDouble(roundDouble(amount, mask_parts[1].length()), mask);
        else
            return formatDouble(roundDouble(amount), mask);
    }

    public static String formatDouble(double amount) {
        return formatDouble(amount, "0.00");
    }

    public static String formatDouble(double amount, String mask) {
        return (new DecimalFormat(mask)).format(amount).replace(",", ".");
    }

    public static String trimWhitespaces(String RAW) {
        String trimmed = RAW == null ? null : RAW.replaceAll("\\p{Z}+", " ").trim();
        return trimmed;
    }

    public static String removeWhitespaces(String RAW) {
        String trimmed = trimWhitespaces(RAW);
        trimmed = trimmed.replaceAll(" ", "");

        return trimmed;
    }

    public static Enum getRandomEnumValue(Class enumClass) {
        int x = (new Random()).nextInt(((Enum[])enumClass.getEnumConstants()).length);
        return ((Enum[])enumClass.getEnumConstants())[x];
    }

    public static String uppercaseFirstLetter(String string) {
        Pattern spaces = Pattern.compile("\\s+[a-z]");
        Matcher m = spaces.matcher(string);
        StringBuilder capitalWordBuilder = new StringBuilder(string.substring(0,1).toUpperCase());
        int prevStart = 1;
        while(m.find()) {
            capitalWordBuilder.append(string.substring(prevStart, m.end() - 1));
            capitalWordBuilder.append(string.substring(m.end() - 1, m.end()).toUpperCase());
            prevStart = m.end();
        }
        capitalWordBuilder.append(string.substring(prevStart, string.length()));

        return capitalWordBuilder.toString();
    }

    public static int getRundomNumber(int number) {
        Random random = new Random();
        int randomNumber = random.nextInt(number);
        if(randomNumber == 0) {
            randomNumber = randomNumber + 1;
        }

        return randomNumber;
    }

}
