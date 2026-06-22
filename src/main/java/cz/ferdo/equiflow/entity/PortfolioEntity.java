package cz.ferdo.equiflow.entity;

import cz.ferdo.equiflow.model.PortfolioType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "portfolio")
public class PortfolioEntity {

    public PortfolioEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PortfolioType type;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;

    @OneToMany(
            mappedBy = "portfolio",
            cascade = CascadeType.ALL
    )
    private List<PositionEntity> positions = new ArrayList<>();

    // === METHODS ===

    public void addPosition(PositionEntity position) {
        position.setPortfolio(this);

        for (PositionEntity p : positions) {
            if (position.getTicker().equals(p.getTicker())) {
                Long totalQuantity = p.getQuantity() + position.getQuantity();
                p.setBuyPrice(averagePrice(p, position));
                p.setQuantity(totalQuantity);
                return;
            }
        }

        positions.add(position);
    }

    public BigDecimal averagePrice(PositionEntity position, PositionEntity newPosition) {
        BigDecimal quantity = BigDecimal.valueOf(position.getQuantity());
        BigDecimal newQuantity = BigDecimal.valueOf(newPosition.getQuantity());
        BigDecimal totalQuantity = BigDecimal.valueOf(position.getQuantity() + newPosition.getQuantity());
        BigDecimal totalPrice = (position.getBuyPrice().multiply(quantity)).add(newPosition.getBuyPrice().multiply(newQuantity));

        return totalPrice.divide(totalQuantity, 4, RoundingMode.HALF_EVEN);
    }

    // === Getter / Setter

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PortfolioType getType() {
        return type;
    }

    public void setType(PortfolioType type) {
        this.type = type;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public List<PositionEntity> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionEntity> positions) {
        this.positions = positions;
    }
}
