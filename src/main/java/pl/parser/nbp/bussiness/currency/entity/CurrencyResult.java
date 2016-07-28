package pl.parser.nbp.bussiness.currency.entity;

import lombok.Value;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Value
public class CurrencyResult {
    private final List<BigDecimal> bids = new ArrayList<>();
    private final List<BigDecimal> asks = new ArrayList<>();
}

