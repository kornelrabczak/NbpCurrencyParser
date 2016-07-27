package pl.parser.nbp.infrastructure.parser;

import pl.parser.nbp.bussiness.currency.boundary.CurrencyParser;
import pl.parser.nbp.bussiness.currency.boundary.DataProvider;
import pl.parser.nbp.bussiness.currency.entity.CurrencyResult;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class StAXParser extends CurrencyParser {

    private static Map<String, Consumer<String>> handlers = new HashMap<>();

    static {
        handlers.put("TEST", System.out::print);
    }

    public StAXParser(String source) {
        super(source);
    }

    private Consumer<String> currentHandler;

    @Override
    public CurrencyResult parse(DataProvider dataProvider) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader streamReader = null;

        try (final InputStream stream = dataProvider.fetch(source)) {
            streamReader = xmlInputFactory.createXMLStreamReader(stream);
            while (streamReader.hasNext()) {
                int eventType = streamReader.next();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        String handlerName = streamReader.getLocalName();
                        currentHandler = handlers.get(handlerName);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String value = streamReader.getText();
                        currentHandler.accept(value);
                        break;
                }
            }
        } catch (XMLStreamException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(streamReader);
        }

        return null;
    }

    private void closeQuietly(XMLStreamReader streamReader) {
        if (streamReader != null) {
            try {
                streamReader.close();
            } catch (XMLStreamException e) {
                // ignored
            }
        }
    }
}
