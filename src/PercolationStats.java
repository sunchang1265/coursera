import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private double[] fractions;
    private int trials;
    public PercolationStats(int n, int trials){
        if(n<=0 || trials <=0)
            throw new java.lang.IllegalArgumentException();
        int size = n*n;
        this.trials = trials;
        fractions = new double[trials];
        for (int i=0; i<trials; i++){
            Percolation p = new Percolation(n);
            int row,col;
            while (!p.percolates()){
                row = StdRandom.uniform(1, n+1);
                col = StdRandom.uniform(1, n+1);
                p.open(row, col);
            }
            double fraction = (double)p.numberOfOpenSites()/size;
            fractions[i] = fraction;

            //StdOut.println("fraction(" + i + "): " + fractions[i]);
        }
    } // perform trials independent experiments on an n-by-n grid
    public double mean() {
        return StdStats.mean(fractions);
    } // sample mean of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    } // sample standard deviation of percolation threshold
    public double confidenceLo() {
        double mValue = mean();
        double stdValue = stddev();
        double sqrRoot = Math.sqrt(trials);
        return mValue - (1.96 * stdValue)/sqrRoot;
    } // low  endpoint of 95% confidence interval
    public double confidenceHi(){
        double mValue = mean();
        double stdValue = stddev();
        double sqrRoot = Math.sqrt(trials);
        return mValue + (1.96 * stdValue)/sqrRoot;
    } // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        int n,trials;
        if(args.length >= 2) {
            n = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
            PercolationStats ps = new PercolationStats(n, trials);
            String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
            StdOut.println("mean: " + ps.mean());
            StdOut.println("stddev: " + ps.stddev());
            StdOut.println("95% confidence interval: " + confidence);
        }
    } // test client (described below)
}
