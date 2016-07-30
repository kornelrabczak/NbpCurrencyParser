package pl.parser.nbp.bussiness.currency.control;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BigDecimalListHandler implements EventHandler<String> {
    private final List<BigDecimal> result = new ArrayList<>();

    @Override
    public void handle(String value) {
        result.add(new BigDecimal(value));
    }
}
