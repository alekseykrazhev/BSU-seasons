package com.example.lab2_part2.Model;

import javax.persistence.*;

/**
 * Tenant class implementation
 */
@Entity
@Table(name="tenant")
@NamedQueries({
        @NamedQuery(name = "Tenant.findAll", query = "select a from Tenant a"),
        @NamedQuery(name = "Tenant.findById", query = "select a from Tenant a where a.id = :id"),
        @NamedQuery(name = "Tenant.deleteById", query = "delete from Tenant a where a.id = :id")
})
public class Tenant {
    /**
     * Name
     */
    @Column(name="name")
    private String name;

    /**
     * Tenant ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    /**
     * Main Tenant constructor
     * @param id - id
     * @param name - name
     */
    public Tenant (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tenant () {};

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get object as string
     * @return - string
     */
    public String toString() {
        return String.format("\nTenant (id: %d; name: %s)", this.id, this.name);
    }
}
