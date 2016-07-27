package pl.parser.nbp.bussiness.currency.boundary;

import java.io.InputStream;

public interface DataProvider {
    InputStream fetch(String source);
}
