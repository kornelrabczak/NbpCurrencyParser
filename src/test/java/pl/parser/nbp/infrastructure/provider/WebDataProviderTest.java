package pl.parser.nbp.infrastructure.provider;

import org.junit.Test;
import pl.parser.nbp.bussiness.currency.entity.CurrencyRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WebDataProviderTest {

    private CurrencyRequest request = mock(CurrencyRequest.class);

    private WebDataProvider sut = new WebDataProvider(request);

    @Test(expected = MalformedURLException.class)
    public void should_fail_for_malformed_url() throws IOException {
        // given
        String url = "qqwe.aszx:qwe";
        when(request.getSource()).thenReturn(url);

        // when
        try(InputStream ignored = sut.fetch()) {

        }
    }

    @Test(expected = SocketTimeoutException.class)
    public void should_timeout() throws IOException {
        // given
        String url = "http://localhost:12345/index.php";
        when(request.getSource()).thenReturn(url);

        // when
        try(InputStream ignored = sut.fetch()) {

        }
    }

    @Test
    public void should_pass_for_proper_url() throws IOException {
        // given
        String url = "http://google.com";
        when(request.getSource()).thenReturn(url);

        // when
        try(InputStream ignored = sut.fetch()) {

        }
    }
}