package cz.ferdo.equiflow.dto;

public class UserDTO {

    private String name;
    private String password;
    private MultiStockDTO portfolio;

    public UserDTO(String name, String password, MultiStockDTO portfolio) {
        this.name = name;
        this.password = password;
        this.portfolio = portfolio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultiStockDTO getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(MultiStockDTO portfolio) {
        this.portfolio = portfolio;
    }
}
