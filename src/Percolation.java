import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create n-by-n grid, with all sites blocked
    public Percolation(int n){

    }
    public void open(int row, int col){}    // open site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        return false;
    } // is site (row, col) open?
    public boolean isFull(int row, int col) {
        return false;
    } // is site (row, col) full?
    public int numberOfOpenSites() {
        return 0;
    } // number of open sites
    public boolean percolates(){
        return false;
    } // does the system percolate?

    public static void main(String[] args){

    } // test client (optional)
}