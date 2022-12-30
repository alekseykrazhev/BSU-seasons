package Model.entity;

/**
 * Lot class implementation
 */
public class Lot {
    private int id;
    private String name;
    private Action action;
    private Double newPrice;

    /**
     * Default constructor
     */
    public Lot() {
    }

    /**
     * Constructor with params
     */
    public Lot(int id, String name, Action action, Double newPrice) {
        this.id = id;
        this.name = name;
        this.action = action;
        this.newPrice = newPrice;
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

    public Action getAction() {
        return action;
    }

    synchronized public void setAction(Action action) {
        this.action = action;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    synchronized public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", action=" + action +
                ", newPrice=" + newPrice +
                '}';
    }
}