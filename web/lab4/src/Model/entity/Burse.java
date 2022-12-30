package Model.entity;

/**
 * Burs class implementation
 */
public class Burse {
    private int id;
    private  String name;
    private Double index;

    /**
     * Default constructor
     */
    public Burse() {
    }

    /**
     * Constructor with params
     * @param id - id
     * @param name - name
     * @param index - index
     */
    public Burse(int id, String name, Double index) {
        this.id = id;
        this.name = name;
        this.index = index;
    }

    public int getId() {
        return id;
    }

    synchronized public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    synchronized public void setName(String name) {
        this.name = name;
    }

    public Double getIndex() {
        return index;
    }

    synchronized public void setIndex(Double index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Burs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", index=" + index +
                '}';
    }
}