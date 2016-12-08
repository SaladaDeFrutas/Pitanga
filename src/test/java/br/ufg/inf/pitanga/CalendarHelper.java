package br.ufg.inf.pitanga;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarHelper {

    public static Calendar converteStringParaCalendar(String data, String formato) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
        Calendar dataCalendar = Calendar.getInstance();
        try {
            dataCalendar.setTime(simpleDateFormat.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataCalendar;
    }
}
