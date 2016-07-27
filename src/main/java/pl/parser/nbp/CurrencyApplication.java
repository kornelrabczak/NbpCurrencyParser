package pl.parser.nbp;

import org.joda.money.CurrencyUnit;

import java.time.LocalDate;

public class CurrencyApplication {

    private CurrencyUnit currencyUnit;
    private LocalDate startDate;
    private LocalDate endDate;

    public CurrencyApplication currency(String currencyStringify) {
        currencyUnit = CurrencyUnit.of(currencyStringify);
        return this;
    }

    public CurrencyApplication startDate(String startDateStringify) {
        startDate = LocalDate.parse(startDateStringify, DateTimeUtilities.DATE_FORMATTER);
        return this;
    }

    public CurrencyApplication endDate(String endDateStringify) {
        startDate = LocalDate.parse(endDateStringify, DateTimeUtilities.DATE_FORMATTER);
        return this;
    }

    public void run() {

    }
}
