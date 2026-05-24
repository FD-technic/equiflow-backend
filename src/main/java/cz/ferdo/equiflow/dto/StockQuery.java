package cz.ferdo.equiflow.dto;

import cz.ferdo.equiflow.model.Interval;
import cz.ferdo.equiflow.model.Period;

public record StockQuery(
        String ticker,
        Period period,
        Interval interval
) {
    public Period safePeriod() {
        return period != null ? period : Period.QUARTER;
    }

    public Interval safeInterval() {
        return interval != null
                ? interval
                : Interval.DAILY;
    }
}
