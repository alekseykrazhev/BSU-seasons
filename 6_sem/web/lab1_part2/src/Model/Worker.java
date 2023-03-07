package Model;

/**
 * Implementation of worker class
 */
public class Worker {
    /**
     * Name
     */
    private String name;

    /**
     * Tenant ID
     */
    public int id;

    /**
     * Type of works
     */
    public int id_type;

    /**
     * Is busy?
     */
    private boolean busy;

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
