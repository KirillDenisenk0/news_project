package myProject.web.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BirthdayFormatter {
    private static final String pattern = "yyyy-MM-dd";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    public static LocalDate parse(String date){
        return LocalDate.parse(date,formatter);
    }

    public static boolean isValidDate(String date){
        try {
            parse(date);
            return true;
        }
        catch (DateTimeParseException e ){
            return false;
        }
    }
}
