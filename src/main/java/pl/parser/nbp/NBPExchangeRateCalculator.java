package pl.parser.nbp;

import pl.parser.nbp.calendarPackage.NBPCalendar;
import pl.parser.nbp.currency.Currency;
import pl.parser.nbp.currency.CurrencyFactory;
import pl.parser.nbp.downloader.NBPDocumentProvider;
import pl.parser.nbp.exchange.ExchangeRate;
import pl.parser.nbp.exchange.ExchangeRateContainer;
import pl.parser.nbp.exchange.NBPExchangeRate;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by PREZES on 2016-03-22.
 */
public class NBPExchangeRateCalculator implements ExchangeRateCalculator {


    @Override
    public double meanBuyValueInDates(String currency, String date1, String date2) {

        double mean = 0;
        int count = 0;
        NBPCalendar firstDay = new NBPCalendar(date1);
        NBPCalendar secondDay = new NBPCalendar(date2);
        Currency usedCurrency = CurrencyFactory.getCurrency(currency);
        LinkedList<NBPCalendar> dates = firstDay.getDatesBeetwen(secondDay);

        LinkedList<Double> buyValues = new LinkedList<Double>();

        ExchangeRate exchangeInstance = new NBPExchangeRate(new NBPDocumentProvider());

        ListIterator<NBPCalendar> iterator = dates.listIterator();

        ExchangeRateContainer tmp;

        while (iterator.hasNext()) {

            tmp = exchangeInstance.getExchangeRateInDay(usedCurrency, iterator.next());
            if (tmp != null) {
                mean = mean + tmp.getDoubleBuyRate();
                count++;

            }


        }

        if (count != 0) {
            return mean / count;
        } else {
            return 0;
        }

    }

    @Override
    public double standardDeviationSellValueInDates(String currency, String date1, String date2) {
        double mean = meanSellValueInDates(currency, date1, date2);
        int count = 0;
        double deviation = 0;
        NBPCalendar firstDay = new NBPCalendar(date1);
        NBPCalendar secondDay = new NBPCalendar(date2);
        Currency usedCurrency = CurrencyFactory.getCurrency(currency);
        LinkedList<NBPCalendar> dates = firstDay.getDatesBeetwen(secondDay);

        LinkedList<Double> buyValues = new LinkedList<Double>();

        ExchangeRate exchangeInstance = new NBPExchangeRate(new NBPDocumentProvider());

        ListIterator<NBPCalendar> iterator = dates.listIterator();

        ExchangeRateContainer tmp;

        while (iterator.hasNext()) {

            tmp = exchangeInstance.getExchangeRateInDay(usedCurrency, iterator.next());
            if (tmp != null) {
                deviation += (tmp.getDoubleSellRate() - mean) * (tmp.getDoubleSellRate() - mean);
                count++;
            }


        }


        if (count != 0) {
            deviation /= count;
            deviation = Math.sqrt(deviation);

            return deviation;
        } else {
            return 0;
        }
    }

    private double meanSellValueInDates(String currency, String date1, String date2) {

        double mean = 0;
        int count = 0;
        NBPCalendar firstDay = new NBPCalendar(date1);
        NBPCalendar secondDay = new NBPCalendar(date2);
        Currency usedCurrency = CurrencyFactory.getCurrency(currency);
        LinkedList<NBPCalendar> dates = firstDay.getDatesBeetwen(secondDay);

        LinkedList<Double> buyValues = new LinkedList<Double>();

        ExchangeRate exchangeInstance = new NBPExchangeRate(new NBPDocumentProvider());

        ListIterator<NBPCalendar> iterator = dates.listIterator();

        ExchangeRateContainer tmp;

        while (iterator.hasNext()) {

            tmp = exchangeInstance.getExchangeRateInDay(usedCurrency, iterator.next());
            if (tmp != null) {
                mean = mean + tmp.getDoubleSellRate();
                count++;

            }


        }

        if (count != 0) {
            return mean / count;
        } else {
            return 0;
        }

    }
}
