package pl.parser.nbp.infrastructure.parser;

import pl.parser.nbp.bussiness.currency.boundary.CurrencyParser;
import pl.parser.nbp.bussiness.currency.boundary.DataProvider;
import pl.parser.nbp.bussiness.currency.control.BigDecimalListHandler;
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

    private static final EventHandler<String> NO_OP_HANDLER = noOpHandler -> {};

    public static final String BID_HANDLER = "Bid";
    public static final String ASK_HANDLER = "Ask";

    private final Map<String, EventHandler<String>> handlers = new HashMap<>();

    public StAXParser() {
        handlers.put(BID_HANDLER, new BigDecimalListHandler());
        handlers.put(ASK_HANDLER, new BigDecimalListHandler());
    }

    private EventHandler<String> currentHandler;

    @Override
    public CurrencyResult parse(DataProvider dataProvider) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader streamReader = null;

        try (final InputStream stream = dataProvider.fetch()) {
            streamReader = xmlInputFactory.createXMLStreamReader(stream, "UTF-8");
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

        return new CurrencyResult(
                ((BigDecimalListHandler) handlers.get(BID_HANDLER)).getResult(),
                ((BigDecimalListHandler) handlers.get(ASK_HANDLER)).getResult()
        );
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
