package Model.entity;

public class Action {
    private int id;
    private String corporationName;
    private int actionCount;
    private Double price;

    public Action() {
    }

    public Action(int id, String corporationName, int actionCount, Double price) {
        this.id = id;
        this.corporationName = corporationName;
        this.actionCount = actionCount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public int getActionCount() {
        return actionCount;
    }

    public void setActionCount(int actionCount) {
        this.actionCount = actionCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", corporationName='" + corporationName + '\'' +
                ", actionCount=" + actionCount +
                ", price=" + price +
                '}';
    }
}