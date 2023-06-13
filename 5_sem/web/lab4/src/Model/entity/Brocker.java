package Model.entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Broker class implementation
 */
public class Brocker {
    private int id;
    private String name;
    private CopyOnWriteArrayList<Action> actionList;

    /**
     * Default constructor
     */
    public Brocker() {
    }

    /**
     * Constructor with params
     * @param id - id
     * @param name - name
     * @param actionList - list
     */
    public Brocker(int id, String name, CopyOnWriteArrayList<Action> actionList) {
        this.id = id;
        this.name = name;
        this.actionList = actionList;
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

    public CopyOnWriteArrayList<Action> getActionList() {
        return actionList;
    }

    synchronized public void setActionList(CopyOnWriteArrayList<Action> actionList) {
        this.actionList = actionList;
    }

    @Override
    public String toString() {
        return "Broker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", actionList=" + actionList +
                '}';
    }
}