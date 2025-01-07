package software.ulpc.moneycalculator.app.windows;

import software.ulpc.moneycalculator.arquitecture.control.GetExchangeRateCommand;
import software.ulpc.moneycalculator.arquitecture.control.GetMoneyCommand;
import software.ulpc.moneycalculator.arquitecture.io.FixerAPICurrencyLoader;
import software.ulpc.moneycalculator.arquitecture.model.Currency;
import software.ulpc.moneycalculator.arquitecture.model.FixerAPIService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String apiKey = getKey();
        FixerAPIService api = new FixerAPIService(apiKey);
        FixerAPICurrencyLoader loader = new FixerAPICurrencyLoader(apiKey);
        List<Currency> currencies = loader.load();
        MainFrame mainFrame = new MainFrame(currencies);
        GetExchangeRateCommand exchangeCommand = createExchangeCommand(api, currencies, mainFrame);
        mainFrame.add("getExchangeRate", exchangeCommand)
                .add("getExchangedAmount", new GetMoneyCommand(mainFrame.fromRateDialog(), mainFrame.ToRateDialog(), exchangeCommand));
        mainFrame.setVisible(true);
    }

    private static String getKey() {
        String apiKey = System.getenv("FIXER_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("ERROR: La variable de entorno FIXER_API_KEY no está configurada.");
            System.exit(1);
        }
        return apiKey;
    }

    private static GetExchangeRateCommand createExchangeCommand(FixerAPIService api, List<Currency> load, MainFrame mainFrame) {
        return new GetExchangeRateCommand(api, load, mainFrame.fromSelectDialog(), mainFrame.toSelectDialog());
    }
}
