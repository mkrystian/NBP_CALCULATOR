package pl.parser.nbp.nbpDownloader;

import pl.parser.nbp.calendarPackage.NBPCalendar;

import java.util.Date;

/**
 * Created by PREZES on 2016-03-21.
 */
public interface DocumentProviderInterface {

    public String[] getDocument( NBPCalendar date );
}
