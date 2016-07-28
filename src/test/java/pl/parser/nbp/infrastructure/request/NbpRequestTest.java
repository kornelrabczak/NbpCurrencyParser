package pl.parser.nbp.infrastructure.request;

import org.junit.Test;
import pl.parser.nbp.bussiness.currency.entity.CurrencyRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class NbpRequestTest {

    @Test
    public void should_create_source_url() {
        // given
        final CurrencyRequest request = new NbpRequest().currency("USD").startDate("2016-04-12").endDate("2016-04-15");

        // when
        final String actual = request.getSource();

        // then
        assertThat(actual).isEqualTo("http://api.nbp.pl/api/exchangerates/rates/c/USD/2016-04-12/2016-04-15/");
    }

}