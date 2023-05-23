package com.example.lab7.model.entity;

import jakarta.persistence.*;

/**
 * Implementation of worker class
 */
@Entity
@Table(name="worker")
@NamedQueries({
        @NamedQuery(name = "Worker.selectStatement", query = "select a from Worker a where a.id_type = :id_type and busy = false"),
        @NamedQuery(name = "Worker.updateWorkerBusy", query = "UPDATE Worker w SET w.busy = :busy WHERE w.id = :id"),
        @NamedQuery(name = "Worker.selectAll", query = "select a from Worker a")
})
public class Worker {
    /**
     * Name
     */
    @Column(name="name")
    public String name;

    /**
     * Tenant ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    /**
     * Type of works
     */
    @Column(name="id_type")
    public int id_type;

    /**
     * Is busy?
     */
    @Column(name="busy")
    private boolean busy;

    public Worker() {}

    /**
     * Main Worker constructor
     * @param id - id
     * @param name - name
     */
    public Worker (int id, String name, int id_type, boolean busy) {
        this.id = id;
        this.name = name;
        this.id_type = id_type;
        this.busy = busy;
    }

    /**
     * Get object as string
     * @return - string
     */
    public String toString() {
        return String.format("\nWorker (id: %d; name: %s, type of work: %d, is busy: %b)", this.id, this.name, this.id_type, this.busy);
    }
}