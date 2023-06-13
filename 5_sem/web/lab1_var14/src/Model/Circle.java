package Model;

/**
 * Implementation of Circle class
 * @author Alex Krazhevskiy
 * @version 1.0
 */
public class Circle {

    /**
     * Field to store horizontal coordinate
     */
    private RationalFraction x;

    /**
     * Field to store vertical coordinate
     */
    private RationalFraction y;

    /**
     * Field to store circle's radius
     */
    private RationalFraction radius;

    /**
     * Circle's square
     */
    private double square;

    /**
     * Circle's length
     */
    private double length;

    /**
     * Getter for x coordinate
     * @return x
     */
    public RationalFraction GetX()
    {
        return x;
    }

    /**
     * Getter for y coordinate
     * @return y
     */
    public RationalFraction GetY()
    {
        return y;
    }

    /**
     * Setter for x coordinate
     * @param new_x - new coordinate
     */
    public void SetX(RationalFraction new_x)
    {
        this.x = new_x;
    }

    /**
     * Setter for y coordinate
     * @param new_y - new coordinate
     */
    public void SetY(RationalFraction new_y)
    {
        this.y = new_y;
    }

    /**
     * Getter for circle's radius
     * @return radius
     */
    public RationalFraction GetRadius()
    {
        return radius;
    }

    /**
     * Setter for radius
     * @param new_rad - new radius
     */
    public void SetRadius(RationalFraction new_rad)
    {
        this.radius = new_rad;
        this.square = countSquare();
        this.length = countLength();
    }

    /**
     * Getter for circle's square
     * @return square
     */
    public double GetSquare()
    {
        return square;
    }

    /**
     * Getter for circle's length
     * @return length
     */
    public double GetLength()
    {
        return length;
    }

    /**
     * Method to check whether circles are on the same line
     * @param m - first circle
     * @param k - second circle
     * @return if they're on the same line
     */
    public boolean IsOnTheSameLine(Circle m, Circle k)
    {
        return (this.GetX().toDouble() - m.GetX().toDouble()) / (this.GetY().toDouble() - m.GetY().toDouble()) ==
                (this.GetX().toDouble() - k.GetX().toDouble()) / (this.GetY().toDouble() - k.GetY().toDouble());
    }

    /**
     * Constructor for Circle class
     * @param X - horizontal coordinate
     * @param Y - vertical coordinate
     * @param Radius - circle's radius
     */
    public Circle(RationalFraction X, RationalFraction Y, RationalFraction Radius)
    {
        this.x = X;
        this.y = Y;
        this.radius = Radius;
        this.length = countLength();
        this.square = countSquare();
    }

    /**
     * Method to count circle's length
     * @return circle length
     */
    private double countLength()
    {
        return Math.abs(2 * Math.PI * this.GetRadius().toDouble());
    }

    /**
     * Method to count circle's square
     * @return circle square
     */
    private double countSquare()
    {
        return Math.PI * Math.pow(GetRadius().toDouble(), 2);
    }

    @Override
    public String toString()
    {
        return String.format("Circle: {\nX = %s\nY = %s\nradius = %s\nlength = %.2f\nsquare = %.2f }\n",
                x.toString(),
                y.toString(),
                radius.toString(),
                length,
                square);
    }

}
