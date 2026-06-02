package cz.ferdo.equiflow.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.model.Stock;
import cz.ferdo.equiflow.model.StockPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class AlphaVantageProvider implements StockDataProvider {

    @Override
    public Stock fetchStock(String json) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        JsonNode seriesData = null;

        try {
            root = mapper.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Iterator<Map.Entry<String, JsonNode>> fields =
                root.fields();

        // Debug output
        System.out.println("ROOT FIELDS:");
        root.fieldNames().forEachRemaining(System.out::println);

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String fieldName = entry.getKey();

            if (fieldName.contains("Time Series")) {
                seriesData = entry.getValue();
                break;
            }
        }

        // Debug output
        if (seriesData == null) {
            System.out.println("=== RAW RESPONSE START ===");
            System.out.println(root.toPrettyString());
            System.out.println("=== RAW RESPONSE END ===");

            throw new IllegalStateException(
                    "No Time Series found in AlphaVantage response");
        }

        List<StockPoint> myList = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> points =
                seriesData.fields();

        while (points.hasNext()) {
            Map.Entry<String, JsonNode> entry = points.next();
            String date = entry.getKey();
            double close = entry.getValue().get("4. close").asDouble();

            myList.add(new StockPoint(date, close));
        }

        return new Stock(root.get("Meta Data").get("2. Symbol").asText(), myList, "USD");
    }

    @Override
    public String fetchRawJson(StockQuery query) {
        RestClient client = RestClient.create();

        String apiKey = "NULL";

        try {
            apiKey = Files.readString(Paths.get("settings/av.key"));
        } catch (IOException e) {
            System.out.println("API key not found");
            throw new RuntimeException(e);
        }

        String function = switch (query.safePeriod()) {
            case WEEK -> "TIME_SERIES_WEEKLY";
            case MONTH -> "TIME_SERIES_MONTHLY";
            default -> "TIME_SERIES_DAILY";
        };

        String response = client.get()
                .uri("https://www.alphavantage.co/query?function=" + function + "&symbol=" + query.ticker().toUpperCase() + "&outputsize=compact&apikey=" + apiKey)
                .retrieve()
                .body(String.class);

        if (response != null && !response.contains("Time Series")) {
            System.out.println("INVALID ALPHAVANTAGE RESPONSE:");
            System.out.println(response);
        }

        return response;
    }
}
