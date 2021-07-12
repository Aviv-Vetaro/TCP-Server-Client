import java.util.HashMap;
import java.util.HashSet;

public class ForexRate {
    //this is the chosen mediator currency. each convert will first go trough said currency
    private static String mediatorCurrency = "$";
    private HashMap<String, Double> forexRateToMediatorCurrency;
    public ForexRate()
    {
        //this is the exchange rate from the mediator currency (the dollar) to various currencies
        forexRateToMediatorCurrency = new HashMap<>();
        forexRateToMediatorCurrency.put("$", 1.0);
        forexRateToMediatorCurrency.put("£", 0.71);
        forexRateToMediatorCurrency.put("¥", 33.72);
        forexRateToMediatorCurrency.put("€", 0.82);
        forexRateToMediatorCurrency.put("₪", 3.24);
    }

    /**
     *
     * @param from the currency to be converted from
     * @param to the currency to be converted to
     * @return the amount of the destination currency that can be exchanged for 1 unit of the origin currency
     */
    public double rate(String from, String to)
    {

        double amountInMediatorCurrency = forexRateToMediatorCurrency.get(from);
        double rateInMediatorCurrency = 1.0 / forexRateToMediatorCurrency.get(to);
        return 1.0 / (amountInMediatorCurrency  * rateInMediatorCurrency);
    }
}
