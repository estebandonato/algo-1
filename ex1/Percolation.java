/**
 * Auto Generated Java Class.
 */
public class Percolation {
    
    private WeightedQuickUnionUF qu;
    private boolean[][] openSite;
    private int N;
    private int sites;
    
    public Percolation(int N){             // create N-by-N grid, with all sites blocked
        if(N <= 0)
            throw new IllegalArgumentException();
        this.N = N;
        sites = N*N+2;
        qu = new  WeightedQuickUnionUF(sites);
        openSite = new boolean[N][N];
    }
    
    public void open(int i, int j){        // open site (row i, column j) if it is not open already
        checkMatrixIndex(i);
        checkMatrixIndex(j);
        if(! isOpen(i,j)){
            openSite[i-1][j-1] = true;
            if(j+1<=N && isOpen(i,j+1)){
                qu.union(matrixToArrayIndex(i,j), matrixToArrayIndex(i,j+1));
            }
            if(i+1<=N && isOpen(i+1,j)){
                qu.union(matrixToArrayIndex(i,j), matrixToArrayIndex(i+1,j));
            }
            if(j-1>=1 && isOpen(i,j-1)){
                qu.union(matrixToArrayIndex(i,j), matrixToArrayIndex(i,j-1));
            }
            if(i-1>=1 && isOpen(i-1,j)){
                qu.union(matrixToArrayIndex(i,j), matrixToArrayIndex(i-1,j));
            }
            if(i+1==N+1){
                qu.union(matrixToArrayIndex(i,j), sites-1);
            }
            if(i-1==0){
                qu.union(matrixToArrayIndex(i,j), 0);
            }
        }
    }
    
    public boolean isOpen(int i, int j){   // is site (row i, column j) open?
        checkMatrixIndex(i);
        checkMatrixIndex(j);
        return openSite[i-1][j-1];
    }
    public boolean isFull(int i, int j){    // is site (row i, column j) full?
        checkMatrixIndex(i);
        checkMatrixIndex(j);
        return qu.connected(0,matrixToArrayIndex(i,j));
    }
    public boolean percolates(){   // does the system percolate?
        return qu.connected(0,sites-1);
    }
    
    private void checkMatrixIndex(int i){
        if(i < 1 || i > N)
            throw new IndexOutOfBoundsException("" + i);
    }
    
    private int matrixToArrayIndex(int i,int j){
        return (i-1)*N + j;
    }
    
    public static void main(String[] args) {
        int n=5;
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
        System.out.println((double)opened/size);
    }
}
