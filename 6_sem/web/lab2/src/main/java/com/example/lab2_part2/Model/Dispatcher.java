package com.example.lab2_part2.Model;

import javax.persistence.*;

/**
 * Dispatcher class implementation
 */
@Entity
@Table(name="dispatcher")
@NamedQueries({
        @NamedQuery(name = "Dispatcher.selectAll", query = "select a from Dispatcher a"),
        @NamedQuery(name = "Dispatcher.findById", query = "select a from Dispatcher a where a.id = :id"),
        @NamedQuery(name = "Dispatcher.deleteById", query = "delete from Dispatcher a where a.id = :id")
})
public class Dispatcher {
    /**
     * Name
     */
    @Column(name="name")
    private String name;

    /**
     * Dispatcher ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    /**
     * Main Dispatcher constructor
     * @param id - id
     * @param name - name
     */
    public Dispatcher (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dispatcher () {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    /**
     * Get object as string
     * @return - string
     */
    public String toString() {
        return String.format("\nDispatcher (id: %d; name: %s)", this.id, this.name);
    }
}
