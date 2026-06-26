package cz.ferdo.equiflow.entity;

import cz.ferdo.equiflow.model.Period;
import cz.ferdo.equiflow.model.Provider;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "stock")
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"provider", "ticker", "period"}
        )
)
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Provider provider;

    @Column(nullable = false)
    private String ticker;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Period period;

    private LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "stock",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private final List<StockPointEntity> points = new ArrayList<>();

    private String currency;
    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<StockPointEntity> getPoints() {
        return points;
    }

    public void setPoints(List<StockPointEntity> points) {
        this.points.clear();

        for (StockPointEntity point : points) {
            point.setStock(this);
            this.points.add(point);
        }
    }
}
