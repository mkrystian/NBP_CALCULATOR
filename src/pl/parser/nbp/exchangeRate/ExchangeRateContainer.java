package pl.parser.nbp.exchangeRate;

/**
 * Created by PREZES on 2016-03-22.
 */
public class ExchangeRateContainer {

    public String sellRate;
    public String buyRate;
    public String avarageRate;

    public double getDoubleSellRate(){
        return Double.valueOf(sellRate.replace( "," , "."));
    }

    public double getDoubleBuyRate(){
        return Double.valueOf(buyRate.replace( "," , "."));
    }

    public double getDoubleAvarageRate(){
        return Double.valueOf(avarageRate.replace( "," , "."));

    }
}
