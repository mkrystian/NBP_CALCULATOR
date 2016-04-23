package pl.parser.nbp.downloader;

import pl.parser.nbp.calendarPackage.NBPCalendar;

/**
 * Created by PREZES on 2016-03-21.
 */
public interface DocumentProviderInterface {

    String[] getDocument(NBPCalendar date);
}
