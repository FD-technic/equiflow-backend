# Roadmapa projektu EquiFlow - Backend

Tento dokument popisuje plán dalšího vývoje backendové části projektu EquiFlow.

---

## Aktuální stav projektu

### Hotovo

* REST API postavené na Spring Boot
* integrace s AlphaVantage API
* ukládání historických dat do PostgreSQL
* DTO vrstva pro přenos dat
* základní výpočet výnosu aktiva
* propojení s frontend aplikací EquiFlow Client

Backend je připraven pro rozšíření o analytickou vrstvu.

---

## Krátkodobé cíle

### Podpora více aktiv v jednom dotazu

Umožnit načítání dat pro více tickerů současně.

Příklad:

```text
/api/stocks?tickers=SPY,AAPL&period=MONTH
```

Přínosy:

* porovnání více aktiv
* příprava pro portfolio analýzu
* efektivnější komunikace s frontendem

---

### Analytický engine

Vytvoření samostatné vrstvy pro finanční výpočty.

Plánované metriky:

* výnos (Return)
* maximální pokles (Drawdown)
* volatilita
* Sharpe Ratio

Architektura:

```text
analysis
│
├─ ReturnCalculator
├─ DrawdownCalculator
├─ VolatilityCalculator
└─ SharpeCalculator
```

---

## Střednědobé cíle

### Portfolio analýza

Výpočty nad skupinou aktiv:

* celkový výnos portfolia
* vážená výkonnost
* rozložení portfolia
* základní rizikové ukazatele

---

### Rozšíření API

Nové endpointy pro:

* analytické výpočty
* porovnání více aktiv
* agregované statistiky

---

## Dlouhodobé cíle

### Podpora dalších poskytovatelů dat

Možné zdroje:

* Yahoo Finance
* Finnhub
* Polygon
* vlastní datové zdroje

---

### Export dat

Možnosti exportu:

* CSV
* JSON
* PDF reporty

---

### Pokročilá analytika

Budoucí rozšíření:

* benchmark porovnání
* korelace aktiv
* risk metrics
* portfolio backtesting

---

## Stav projektu

EquiFlow je výukový a portfolio projekt zaměřený na získávání zkušeností s:

* Java
* Spring Boot
* REST API
* PostgreSQL
* návrhem aplikací
* finančními daty a analytikou
