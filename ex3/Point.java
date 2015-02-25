import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

        @Override
        public int compare(Point p1, Point p2) {
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);
            double slopeDiff = slope1 - slope2;
            if (Double.isNaN(slopeDiff) || slopeDiff == 0) {
                return 0;
            } else if (slopeDiff > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    };

    private final int x; // x coordinate
    private final int y; // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        int dy = that.y - this.y;
        int dx = that.x - this.x;
        if (dx == 0) {
            if (dy == 0) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        } else if (dy == 0) {
            return 0d;
        }
        return (double) dy / dx;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        int yDiff = this.y - that.y;
        if (yDiff == 0) {
            return this.x - that.x;
        }
        return yDiff;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        // StdOut.println(new Point(0, 0).SLOPE_ORDER.compare(new Point(1, 10),
        // new Point(0, 1)));
    }
}
