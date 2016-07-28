package pl.parser.nbp.infrastructure.parser;

import com.google.common.io.Resources;
import org.junit.Test;
import pl.parser.nbp.bussiness.currency.boundary.CurrencyParser;
import pl.parser.nbp.bussiness.currency.boundary.DataProvider;
import pl.parser.nbp.bussiness.currency.entity.CurrencyResult;

import static org.assertj.core.api.Assertions.assertThat;

public class StAXParserTest {

    CurrencyParser sut = new StAXParser();

    @Test
    public void parse_test_xml() {
        // given
        DataProvider fileDataProvider = () -> Resources.getResource("nbp_test_data.xml").openStream();

        // when
        final CurrencyResult result = sut.parse(fileDataProvider);

        // then
        assertThat(result.getAsks()).hasSize(7);
        assertThat(result.getBids()).hasSize(7);
    }
}