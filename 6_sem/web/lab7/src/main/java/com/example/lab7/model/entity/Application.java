package com.example.lab7.model.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Application class implementation
 */
@Entity
@Table(name="application")
@NamedQueries({
        @NamedQuery(name = "Application.selectAll", query = "select a from Application a"),
        @NamedQuery(name = "Application.selectStatement", query = "select a from Application a where a.date < :curdate"),
        @NamedQuery(name = "Application.getIdType", query = "select a from Application a where a.id = :id")
})
public class Application {
    /**
     * Name
     */
    @Column(name="name")
    private String name;

    /**
     * Application ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    /**
     * Type of work
     */
    @Column(name="id_type")
    private int id_type;

    /**
     * Tenant of an application
     */
    @OneToOne
    @JoinColumn(name="tenant_id")
    private Tenant tenant;

    /**
     * Finish date
     */
    @Column(name="date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    /**
     * Dispatcher
     */
    @OneToOne
    @JoinColumn(name="dispatcher_id")
    private Dispatcher dispatcher;

    /**
     * Main Application constructor
     * @param id - id
     * @param name - name
     */
    public Application (int id, String name, int id_type, Tenant tenant, Date date, Dispatcher dispatcher) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.dispatcher = dispatcher;
        this.tenant = tenant;
        this.id_type = id_type;
    }

    public Application () {};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId_type (int id_type) {
        this.id_type = id_type;
    }

    public int getId_type () {
        return id_type;
    }

    public Date getDate() {
        return date;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    /**
     * Get object as string
     * @return - string
     */
    public String toString() {
        return String.format("\nApplication (id: %d; name: %s, date: %s, tenant id: %d, dispatcher id: %d)",
                this.id, this.name, this.date, this.tenant.id, this.dispatcher.id);
    }
}