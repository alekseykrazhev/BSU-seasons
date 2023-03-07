package Model;

/**
 * Tenant class implementation
 */
public class Tenant {
    /**
     * Name
     */
    private String name;

    /**
     * Tenant ID
     */
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

    /**
     * Get object as string
     * @return - string
     */
    public String toString() {
        return String.format("\nTenant (id: %d; name: %s)", this.id, this.name);
    }
}
