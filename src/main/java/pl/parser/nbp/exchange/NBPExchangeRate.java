package pl.parser.nbp.exchange;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import pl.parser.nbp.calendarPackage.NBPCalendar;
import pl.parser.nbp.currency.Currency;
import pl.parser.nbp.downloader.DocumentProviderInterface;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by PREZES on 2016-03-22.
 */
public class NBPExchangeRate extends ExchangeRate {


    public NBPExchangeRate(DocumentProviderInterface documentProvider) {
        this.documentProvider = documentProvider;
    }


    @Override
    public ExchangeRateContainer getExchangeRateInDay(Currency currency, NBPCalendar date) {

        String[] document = documentProvider.getDocument(date);
        if (document == null) return null;


        return getRate(currency, document);
    }


    private ExchangeRateContainer getRate(Currency currency, String[] XMLDocument) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        ExchangeRateContainer rates = new ExchangeRateContainer();

        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;


        try {
            document = builder.parse(new InputSource(new StringReader(XMLDocument[0])));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        NodeList nList = document.getElementsByTagName("pozycja");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element eElement = (Element) nNode;
            if (eElement.getElementsByTagName("kod_waluty").item(0).getTextContent().equals(currency.toString())) {


                rates.buyRate = eElement.getElementsByTagName("kurs_kupna").item(0).getTextContent();
                rates.sellRate = eElement.getElementsByTagName("kurs_sprzedazy").item(0).getTextContent();
            }

        }

        try {
            document = builder.parse(new InputSource(new StringReader(XMLDocument[1])));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        nList = document.getElementsByTagName("pozycja");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element eElement = (Element) nNode;
            if (eElement.getElementsByTagName("kod_waluty").item(0).getTextContent().equals("EUR")) {


                rates.averageRate = eElement.getElementsByTagName("kurs_sredni").item(0).getTextContent();

            }

        }
        return rates;


    }

}
