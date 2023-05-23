package com.example.lab8.Entities;

import java.util.Date;

import jakarta.persistence.*;

/**
 * Application class implementation
 */
@Entity
@Table(name="application")
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
    @Column(name="tenant_id")
    private int tenant_id;

    /**
     * Finish date
     */
    @Column(name="date")
    private String date;

    /**
     * Dispatcher
     */
    @Column(name="dispatcher_id")
    private int dispatcher_id;

    /**
     * Main Application constructor
     * @param id - id
     * @param name - name
     */
    public Application (int id, String name, int id_type, int tenant_id, String date, int dispatcher_id) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.dispatcher_id = dispatcher_id;
        this.tenant_id = tenant_id;
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

    public String getDate() {
        return date;
    }

    public int getDispatcher() {
        return dispatcher_id;
    }

    /**
     * Get object as string
     * @return - string
     */
    public String toString() {
        return String.format("\nApplication (id: %d; name: %s, date: %s, tenant id: %d, dispatcher id: %d)",
                this.id, this.name, this.date, this.tenant_id, this.dispatcher_id);
    }
}
