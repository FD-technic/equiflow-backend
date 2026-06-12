package cz.ferdo.equiflow.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockPointDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class AlphaVantageProvider implements StockDataProvider {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public StockDTO parseStock(String json) {
        JsonNode root;

        try {
            root = mapper.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JsonNode seriesData = null;

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

        List<StockPointDTO> closePoints = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> points =
                seriesData.fields();

        while (points.hasNext()) {
            Map.Entry<String, JsonNode> entry = points.next();
            LocalDate date = LocalDate.parse(entry.getKey());

            JsonNode day = entry.getValue();

            BigDecimal open = getValue(day, "1. open");
            BigDecimal high = getValue(day, "2. high");
            BigDecimal low = getValue(day, "3. low");
            BigDecimal close = getValue(day, "4. close");
            BigDecimal volume = getValue(day, "5. volume");

            closePoints.add(new StockPointDTO(date, open, high, low, close, volume));
        }

        String ticker = root.path("Meta Data").path("2. Symbol").asText();

        if (ticker.isBlank()) {
            throw new IllegalStateException(
                    "Ticker not found in response");
        }

        return new StockDTO(ticker, "USD", closePoints, LocalDateTime.now());
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
        }

        System.out.println("RESPONSE: " + response);
        return response;
    }

    private BigDecimal getValue(
            JsonNode node,
            String field
    ) {
        String value = node.path(field).asText();

        return new BigDecimal(value);
    }
}
