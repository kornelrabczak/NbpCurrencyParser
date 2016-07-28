package pl.parser.nbp.bussiness.currency.control;

import pl.parser.nbp.bussiness.currency.entity.CurrencyResult;

public class BidHandler implements EventHandler<String> {
    public static final String HANDLE_KEY = "Bid";

    private final CurrencyResult result;

    public BidHandler(CurrencyResult result) {
        this.result = result;
    }

    @Override
    public void handle(String value) {
        result.getBids().add(value);
    }
}
