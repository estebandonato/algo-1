
public class Fast {

    public static void main(String[] args) {
    	 In in = new In(args[0]);
         Point[] points = new Point[Integer.parseInt(in.readLine())];
         int i = 0;
         while (in.hasNextLine()) {
             String[] coord = in.readLine().trim().split("\\s+");
             points[i++] = new Point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
         }
         Distance[] distances = new Distance[points.length - 1];
         StdDraw.setXscale(0, 32768);
         StdDraw.setYscale(0, 32768);
         
         for (i = 0; i < points.length; i++) {
        	 points[i].draw();
        	 int idx = 0;
        	 for (int j = 0; j < points.length; j++) {
        		 if (i != j) {
        			 distances[idx++] = new Distance(points[i].slopeTo(points[j]), j);
        		 }
        	 }
        	 
        	 Merge.sort(distances);
        	 
        	 double lastSlope = 0;
        	 int pointsInSegment = 0;
        	 boolean validSegment = false;
        	 for (int j = 0; j < distances.length; j++) {
        		 if (validSegment && distances[j].slope == lastSlope) {
        			 pointsInSegment++;
        		 } else if (j == 0 || distances[j].slope != lastSlope) {
        			 if (validSegment && pointsInSegment >= 4) {
        				 //print & draw last 3 points + pivot
        				 Object[] pis = new Object[pointsInSegment];
        				 pis[0] = points[i];
        				 String print = "%s";
        				 idx = 1;
        				 for (int z = j - pointsInSegment + 1; z < j; z++ ) {
        					 print += " -> %s";
        					 points[i].drawTo(points[distances[z].pointIdx]);
        					 pis[idx++] = points[distances[z].pointIdx];
        				 }
        				 StdOut.printf(print + "\n", pis);
        			 }
        			 lastSlope = distances[j].slope;
        			 pointsInSegment = 2;
        			 validSegment = distances[j].pointIdx > i;
        		 }
        	 }
         }
    }
    
    private static class Distance implements Comparable<Distance> {
    	private double slope;
    	private int pointIdx;
    	
    	Distance(double slope, int pointIdx) {
    		this.slope = slope;
    		this.pointIdx = pointIdx;
    	}

		@Override
		public int compareTo(Distance d) {
			double slopeDiff = this.slope - d.slope;
            if (Double.isNaN(slopeDiff) || slopeDiff == 0) {
                return 0;
            } else if (slopeDiff > 0) {
                return 1;
            } else {
                return -1;
            }
		}
    }

}
