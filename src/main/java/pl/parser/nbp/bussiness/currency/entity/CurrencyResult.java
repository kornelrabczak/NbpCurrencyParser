package pl.parser.nbp.bussiness.currency.entity;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_DOWN;
import static java.math.RoundingMode.HALF_UP;

@Value
public class CurrencyResult {
    private static final int BIG_DECIMAL_SCALE = 4;

    private final List<BigDecimal> bids;
    private final List<BigDecimal> asks;

    public BigDecimal averageBids() {
        return computeAverage(bids);
    }

    public BigDecimal averageAsks() {
        return computeAverage(asks);
    }

    public BigDecimal stdev(List<BigDecimal> list, BigDecimal average) {
        final BigDecimal y = list.stream()
                .map(x -> x.subtract(average).pow(2))
                .reduce(BigDecimal::add).get();

        final double sqrt = Math.sqrt(y.divide(new BigDecimal(list.size()), ROUND_HALF_DOWN).doubleValue());
        return new BigDecimal(sqrt).setScale(BIG_DECIMAL_SCALE, HALF_UP);
    }

    private BigDecimal computeAverage(List<BigDecimal> list) {
        final BigDecimal total = list.stream().reduce(BigDecimal::add).get();
        return total.divide(new BigDecimal(list.size()), ROUND_HALF_DOWN);
    }
}

