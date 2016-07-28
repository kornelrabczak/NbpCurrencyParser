package pl.parser.nbp.infrastructure.provider;

import pl.parser.nbp.bussiness.currency.boundary.DataProvider;
import pl.parser.nbp.bussiness.currency.entity.CurrencyRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WebDataProvider implements DataProvider {

    private static final int CONNECT_TIMEOUT = 200;
    private static final int READ_TIMEOUT = 1000;

    private final CurrencyRequest request;

    public WebDataProvider(CurrencyRequest request) {
        this.request = request;
    }

    @Override
    public InputStream fetch() throws IOException {
        final URL url = new URL(request.getSource());
        final URLConnection connection = url.openConnection();
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);
        connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
        return connection.getInputStream();
    }
}
