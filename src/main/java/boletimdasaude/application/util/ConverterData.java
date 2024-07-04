package boletimdasaude.application.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConverterData {

    public static String toMes(Date data) {
        DateFormat formato = new SimpleDateFormat("dd de MMMM yyyy", Locale.getDefault());
        formato.setCalendar(Calendar.getInstance(Locale.getDefault()));

        return formato.format(data);
    }

    public static int toAno(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        return calendar.get(Calendar.YEAR);
    }

}
