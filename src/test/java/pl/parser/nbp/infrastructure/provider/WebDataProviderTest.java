package pl.parser.nbp.infrastructure.provider;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

public class WebDataProviderTest {

    private WebDataProvider sut = new WebDataProvider();

    @Test(expected = MalformedURLException.class)
    public void should_fail_for_malformed_url() throws IOException {
        // given
        String url = "qqwe.aszx:qwe";

        // when
        sut.fetch(url);
    }

    @Test(expected = SocketTimeoutException.class)
    public void should_timeout() throws IOException {
        // given
        String url = "http://localhost:12345/index.php";

        // when
        sut.fetch(url);
    }

    @Test
    public void should_pass_for_proper_url() throws IOException {
        // given
        String url = "http://google.com";

        // when
        sut.fetch(url);
    }
}