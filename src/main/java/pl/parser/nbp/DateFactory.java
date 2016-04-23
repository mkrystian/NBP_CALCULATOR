package pl.parser.nbp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PREZES on 2016-03-21.
 */
public class DateFactory {

    public static SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-mm-dd");

    public static Date getDate(String stringDate) {
        Date date = null;

        try {
            date = simpleDate.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }

}
