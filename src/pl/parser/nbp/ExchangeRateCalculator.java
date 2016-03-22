package pl.parser.nbp;

/**
 * Created by PREZES on 2016-03-22.
 */
public interface ExchangeRateCalculator {

    public double meanBuyValueInDates( String currency, String date1, String date2);

    public double standardDeviationSellValueInDates( String currency, String date1, String date2);
}
