package software.ulpc.moneycalculator.arquitecture.control;

import software.ulpc.moneycalculator.arquitecture.model.Currency;
import software.ulpc.moneycalculator.arquitecture.model.ExchangeRate;
import software.ulpc.moneycalculator.arquitecture.model.FixerAPIService;
import software.ulpc.moneycalculator.app.windows.FromSelectorDialog;
import software.ulpc.moneycalculator.arquitecture.view.SelectCurrencyDialog;
import software.ulpc.moneycalculator.app.windows.ToSelectorDialog;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class GetExchangeRateCommand implements Command{
    private final FixerAPIService api;
    private final List<Currency> currencies;
    private final SelectCurrencyDialog from;
    private final SelectCurrencyDialog to;
    private ExchangeRate exchangerate;

    public GetExchangeRateCommand(FixerAPIService api, List<Currency> currencies, FromSelectorDialog from, ToSelectorDialog to) {
        this.api = api;
        this.currencies = currencies;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        try {
            this.exchangerate = new ExchangeRate(currencies.get(from.getSelecteCurrency()), currencies.get(to.getSelecteCurrency()), LocalDate.now(), getRate());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ExchangeRate getExchangeRate() throws IOException {
        return exchangerate;
    }

    private Double getRate() throws IOException {
        return api.getExchangeRate(currencies.get(from.getSelecteCurrency()), currencies.get(to.getSelecteCurrency()));
    }

    public Currency getFromCurrency() {
        return currencies.get(from.getSelecteCurrency());
    }
}
