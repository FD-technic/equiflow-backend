package cz.ferdo.equiflow.entity;

import cz.ferdo.equiflow.model.Role;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "users")
public class UserEntity {

    public UserEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String userName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "owner")
    private List<PortfolioEntity> portfolios;

    // === Getter / Setter ===

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
