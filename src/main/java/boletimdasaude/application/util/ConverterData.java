package boletimdasaude.application.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConverterData {

    public static String toData(Date data) {
        DateFormat formato = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        formato.setCalendar(Calendar.getInstance(Locale.getDefault()));

        return formato.format(data);
    }

    public static int toDia(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int toDia(String dataString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(dataString, formatter);
        return data.getDayOfMonth();
    }

    public static int toMes(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int toMes(String dataString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(dataString, formatter);
        return data.getMonthValue();
    }


    public static int toAno(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        return calendar.get(Calendar.YEAR);
    }

    public static int toAno(String dataString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(dataString, formatter);
        return data.getYear();
    }

}
