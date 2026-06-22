package cz.ferdo.equiflow.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "position")
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"ticker", "portfolio_id"}
                )
        }
)
public class PositionEntity {

    public PositionEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ticker;

    private long quantity;

    private BigDecimal buyPrice;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private PortfolioEntity portfolio;

    // === Getter / Setter

    public Long getId() {
        return id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public PortfolioEntity getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(PortfolioEntity portfolio) {
        this.portfolio = portfolio;
    }
}
