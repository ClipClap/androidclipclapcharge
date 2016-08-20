package co.clipclap.pasta.objects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by josedavidmantilla on 1/14/16.
 */
public class DateParser {

    public static  final String [] dates= {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    public static final String [] months= {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public static Date getZeroTimeDate(Date fecha) {
        Date res = fecha;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( fecha );
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        res = calendar.getTime();

        return res;
    }

    public static Date addDays(Date date, int dias){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos


    }
    public static int getMonthOfTheYear(Date d){

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return  cal.get(Calendar.MONTH);
    }
    public static int getDayOfTheWeek(Date d){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String getDayS(Date date){
        return dates[getDayOfTheWeek(date) - 1];
    }
    public static String getDayI(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("d", new Locale("es_ES"));
        return dateFormat.format(date);
    }

    public static String getMonthS(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("d", new Locale("es_ES"));
        return months[getMonthOfTheYear(date)];
    }
    public static String getDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d yyyy", new Locale("es_ES"));
        String fecha = dateFormat.format(date);
        return dates[getDayOfTheWeek(date) - 1] + ", " + months[getMonthOfTheYear(date)] + " " + fecha;//dates[getDayOfTheWeek(date)-1]+", "+ ;
    }
}
