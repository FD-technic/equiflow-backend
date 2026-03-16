package cz.ferdo.equiflow;

public class Person {
    private String name;
    private int vek;

    public Person(String name, int vek) {
        this.name = name;
        this.vek = vek;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }
}
