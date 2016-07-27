package pl.parser.nbp.bussiness.currency.boundary;

import pl.parser.nbp.bussiness.currency.entity.CurrencyResult;

public abstract class CurrencyParser {

    protected final String source;

    public CurrencyParser(String source) {
        this.source = source;
    }

    public abstract CurrencyResult parse(DataProvider dataProvider);
}
