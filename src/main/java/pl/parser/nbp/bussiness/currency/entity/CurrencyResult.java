package pl.parser.nbp.bussiness.currency.entity;

import com.google.common.collect.ImmutableCollection;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class CurrencyResult {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String currency;
    private ImmutableCollection<BigDecimal> values;
}

