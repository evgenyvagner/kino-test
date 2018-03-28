package lv.iljapavlovs.cucumber.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateUtil {

    public static LocalDate convertToLocalDate(String stringDate, String pattern) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(pattern)
                //in order to convert date, year is obligatory
                .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
                .toFormatter();

        return LocalDate.parse(stringDate, formatter);

    }
}
