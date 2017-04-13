package katas.stringcalculator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String number){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(number);
        int total = 0;
        while (matcher.find()) {
            total += Integer.valueOf(matcher.group());
        }
        return total;
    }
}
