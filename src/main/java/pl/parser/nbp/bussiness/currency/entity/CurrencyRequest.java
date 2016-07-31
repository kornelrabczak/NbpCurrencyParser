package pl.parser.nbp.bussiness.currency.entity;

import lombok.Getter;
import org.joda.money.CurrencyUnit;
import pl.parser.nbp.DateTimeUtilities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
        if (endDate != null && startDate.isAfter(endDate))
            throw new IllegalStateException("Start date cannot be after the end date.");
        if (endDate != null && ChronoUnit.DAYS.between(startDate, endDate) > 367)
            throw new IllegalStateException("Limit of 367 days has been exceeded");
        return this;
    }

    public CurrencyRequest endDate(String endDateStringify) {
        endDate = LocalDate.parse(endDateStringify, DateTimeUtilities.DATE_FORMATTER);
        if (startDate != null && endDate.isBefore(startDate))
            throw new IllegalStateException("End date cannot be before the start date.");
        if (startDate != null && ChronoUnit.DAYS.between(startDate, endDate) > 367)
            throw new IllegalStateException("Limit of 367 days has been exceeded");
        return this;
    }

    public abstract String getSource();
}
