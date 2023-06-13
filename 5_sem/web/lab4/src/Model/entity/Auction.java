package Model.entity;

/**
 * Auction class implementation
 */
public class Auction {
    private int id;
    private Lot lot;
    private int winnerBrockerId;

    /**
     * Default constructor
     */
    public Auction() {
    }

    /**
     * Constructor with params
     */
    public Auction(int id, Lot lot, int winnerBrockerId) {
        this.id = id;
        this.lot = lot;
        this.winnerBrockerId = winnerBrockerId;
    }

    public int getId() {
        return id;
    }

    synchronized public void setId(int id) {
        this.id = id;
    }

    public Lot getLot() {
        return lot;
    }

    synchronized public void setLot(Lot lot) {
        this.lot = lot;
    }

    public int getWinnerBrockerId() {
        return winnerBrockerId;
    }

    synchronized public void setWinnerBrockerId(int winnerBrockerId) {
        this.winnerBrockerId = winnerBrockerId;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", lot=" + lot +
                ", winnerBrockerId=" + winnerBrockerId +
                '}';
    }
}