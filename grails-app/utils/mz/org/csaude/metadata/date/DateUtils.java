package mz.org.csaude.metadata.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class DateUtils {
    public static final String YEAR_FORMAT="YYYY";
    public static final String DAY_FORMAT="DD";
    public static final String MONTH_FORMAT="MM";
    public static final String HOUR_FORMAT="HH";
    public static final String SECOND_FORMAT="ss";
    public static final String MINUTE_FORMAT="mm";
    public static final String MILLISECOND_FORMAT="SSS";
    public static final String DDMM_DATE_FORMAT ="dd-MM-yyyy";
    public static final String MMDD_DATE_FORMAT="MM-dd-yyyy";
    public static final String DATE_TIME_FORMAT="dd-MM-yyyy HH:mm:ss";



    public static Date createDate(String stringDate, String dateFormat) {
        try {
            SimpleDateFormat sDate = new SimpleDateFormat(dateFormat);
            Date date = sDate.parse(stringDate);

            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
