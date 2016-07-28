package pl.parser.nbp.bussiness.currency.control;

import pl.parser.nbp.bussiness.currency.entity.CurrencyResult;

public class AskHandler implements EventHandler<String> {
    public static final String HANDLE_KEY = "Ask";

    private final CurrencyResult result;

    public AskHandler(CurrencyResult result) {
        this.result = result;
    }

    @Override
    public void handle(String value) {
        result.getAsks().add(value);
    }
}
