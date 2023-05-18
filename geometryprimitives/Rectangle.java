// ID 209327279
package geometryprimitives;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
// ID 209327279
/** public class geometryprimitives.Rectangle.
 * @author Noa Tal
 * The class geometryprimitives.Rectangle have 4 lines and 4 points.
 */
public class Rectangle {
    private Point upperLeft;
    private Point lowerRight;
    private Color color;
    private double width;
    private double height;

    /**
     * constructor geometryprimitives.Rectangle by the upper left point - location and width and height.
     * @param upperLeft the upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.lowerRight = new Point(upperLeft.getX() + width,
                upperLeft.getY() + height);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * this method creates a list of the rectangle sides.
     * @return edgeList a list of lines.
     */
    public List<Line> edgesList() {
        List<Line> edgesList = new ArrayList<>();
        Line l1 = new Line(this.upperLeft, new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()));
        Line l2 = new Line(this.upperLeft, new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height));
        Line l3 = new Line(this.lowerRight, new Point(this.lowerRight.getX(), this.lowerRight.getY() - this.height));
        Line l4 = new Line(this.lowerRight, new Point(this.lowerRight.getX() - this.width, this.lowerRight.getY()));
        edgesList.add(l1);
        edgesList.add(l2);
        edgesList.add(l3);
        edgesList.add(l4);
        return edgesList;
    }

    /**
     * this method return a (possibly empty) List of intersection points with the specified line.
     * @param line the line
     * @return intersectionList a list of intersection points.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionList = new ArrayList<>();
        for (int i = 0; i < 4; i++) { //check intersections with the rectangle edges.
            if (this.edgesList().get(i).isIntersecting(line)) {
                Point p = this.edgesList().get(i).intersectionWith(line);
                if (p != null) {
                    intersectionList.add(p); //if intersecting add the intersection point to the list.
                }
            }
        }
        return intersectionList;
    }

    /**
     * This method returns the width of a rectangle.
     * @return the width of rectangle.
     */
    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }
    /**
     * This method returns the height of a rectangle.
     * @return the height of rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * This method returns the upper left-point of a rectangle.
     * @return upper-left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * This method returns the lower right-point of a rectangle.
     * @return lower-right point.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * set color method.
     * @param c ths color
     */
    public void setColor(Color c) {
        this.color = c;
    }
}

