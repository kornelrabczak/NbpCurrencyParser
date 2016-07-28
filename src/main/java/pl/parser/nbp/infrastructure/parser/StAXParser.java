package pl.parser.nbp.infrastructure.parser;

import pl.parser.nbp.bussiness.currency.boundary.CurrencyParser;
import pl.parser.nbp.bussiness.currency.boundary.DataProvider;
import pl.parser.nbp.bussiness.currency.control.AskHandler;
import pl.parser.nbp.bussiness.currency.control.BidHandler;
import pl.parser.nbp.bussiness.currency.control.EventHandler;
import pl.parser.nbp.bussiness.currency.entity.CurrencyResult;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class StAXParser implements CurrencyParser {

    public static final EventHandler<String> NO_OP_HANDLER = noOpHandler -> {};

    private final Map<String, EventHandler<String>> handlers = new HashMap<>();

    private final CurrencyResult result = new CurrencyResult();

    public StAXParser() {
        handlers.put(BidHandler.HANDLE_KEY, new BidHandler(result));
        handlers.put(AskHandler.HANDLE_KEY, new AskHandler(result));
    }

    private EventHandler<String> currentHandler;

    @Override
    public CurrencyResult parse(DataProvider dataProvider) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader streamReader = null;

        try (final InputStream stream = dataProvider.fetch()) {
            streamReader = xmlInputFactory.createXMLStreamReader(stream);
            while (streamReader.hasNext()) {
                int eventType = streamReader.next();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        String handlerName = streamReader.getLocalName();
                        currentHandler = handlers.getOrDefault(handlerName, NO_OP_HANDLER);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        currentHandler.handle(streamReader.getText());
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        currentHandler = NO_OP_HANDLER;
                        break;
                }
            }
        } catch (XMLStreamException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(streamReader);
        }

        return result;
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
