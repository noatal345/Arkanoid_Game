// ID 209327279
package geometryprimitives;

import java.util.List;

/**
 * public class geometryprimitives.Line.
 * the class line (segment) has two points, length,
 * may intersect with other lines, check if equals to another line.
 */
public class Line {
    private Point s;
    private Point e;
    private double m;
    private double b;

    /**
     * constructors from 2 points.
     *
     * @param start the first point that defines the line.
     * @param end   the second point that defines the line.
     */
    public Line(Point start, Point end) {
        this.s = start;
        this.e = end;

        if (this.parallelToY()) {
            this.m = this.s.getX(); //not for use
        } else {
            this.m = (this.s.getY() - this.e.getY()) / (this.s.getX() - this.e.getX()); //slope calc
        }
        this.b = -this.m * this.s.getX() + this.s.getY();
    }

    /**
     * constructor from four coordinates.
     *
     * @param x1 the x coordinate of the first point.
     * @param y1 the y coordinate of the first point.
     * @param x2 the x coordinate of the second point.
     * @param y2 the y coordinate of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.s = new Point(x1, y1);
        this.e = new Point(x2, y2);

        if (this.s.getX() == this.e.getX()) {
            this.m = this.s.getX(); //not for use
        } else {
            this.m = (this.s.getY() - this.e.getY()) / (this.s.getX() - this.e.getX()); //slope calc
        }
        this.b = -this.m * this.s.getX() + this.s.getY();
    }

    /**
     * this method measures the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.s.distance(this.e);
    }

    /**
     * this method calculates the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        Point mid = new Point((this.s.getX() + this.e.getX()) / 2, (this.s.getY() + this.e.getY()) / 2);
        return mid;
    }

    /**
     * this method returns the first point that defines the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.s;
    }

    /**
     * this method returns the second point that defines the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.e;
    }

    /**
     * this method calculates the maximum value between two x coordinate.
     *
     * @return the max x value.
     */
    public double maxValueX() {
        return Math.max(this.e.getX(), this.s.getX());
    }

    /**
     * this method calculates the maximum value between two y coordinate.
     *
     * @return the max y value.
     */
    public double maxValueY() {
        return Math.max(this.e.getY(), this.s.getY());
    }

    /**
     * this method calculates the minimum value between two x coordinate.
     *
     * @return the max y value.
     */
    public double minValueX() {
        return Math.min(this.e.getX(), this.s.getX());
    }

    /**
     * this method calculates the minimum value between two y coordinate.
     *
     * @return the max y value.
     */
    public double minValueY() {
        return Math.min(this.e.getY(), this.s.getY());
    }

    /**
     * this method checks if 2 lines have the same equation.
     *
     * @param other the other line to compare.
     * @return true is the lines are the same and false otherwise.
     */
    public boolean sameEquation(Line other) {
        if (this.parallelToY() && other.parallelToY()) { //both are parallel to the y axis
            if (this.m == other.m) { //have the same slope(in this case same x value).
                return true;
            }
            return false;
        } else if (this.parallelToY() && !other.parallelToY() || !this.parallelToY() && other.parallelToY()) {
            //different slopes, really parallel
            return false;
        } else {
            if (this.m + this.b == other.m + other.b) { //the same equation not just parallel
                return true;
            }
            return false;
        }
    }


    /**
     * this method checks if a line is paraell to the y axis.
     *
     * @return true if the line is parallel.
     */
    public boolean parallelToY() {
        if (this.s.getX() == this.e.getX()) {
            return true;
        }
        return false;
    }

    /**
     * this method checks if a line is paraell to the x axis.
     *
     * @return true if the line is parallel.
     */
    public boolean parallelToX() {
        if (this.s.getY() == this.e.getY()) {
            return true;
        }
        return false;
    }

    /**
     * this method checks if a 2 given lines and a point share x area.
     *
     * @param other the other line to check intersection with.
     * @param px    the hypothetical point of intersection.
     * @return true if the points share an overlapping area and false otherwise.
     */
    public boolean overlappingArea(Line other, double px) {
        if ((this.minValueX() <= px && px <= this.maxValueX())
                && (px >= other.minValueX() && px <= other.maxValueX())) { //check both ways

            return true;
        }
        return false;
    }

    /**
     * this method checks if a point is on a given line.
     *
     * @param l the line to check if the point is on it.
     * @param p the point to check if is on the line.
     * @return true if the point is on the given line or false otherwise.
     */
    public boolean pointOnLine(Line l, Point p) {
        if (l.s.equals(l.e)) { //l is just a point
            if (p.equals(l.s)) {
                return true;
            }
            return false;
        }
        if (l.parallelToY()) {
            if (l.s.getX() == p.getX()) { //same x
                return (p.getY() >= Math.min(l.s.getY(), l.e.getY()) && p.getY() <= Math.max(l.s.getY(), l.e.getY()));
            }
        }
        if (p.getY() == l.m * p.getX() + l.b) {
            return true;
        }
        return false;
    }

