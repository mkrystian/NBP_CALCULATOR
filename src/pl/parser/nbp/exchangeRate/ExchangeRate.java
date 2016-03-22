package pl.parser.nbp.exchangeRate;

import pl.parser.nbp.calendarPackage.NBPCalendar;
import pl.parser.nbp.currencyPackage.Currency;
import pl.parser.nbp.nbpDownloader.DocumentProviderInterface;

/**
 * Created by PREZES on 2016-03-22.
 */
public abstract class ExchangeRate {

    protected DocumentProviderInterface documentProvider;

    public abstract ExchangeRateContainer getExchangeRateInDay( Currency currency , NBPCalendar date);

    public DocumentProviderInterface getDocumentProvider() {
        return documentProvider;
    }

    public void setDocumentProvider(DocumentProviderInterface documentProvider) {
        this.documentProvider = documentProvider;
    }
}
