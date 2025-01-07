package software.ulpc.moneycalculator.arquitecture.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class FixerAPIService {
    private static final String API_URL = "http://data.fixer.io/api/latest";
    private final String accessKey;

    public FixerAPIService(String accessKey) {
        this.accessKey = accessKey;
    }

    public Double getExchangeRate(Currency from, Currency to) throws IOException {
        String fromCode = from.code();
        String toCode = to.code();
        Connection connection = Jsoup.connect(API_URL)
                .ignoreContentType(true)
                .data("access_key", accessKey)
                .data("symbols", fromCode + "," + toCode);
        String jsonResponse = connection.get().text();
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        if (!jsonObject.get("success").getAsBoolean()) {
            throw new RuntimeException("Error en la API: " + jsonObject.get("error").getAsJsonObject());
        }

        JsonObject rates = jsonObject.getAsJsonObject("rates");
        double fromRate = rates.get(fromCode).getAsDouble();
        double toRate = rates.get(toCode).getAsDouble();
        return toRate / fromRate;
    }
}
