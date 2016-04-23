package pl.parser.nbp.currency;

/**
 * Created by PREZES on 2016-03-21.
 */
public class CurrencyFactory {

    public static Currency getCurrency(String currency) {


        CurrencyTypes currencyType = CurrencyTypes.valueOf(currency);

        switch (currencyType) {

            case USD:
                return new CurrencyUSD();

            case EUR:
                return new CurrencyEUR();

            case CHF:
                return new CurrencyCHF();

            case GBP:
                return new CurrencyGBP();

        }

        throw new ExceptionInInitializerError("Unknown currency");

    }

}
