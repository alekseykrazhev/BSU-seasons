package Model.entity;

public class Burse {
    private int id;
    private  String name;
    private Double index;

    public Burse() {
    }

    public Burse(int id, String name, Double index) {
        this.id = id;
        this.name = name;
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getIndex() {
        return index;
    }

    public void setIndex(Double index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Burse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", index=" + index +
                '}';
    }
}