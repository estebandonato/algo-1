
public class Brute {

    public static void main(String[] args) {
        In in = new In(args[0]);
        Point[] points = new Point[Integer.parseInt(in.readLine())];
        int i = 0;
        while (in.hasNextLine()) {
            String[] coord = in.readLine().trim().split("\\s+");
            points[i++] = new Point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
        }
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (int p = 0; p < points.length; p++) {
            points[p].draw();
            for (int q = p + 1; q < points.length; q++) {
                for (int r = q + 1; r < points.length; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].SLOPE_ORDER.compare(points[q], points[r]) == 0 && 
                                points[p].SLOPE_ORDER.compare(points[q], points[s]) == 0) {
                            StdOut.printf("%s -> %s -> %s -> %s\n", points[p], points[q], 
                            		points[r], points[s]);
                            points[p].drawTo(points[q]);
                            points[p].drawTo(points[r]);
                            points[p].drawTo(points[s]);
                        }
                    }
                }
            }
        }

    }

}