    /**
     * this method checks if two lines are intersecting.
     *
     * @param other the other line to check intersection with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double m1 = this.m;
        double b1 = this.b;
        double m2 = other.m;
        double b2 = other.b;

        double px;
        if (this.s.equals(other.e) || this.s.equals(other.s) || this.e.equals(other.s) || this.e.equals(other.e)) {
            //shared point
            return true;
        }
        if (this.s.equals(this.e) || other.s.equals(other.e)) {
            if (this.s.equals(this.e)) { //this line is a point
                if (pointOnLine(other, this.s)) { //check if the point is on the other line
                    return true;
                }
                return false;
            } else { //other is a point
                if (pointOnLine(this, other.s)) { //check if the point is on this line
                    return true;
                }
                return false;
            }
        }
        if (sameEquation(other)) { // the same slope, the same equation
            if (this.parallelToY()) { //both of the lines are parallel to y axis
                if (this.maxValueY() < other.minValueY() || this.minValueY() > other.maxValueY()) {
                    return false;
                }
                return true;
            } else { //  both are parallel to x axis or have the same slope
                if (this.maxValueX() < other.minValueX() || this.minValueX() > other.maxValueX()) {
                    return false;
                }
                return true;
            }
        } else { //same slope, different lines
            if ((double) m1 == (double) m2 || this.parallelToY() && other.parallelToY()) { //different parallel line.
                return false;
            } else {
                if (this.parallelToY() || other.parallelToY()) { //if one line parallel to y axis
                    if (this.parallelToY()) {
                        px = this.s.getX();
                        if (px == other.s.getX() || px == other.e.getX()) {
                            return true;
                        }
                        if (this.overlappingArea(other, px)) {
                            return true;
                        }
                        return false;
                    } else {
                        px = other.s.getX();
                        if (px == this.s.getX() || px == this.e.getX()) {
                            return true;
                        }
                        if (other.overlappingArea(this, px)) {
                            return true;
                        }
                        return false;
                    }
                } else {
                    px = (b1 - b2) / (m2 - m1);
                    if (this.overlappingArea(other, px)) {
                        return true;
                    }
                    return false;
                }
            }
        }
    }


    /**
     * this method calculating intersection point of two lines.
     *
     * @param other the other line to check intersection with.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double x;
        double y;
        if (isIntersecting(other)) {
            if (this.s.equals(this.e) || other.s.equals(other.e)) { //one of the lines is just a point
                if (this.s.equals(this.e)) { //this line is a point
                    if (pointOnLine(other, this.s)) { //check if the point is on the other line
                        return this.s;
                    } else {
                        return null;
                    }
                } else { //other is a point
                    if (pointOnLine(this, other.s)) { //check if the point is on this line
                        return other.s;
                    } else {
                        return null;
                    }
                }
            }
            if (this.parallelToY() && other.parallelToY()) { //both are parallel to y axis (the same equation)
                if (other.maxValueY() == this.minValueY() || other.minValueY() == this.maxValueY()) {
                    //only one intersection point.
                    if (other.maxValueY() == this.minValueY()) {
                        return new Point(this.s.getX(), this.minValueY());
                    } else { //other.minValueY() == this.maxValueY()
                        return new Point(this.s.getX(), this.maxValueY());
                    }
                } else { // intersect in more than one point.
                    return null; // lines are overlapping
                }
            } else {
                if (this.parallelToY() || other.parallelToY()) { //only one line is parallel to y axis
                    if (this.parallelToY()) { //this is parrallel
                        x = this.s.getX();
                        y = other.m * x + other.b;
                        Point p = new Point(x, y);
                        if (pointOnLine(this, p)) {
                            return new Point(x, y);
                        } else {
                            return null;
                        }
                    } else { //other is parallel
                        x = other.s.getX();
                        y = this.m * x + this.b;
                        Point p = new Point(x, y);
                        if (pointOnLine(other, p)) {
                            return new Point(x, y);
                        } else {
                            return null;
                        }
                    }
                }
                if (this.sameEquation(other)) {
                    if (this.maxValueX() == other.minValueX() || this.minValueX() == other.maxValueX()) {
                        if (other.s.equals(this.s) || other.s.equals(this.e)) {
                            return new Point(other.s.getX(), other.s.getY());
                        } else {
                            return new Point(other.e.getX(), other.e.getY());
                        }
                    } else {
                        return null;
                    }
                } else {
                    x = (this.b - other.b) / (other.m - this.m);
                    y = this.m * x + this.b;
                    return new Point(x, y);
                }
            }
        } else {
            return null;
        }
    }

    /**
     * this method checks if two lines are the same.
     *
     * @param other another line.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.s != null && this.e != null && other.s != null && other.e != null) {
            if ((this.s.equals(other.s) && this.e.equals(other.e))
                    || (this.s.equals(other.e) && this.e.equals(other.s))) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * This method returns the closest intersection point to the start of the line with a rectangle.
     * @param rect is the rectangle to check intersection with.
     * @return point the closest intersection point or null If the line doesn't intersect with the rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionList = rect.intersectionPoints(this); //rectangle intersections
        if (intersectionList.isEmpty()) { //no intersections
            return null;
        } else {
            if (intersectionList.size() < 2) { //Only one intersection.
                return intersectionList.get(0);
            } else { //2 intersections, need to compare distances.
                if (this.s.distance(intersectionList.get(0)) < this.s.distance(intersectionList.get(1))) {
                    return intersectionList.get(0);
                }
                return intersectionList.get(1);
            }
        }
    }


    /**
     * This method checks if a given line contains a given point.
     *
     * @param p the point
     * @return true if the point is on the line
     */
    public boolean isContaining(Point p) {
        return ((p.getX() >= this.minValueX()) && (p.getX() <= this.maxValueX())
                && (p.getY() >= this.minValueY() - 0.0001) && (p.getY() <= this.maxValueY()));

    }
}

