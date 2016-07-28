package pl.parser.nbp;

import org.joda.money.IllegalCurrencyException;
import org.junit.Test;
import pl.parser.nbp.bussiness.currency.entity.CurrencyRequest;

import java.time.format.DateTimeParseException;

public class CurrencyRequestTest {

    private CurrencyRequest sut = new CurrencyRequest() {
        @Override
        public String getSource() {
            return "empty";
        }
    };

    @Test(expected = NullPointerException.class)
    public void should_fail_for_null_currency() {
        // given
        String currency = null;

        // when
        sut.currency(currency);
    }

    @Test(expected = IllegalCurrencyException.class)
    public void should_fail_for_not_existing_currency() {
        // given
        String currency = "ZXC";

        // when
        sut.currency(currency);
    }

    @Test
    public void should_pass_for_real_currency() {
        // given
        String currency = "PLN";

        // when
        sut.currency(currency);
    }

    @Test(expected = NullPointerException.class)
    public void should_fail_for_null_start_date() {
        // given
        String startDate = null;

        // when
        sut.startDate(startDate);
    }

    @Test(expected = DateTimeParseException.class)
    public void should_fail_for_wrong_format_end_date() {
        // given
        String startDate = "01-01-1234xx";

        // when
        sut.startDate(startDate);
    }

    @Test(expected = NullPointerException.class)
    public void should_fail_for_null_end_date() {
        // given
        String endDate = null;

        // when
        sut.endDate(endDate);
    }

    @Test(expected = DateTimeParseException.class)
    public void should_fail_for_wrong_format_start_date() {
        // given
        String endDate = "01-01-1234";

        // when
        sut.endDate(endDate);
    }

    @Test
    public void should_pass_for_proper_start_date() {
        // given
        String startDate = "2016-01-18";

        // when
        sut.startDate(startDate);
    }

    @Test
    public void should_pass_for_proper_endDate_date() {
        // given
        String endDate = "2016-01-18";

        // when
        sut.endDate(endDate);
    }

}