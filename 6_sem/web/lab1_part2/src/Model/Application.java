package Model;

import java.util.Date;

/**
 * Application class implementation
 */
public class Application {
    /**
     * Name
     */
    private String name;

    /**
     * Application ID
     */
    private int id;

    /**
     * Type of work
     */
    private int id_type;

    /**
     * Tenant of an application
     */
    private Tenant tenant;

    /**
     * Finish date
     */
    private Date date;

    /**
     * Dispatcher
     */
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

    /**
     * Get object as string
     * @return - string
     */
    public String toString() {
        return String.format("\nApplication (id: %d; name: %s, date: %s, tenant id: %d, dispatcher id: %d)",
                this.id, this.name, this.date, this.tenant.id, this.dispatcher.id);
    }
}
