package Model;

/**
 * Dispatcher class implementation
 */
public class Dispatcher {
    /**
     * Name
     */
    private String name;

    /**
     * Dispatcher ID
     */
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

    /**
     * Get object as string
     * @return - string
     */
    public String toString() {
        return String.format("\nDispatcher (id: %d; name: %s)", this.id, this.name);
    }
}
