
public class Brute {

    public static void main(String[] args) {
        Point[] points = new Point[0];
        for (int p = 0; p < points.length; p++) {
            for (int q = p + 1; q < points.length; q++) {
                for (int r = q + 1; r < points.length; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].SLOPE_ORDER.compare(points[q], points[r]) == 0 && 
                                points[p].SLOPE_ORDER.compare(points[q], points[s]) == 0) {
                            //TODO print line segment
                        }
                    }
                }
            }
        }

    }

}
