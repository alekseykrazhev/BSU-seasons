package Model;

/**
 * Implementation of work plan class
 */
public class WorkPlan {
    /**
     * Name
     */
    private String name;

    /**
     * WorkPlan ID
     */
    public int id;

    /**
     * Worker for the job
     */
    public Worker worker;

    /**
     * Type of work
     */
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
     * Get object as string
     * @return - string
     */
    public String toString() {
        return String.format("\nWork plan (id: %d; name: %s, type of work: %d)", this.id, this.name, this.id_type);
    }
}
