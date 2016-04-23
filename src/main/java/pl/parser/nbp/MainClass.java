package pl.parser.nbp;


import java.text.DecimalFormat;

public class MainClass {

    public static void main(String[] args) {

        // Expected args format: currency (USD/CHF/EUR/GBP) startDate (2013-01-28) endDate (2013-01-31)

        String currency = args[0];
        String date1 = args[1];
        String date2 = args[2];


        DecimalFormat df = new DecimalFormat("#.####");

        ExchangeRateCalculator calculator = new NBPExchangeRateCalculator();
        Double mean = calculator.meanBuyValueInDates(currency, date1, date2);
        Double standardDeviation = calculator.standardDeviationSellValueInDates(currency, date1, date2);


        System.out.println(df.format(mean));
        System.out.print(df.format(standardDeviation));

    }
}
