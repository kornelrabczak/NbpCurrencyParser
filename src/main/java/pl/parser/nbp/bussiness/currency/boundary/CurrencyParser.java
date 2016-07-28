package pl.parser.nbp.bussiness.currency.boundary;

import pl.parser.nbp.bussiness.currency.entity.CurrencyResult;

public interface CurrencyParser {
    CurrencyResult parse(DataProvider dataProvider);
}
