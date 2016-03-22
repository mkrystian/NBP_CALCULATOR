package pl.parser.nbp.currencyPackage;

/**
 * Created by PREZES on 2016-03-21.
 */
public abstract class Currency {



    protected CurrencyTypes myCurrency;

    public CurrencyTypes getMyCurrency(){
        return myCurrency;
    }


    @Override
    public String toString(){
        return myCurrency.toString();
    }


}
