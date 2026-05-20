package cz.ferdo.equiflow.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.model.Stock;
import cz.ferdo.equiflow.model.StockPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class AlphaVantageProvider implements StockDataProvider {

    private JsonNode seriesData;

    @Override
    public Stock fetchStock(StockQuery query) {
        RestClient client = RestClient.create();

        String function = switch (query.safeInterval()) {
            case DAILY -> "TIME_SERIES_DAILY";
            case WEEKLY -> "TIME_SERIES_WEEKLY";
            case MONTHLY -> "TIME_SERIES_MONTHLY";
        };

        String seriesName = switch (query.safeInterval()) {
            case DAILY -> "Time Series (Daily)";
            case WEEKLY -> "Weekly Time Series";
            case MONTHLY -> "Monthly Time Series";
        };

        /**
         * Dotaz na hledání produktu:
         *
         * https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=tesco&apikey=HRQED98AMN93D5WT
         */
        String json = client.get()
                .uri("https://www.alphavantage.co/query?function=" + function + "&symbol=" + query.ticker().toUpperCase() + "&outputsize=compact&apikey=HRQED98AMN93D5WT")
                .retrieve()
                .body(String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root;

        try {
        root = mapper.readTree(json);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        seriesData = root.get(seriesName);

        List<StockPoint> myList = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> fields =
                seriesData.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String date = entry.getKey();
            double close = entry.getValue().get("4. close").asDouble();

            myList.add(new StockPoint(date, close));
        }

        return new Stock(root.get("Meta Data").get("2. Symbol").asText(), myList, "USD");
    }
}
