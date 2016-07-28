package pl.parser.nbp;

import com.google.common.base.Preconditions;
import pl.parser.nbp.bussiness.currency.boundary.DataProvider;
import pl.parser.nbp.bussiness.currency.entity.CurrencyRequest;
import pl.parser.nbp.bussiness.currency.entity.CurrencyResult;
import pl.parser.nbp.infrastructure.parser.StAXParser;
import pl.parser.nbp.infrastructure.provider.WebDataProvider;
import pl.parser.nbp.infrastructure.request.NbpRequest;

public class MainClass {

    public static void main(String[] args) {
        Preconditions.checkArgument(args.length == 3, "Proper Usage is: java -jar jarname CURRENCY_NAME START_DATE END_DATE");

        CurrencyRequest request = new NbpRequest()
                .currency(args[0])
                .startDate(args[1])
                .endDate(args[2]);

        final DataProvider provider = new WebDataProvider(request);
        final CurrencyResult result = new StAXParser().parse(provider);
        System.out.println(result.toString());
    }

}
