package pl.parser.nbp;

import com.google.common.base.Preconditions;

public class MainClass {

    public static void main(String[] args) {
        Preconditions.checkArgument(args.length == 3, "Proper Usage is: java -jar jarname CURRENCY_NAME START_DATE END_DATE");

        new CurrencyApplication()
                .currency(args[0])
                .startDate(args[1])
                .endDate(args[2])
                .run();
    }

}
