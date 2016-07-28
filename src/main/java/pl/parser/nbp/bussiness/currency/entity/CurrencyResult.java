package pl.parser.nbp.bussiness.currency.entity;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class CurrencyResult {
    private final List<String> bids = new ArrayList<>();
    private final List<String> asks = new ArrayList<>();
}

