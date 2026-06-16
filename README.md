# EquiFlow Backend

Backendová část projektu EquiFlow.

EquiFlow je experimentální projekt zaměřený na práci s historickými daty finančních aktiv (akcie, ETF).

Projekt se skládá z:

* backendového REST API postaveného na Spring Boot
* frontendového dashboardu postaveného na Reactu
* PostgreSQL databáze pro ukládání historických dat

Cílem projektu je postupně vybudovat jednoduchý analytický engine pro práci s finančními časovými řadami a investičními daty.

## Související repozitáře

Frontend:
https://github.com/FD-technic/equiflow-client

Backend:
https://github.com/FD-technic/equiflow-backend

## Live Demo

#### Frontend: https://equiflow.ferdo.eu
#### API: https://api.equiflow.ferdo.eu

---

## Popis
Aplikace poskytuje REST API pro získávání, ukládání a zpracování historických dat finančních aktiv (akcie, ETF).

Data jsou načítána z AlphaVantage API a ukládána do PostgreSQL databáze.

---

## Funkce

* načítání historických dat akcií a ETF
* ukládání dat do PostgreSQL
* REST API pro přístup k datům
* automatická aktualizace dat
* příprava pro analytické výpočty

---

## Technologie

* Java 21
* Spring Boot 3
* Spring Data JPA
* PostgreSQL
* Maven

---

## Architektura

```text
AlphaVantage API
        │
        ▼
 Spring Boot
        │
        ▼
   PostgreSQL
        │
        ▼
     REST API
```

---

## Spuštění projektu

### Požadavky

* Java 21+
* Maven 3.9+
* PostgreSQL

---

## Konfigurace

Nastavení databáze a API klíčů se provádí v:

```text
src/main/resources/application.properties
```

Příklad:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/equiflow
spring.datasource.username=user
spring.datasource.password=password

alphavantage.api.key=YOUR_API_KEY
```

---

## Spuštění

V kořenové složce projektu:

```bash
mvn spring-boot:run
```

nebo spuštěním třídy:

```text
EquiFlowApplication
```

Po spuštění bude API dostupné na:

```text
http://localhost:8080
```

---

## API

### Kontrola stavu

```http
GET /api/health
```

Příklad:

```text
http://localhost:8080/api/health
```

---

## Historická data

```http
GET /api/stocks/av
```

Příklad:

```http
GET /api/stocks/av?ticker=QQQ&period=MONTH
```

Parametry:

| Parametr | Popis                                          |
| -------- | ---------------------------------------------- |
| ticker   | Burzovní ticker symbol (např. SPY, QQQ, AAPL)  |
| period   | Periodicita dat (DAY, WEEK, MONTH)             |

---

# Aktuální stav

## Hotovo

* REST API
* integrace AlphaVantage
* ukládání dat do PostgreSQL
* JPA entity a repository
* DTO vrstva
* základní výpočty výnosu

## Plánované

* volatilita
* drawdown
* Sharpe ratio
* portfolio analýza
* rozšíření testů

---

# Dokumentace

Další dokumentace:

* architecture.md
* api.md
* roadmap.md

---

# Licence

Projekt slouží jako výukový a portfolio projekt.
