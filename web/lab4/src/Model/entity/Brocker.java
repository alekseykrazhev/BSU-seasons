package Model.entity;

import java.util.List;

public class Brocker {
    private int id;
    private String name;
    private List<Action> actionList;

    public Brocker() {
    }

    public Brocker(int id, String name, List<Action> actionList) {
        this.id = id;
        this.name = name;
        this.actionList = actionList;
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

    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    @Override
    public String toString() {
        return "Brocker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", actionList=" + actionList +
                '}';
    }
}