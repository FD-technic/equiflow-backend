package cz.ferdo.equiflow.dto;

import cz.ferdo.equiflow.model.Interval;
import cz.ferdo.equiflow.model.Period;
import cz.ferdo.equiflow.model.Provider;

public record StockQuery(
        Provider provider,
        String ticker,
        Period period,
        Interval interval
) {
    public Provider safeProvider() {
        return provider != null ? provider : Provider.ALPHAVANTAGE;
    }

    public Period safePeriod() {
        return period != null ? period : Period.WEEK;
    }

    public Interval safeInterval() {
        return interval != null
                ? interval
                : Interval.WEEKLY;
    }
}
