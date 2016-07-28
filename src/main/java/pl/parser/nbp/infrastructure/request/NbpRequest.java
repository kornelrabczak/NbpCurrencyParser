package pl.parser.nbp.infrastructure.request;

import pl.parser.nbp.bussiness.currency.entity.CurrencyRequest;

public class NbpRequest extends CurrencyRequest {

    private static final String NBP_DEFAULT_API = "http://api.nbp.pl/api/exchangerates/c/%s/%s/%s/";

    @Override
    public String getSource() {
        return String.format(NBP_DEFAULT_API, getCurrencyUnit(), getStartDate(), getEndDate());
    }
}
