public class PercolationStats{
   private double mean;
   private double sigma;
   private int T;
   
   public PercolationStats(int N, int T){     // perform T independent experiments on an N-by-N grid
       this.T = T;
       if(N <=0 || T <=0){
           throw new IllegalArgumentException();
       }
       double[] probs = new double[T];
       for(int i=0; i<T; i++){
           probs[i] = calculateProb(N);
       }
       
       mean = StdStats.mean(probs);
       sigma = StdStats.stddev(probs);
   }
   
   private double calculateProb(int n){
       Percolation perc = new Percolation(n);
       int size = n*n;
       int opened=0;
       while(! perc.percolates()){
           int idx = StdRandom.uniform(size);
           int i = idx / n;
           int j = idx - i*n;
           i++;
           j++;
           if(! perc.isOpen(i,j)){
               opened++;
               perc.open(i,j);
           }
       }
       return (double)opened/size;
   }
   
   public double mean(){                      // sample mean of percolation threshold
       return mean;
   }
   public double stddev(){                  // sample standard deviation of percolation threshold
       return sigma;
   }
   public double confidenceLo(){              // low  endpoint of 95% confidence interval
       return mean() - 1.96*stddev()/Math.sqrt(T);
   }
       
   public double confidenceHi(){              // high endpoint of 95% confidence interval
       return mean() + 1.96*stddev()/Math.sqrt(T);
   }

   public static void main(String[] args){    // test client (described below)
       PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
       StdOut.printf("mean                    = %f\n", stats.mean());
       StdOut.printf("stddev                  = %f\n", stats.stddev());
       StdOut.printf("95%% confidence interval = %f, %f\n", stats.confidenceLo(), stats.confidenceHi());
   }
}