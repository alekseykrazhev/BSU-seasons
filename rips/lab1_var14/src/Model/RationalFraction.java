package Model;

/**
 * Implementation of Rational Fraction class
 * @author Alex Krazhevskiy
 * @version 1.0
 */
public class RationalFraction {

    /**
     * Field x - numerator
     */
    private final double x;

    /**
     * Field y - denominator
     */
    private final double y;

    /**
     * Getter for numerator (x)
     * @return numerator
     */
    public double GetNumerator() {
        return x;
    }

    /**
     * Getter for denominator (y)
     * @return denominator
     */
    public double GetDenominator() {
        return y;
    }

    /**
     * Constructor for Rational Fraction class
     * Creates Rational Fraction object with (x, y) pair
     * @param x - numerator
     * @param y - denominator
     */
    public RationalFraction(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Default constructor to create 0/1 fraction
     */
    public RationalFraction() {
        this.x = 0;
        this.y = 1;
    }

    public double toDouble() {
        return this.x / this.y;
    }
    @Override
    public String toString() {
        return x + " / " + y;
    }
}
