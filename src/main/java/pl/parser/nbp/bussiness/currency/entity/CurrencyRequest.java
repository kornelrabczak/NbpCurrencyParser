package pl.parser.nbp.bussiness.currency.entity;

import lombok.Getter;
import org.joda.money.CurrencyUnit;
import pl.parser.nbp.DateTimeUtilities;

import java.time.LocalDate;

@Getter
public abstract class CurrencyRequest {

    private CurrencyUnit currencyUnit;
    private LocalDate startDate;
    private LocalDate endDate;

    public CurrencyRequest currency(String currencyStringify) {
        currencyUnit = CurrencyUnit.of(currencyStringify);
        return this;
    }

    public CurrencyRequest startDate(String startDateStringify) {
        startDate = LocalDate.parse(startDateStringify, DateTimeUtilities.DATE_FORMATTER);
        return this;
    }

    public CurrencyRequest endDate(String endDateStringify) {
        endDate = LocalDate.parse(endDateStringify, DateTimeUtilities.DATE_FORMATTER);
        return this;
    }

    public abstract String getSource();
}
