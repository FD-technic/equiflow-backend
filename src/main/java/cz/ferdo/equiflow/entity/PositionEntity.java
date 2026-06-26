package cz.ferdo.equiflow.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "position")
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"stock_id", "portfolio_id"}
                )
        }
)
public class PositionEntity {

    public PositionEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal buyPrice;

    private long quantity;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private StockEntity stock;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private PortfolioEntity portfolio;

    // === Getter / Setter

    public Long getId() {
        return id;
    }

    public StockEntity getStock() {
        return stock;
    }

    public void setStock(StockEntity stock) {
        this.stock = stock;
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
