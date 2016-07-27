package pl.parser.nbp.infrastructure.provider;

import pl.parser.nbp.bussiness.currency.boundary.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WebDataProvider implements DataProvider {

    public static final int connectTimeout = 200;
    public static final int readTimeout = 1000;

    @Override
    public InputStream fetch(final String source) throws IOException {
        final URL url = new URL(source);
        final URLConnection connection = url.openConnection();
        connection.setConnectTimeout(connectTimeout);
        connection.setReadTimeout(readTimeout);
        return connection.getInputStream();
    }
}
