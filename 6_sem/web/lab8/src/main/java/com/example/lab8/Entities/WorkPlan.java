package com.example.lab8.Entities;

import jakarta.persistence.*;
// @NamedQuery(name = "WorkPlan.insertNewPlan", query = "insert into WorkPlan (name, worker, id_type) values (:name, :id_worker,:id_type)")
/**
 * Implementation of work plan class
 */
@Entity
@Table(name="work_plan")
@NamedQueries({
        @NamedQuery(name = "WorkPlan.selectAll", 
                    query = "select a from WorkPlan a")
})
public class WorkPlan {
    /**
     * Name
     */
    @Column(name="name")
    public String name;

    /**
     * WorkPlan ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    /**
     * Worker for the job
     */
    @OneToOne
    @JoinColumn(name="id_worker")
    public Worker worker;

    /**
     * Type of work
     */
    @Column(name="id_type")
    public int id_type;

    /**
     * Main WorkPlan constructor
     * @param id - id
     * @param name - name
     */
    public WorkPlan (int id, String name, Worker worker, int id_type) {
        this.id = id;
        this.name = name;
        this.worker = worker;
        this.id_type = id_type;
    }

    /**
     * Default constructor
     */
    public WorkPlan() {}

    /**
     * Get object as string
     * @return - string
     */
    public String toString() {
        return String.format("\nWork plan (id: %d; name: %s, type of work: %d)", this.id, this.name, this.id_type);
    }
}
