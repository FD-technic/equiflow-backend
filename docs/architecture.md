# Architektura projektu EquiFlow

## Přehled projektu

EquiFlow je aplikace pro práci s historickými daty finančních aktiv (např. akcie nebo ETF).
Projekt je rozdělen na dvě hlavní části:

- **Backend** – Spring Boot aplikace poskytující API
- **Client** – React aplikace zobrazující data a grafy

Client komunikuje s backendem pomocí HTTP API.

---

## Celková architektura

```
      React (Frontend)
            ↓
      HTTP API
            ↓
      Spring Boot (Backend)
            │
       ┌────┴────┐
       ▼         ▼
 PostgreSQL   AlphaVantage
```

---

## Struktura repozitáře

Frontend:
https://github.com/FD-technic/equiflow-client

Backend:
https://github.com/FD-technic/equiflow-backend


---

## Backend (Spring Boot)

Struktura backendu:

```
equiflow
└─ backend
    └─ src/main/java/cz/ferdo/equiflow
        │
        ├─ config
        │   └─ corsConfig
        |
        ├─ controller
        │   ├─ healthController 
        |   └─ StockController
        │
        ├─ dto
        |   ├─ ProviderApiKey
        |   ├─ StockDTO
        |   ├─ StockPointDTO
        |   ├─ StockQuery
        |   └─ UserDTO
        |
        ├─ entity
        |   ├─ ProviderApiKeyEntity
        |   ├─ StockEntity
        |   ├─ StockPointEntity
        |
        ├─ mapper
        │   ├─ StockMapper
        │   └─ StockPointMapper
        │
        ├─ model
        │   ├─ Interval
        │   ├─ Period
        │   ├─ Portfolio
        |   └─ Provider
        |
        ├─ provider
        │   ├─ AlphaVantageProvider
        │   └─ StockDataProvider
        │
        ├─ repository
        │   ├─ StockJpaRepository
        │   └─ ProviderApiKeyRepository
        |
        └─ service
            ├─ ApiKeyService
            ├─ ApiKeyServiceImpl
            ├─ StockService
            └─ StockServiceImpl
        
```

### Vrstvy aplikace

| Vrstva | Odpovědnost |
|---------|------------|
| controller | REST endpointy |
| service | obchodní logika |
| provider | komunikace s externími zdroji dat |
| repository | přístup k databázi |
| mapper | převod Entity ↔ DTO |
| dto | přenos dat mezi vrstvami |
| entity | databázové entity |

### Datový tok

1. Frontend odešle HTTP požadavek
2. Controller přijme požadavek
3. Service ověří dostupnost dat
4. Repository načte data z databáze
5. Pokud data chybí nebo jsou zastaralá, Provider načte nová data z AlphaVantage
6. Data se uloží do databáze
7. Service vytvoří DTO odpověď
8. Controller vrátí JSON