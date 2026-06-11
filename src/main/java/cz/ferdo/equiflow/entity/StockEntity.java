package cz.ferdo.equiflow.entity;

import cz.ferdo.equiflow.model.Period;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"ticker", "period"}
        )
)
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticker;

    @Enumerated(EnumType.STRING)
    private Period period;

    private LocalDateTime updateAt;

    @OneToMany(
            mappedBy = "stock",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<StockPointEntity> points = new ArrayList<>();

    private String currency;
    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
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
