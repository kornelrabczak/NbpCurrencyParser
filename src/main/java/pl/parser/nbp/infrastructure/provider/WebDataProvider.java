package pl.parser.nbp.infrastructure.provider;

import pl.parser.nbp.bussiness.currency.boundary.DataProvider;
import pl.parser.nbp.bussiness.currency.entity.CurrencyRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WebDataProvider implements DataProvider {

    private static final int connectTimeout = 200;
    private static final int readTimeout = 1000;

    private final CurrencyRequest request;

    public WebDataProvider(CurrencyRequest request) {
        this.request = request;
    }

    @Override
    public InputStream fetch() throws IOException {
        final URL url = new URL(request.getSource());
        final URLConnection connection = url.openConnection();
        connection.setConnectTimeout(connectTimeout);
        connection.setReadTimeout(readTimeout);
        return connection.getInputStream();
    }
}
