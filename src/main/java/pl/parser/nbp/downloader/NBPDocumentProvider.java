package pl.parser.nbp.downloader;

import pl.parser.nbp.calendarPackage.NBPCalendar;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by PREZES on 2016-03-21.
 */
public class NBPDocumentProvider implements DocumentProviderInterface {

    private static String address = "http://www.nbp.pl/kursy/xml/";

    private Map<String, String[]> filenameMap = new HashMap<>();

    @Override
    public String[] getDocument(NBPCalendar date) {
        String filename = findFilename(date);
        String[] Documents = new String[2];
        if (filename == null) return null;

        Documents[0] = downloadFile("http://www.nbp.pl/kursy/xml/" + "c" + filename + ".xml");
        Documents[1] = downloadFile("http://www.nbp.pl/kursy/xml/" + "a" + filename + ".xml");

        return Documents;


    }

    private String findFilename(NBPCalendar date) {

        String[] filename = getFilenameList(date);

        for (String val : filename) {
            if (val.substring(7).equals(date.getMonth() + date.getDay())) return val.substring(1);
        }


        return null;
    }

    private void downloadFilenameList(NBPCalendar date) {
        String address;
        String content;
        if (date.isYearCurrent()) {
            address = "http://www.nbp.pl/kursy/xml/dir.txt";
        } else {
            address = "http://www.nbp.pl/kursy/xml/dir" + date.getYear() + ".txt";
        }

        content = downloadFile(address);


        filenameMap.put(date.getYear(), content.split("\r\n"));
    }

    private String[] getFilenameList(NBPCalendar date) {

        if (!filenameMap.containsKey(date.getYear())) {
            downloadFilenameList(date);
        }

        return filenameMap.get(date.getYear());


    }

    private String downloadFile(String address) {

        String content = null;
        try {
            content = new Scanner(new URL(address).openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

}
