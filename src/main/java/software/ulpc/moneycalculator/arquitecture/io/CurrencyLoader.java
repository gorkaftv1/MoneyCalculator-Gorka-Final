package software.ulpc.moneycalculator.arquitecture.io;

import software.ulpc.moneycalculator.arquitecture.model.Currency;

import java.util.List;

public interface CurrencyLoader {
    List<Currency> load();
}
