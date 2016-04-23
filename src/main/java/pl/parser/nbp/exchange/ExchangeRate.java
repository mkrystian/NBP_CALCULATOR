package pl.parser.nbp.exchange;

import pl.parser.nbp.calendarPackage.NBPCalendar;
import pl.parser.nbp.currency.Currency;
import pl.parser.nbp.downloader.DocumentProviderInterface;

/**
 * Created by PREZES on 2016-03-22.
 */
public abstract class ExchangeRate {

    protected DocumentProviderInterface documentProvider;

    public abstract ExchangeRateContainer getExchangeRateInDay(Currency currency, NBPCalendar date);

    public DocumentProviderInterface getDocumentProvider() {
        return documentProvider;
    }

    public void setDocumentProvider(DocumentProviderInterface documentProvider) {
        this.documentProvider = documentProvider;
    }
}
