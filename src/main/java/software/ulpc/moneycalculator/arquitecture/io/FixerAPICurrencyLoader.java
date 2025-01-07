package software.ulpc.moneycalculator.arquitecture.io;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import software.ulpc.moneycalculator.arquitecture.model.Currency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FixerAPICurrencyLoader implements CurrencyLoader {
    private static final String API_URL = "http://data.fixer.io/api/symbols";
    private final String accessKey;

    public FixerAPICurrencyLoader(String accessKey) {
        this.accessKey = accessKey;
    }

    @Override
    public List<Currency> load() {
        List<Currency> currencies = new ArrayList<>();
        try {
            Connection connection = Jsoup.connect(API_URL)
                    .ignoreContentType(true)
                    .data("access_key", accessKey);
            String jsonResponse = connection.get().text();
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            if (!jsonObject.get("success").getAsBoolean()) {
                throw new RuntimeException("Error en la API: " + jsonObject.get("error").getAsJsonObject());
            }
            JsonObject symbols = jsonObject.getAsJsonObject("symbols");
            for (String code : symbols.keySet()) {
                String name = symbols.get(code).getAsString();
                String symbol = getSymbolFromCode(code);
                currencies.add(new Currency(code, name, symbol));
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar las monedas desde Fixer API", e);
        }
        return currencies;
    }

    private String getSymbolFromCode(String code) {
        return code;
    }
}
