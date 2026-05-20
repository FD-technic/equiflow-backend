package cz.ferdo.equiflow.dto;

import cz.ferdo.equiflow.model.Interval;
import org.springframework.scheduling.config.IntervalTask;

public record StockQuery(
        String ticker,
        Integer days,
        Interval interval
) {
    public int safeDays() {
        return days != null ? days : 30;
    }

    public Interval safeInterval() {
        return interval != null
                ? interval
                : Interval.DAILY;
    }
}
