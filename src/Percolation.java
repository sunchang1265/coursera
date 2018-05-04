import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Random;

public class Percolation {
    private Boolean[][] board;
    private int n;
    private int[] id;
    private int[] size;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        this.n = n;
        board = new Boolean[n][n];
        id = new int[n * n + 2];
        size = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = false;
                int index = i * n + j;
                id[index] = index;
                size[index] = 1;
            }
        }
        id[n+1] = id[n] + 1; //top
        id[n+2] = id[n] + 2; //bottom
    }

    public void open(int row, int col) {
        int x = row -1;
        int y = col -1;
        if(board[x][y] == false) {
            board[x][y] = true;
            if (row == 1) {
                union(getID(x, y), id[n + 1]);
            }
            if (row == n) {
                union(getID(x, y), id[n + 2]);
            }

            if (row > 1 && board[x - 1][y]) union(getID(x, y), getID(x - 1, y));//connect above
            if (col > 1 && board[x][y - 1]) union(getID(x, y), getID(x, y - 1));//connect left
            if (col < n && board[x][y + 1]) union(getID(x, y), getID(x, y + 1));//connect right
            if (row < n && board[x + 1][y]) union(getID(x, y), getID(x + 1, y));//connect below
        }
        if(percolates()){
            System.out.println("Percolated");
        }

    } // open site (row, col) if it is not open already

    private void union(int p, int q) {
        if(size[p] >= size[q]) {
            id[root(q)] = root(p);
            size[p] += size[q];
        } else {
            id[root(p)] = root(q);
            size[q] += size[p];
        }
    }

    private int root(int i){
        while(i != id[i]){
            i = id[i];
        }
        return id[i];
    }

    private int getID(int x, int y){
        return x * n + y;
    }

    public boolean isOpen(int row, int col) {
        return board[row - 1][col - 1];
    } // is site (row, col) open?

    public boolean isFull(int row, int col) {
        int index = getID(row-1, col-1);
        return root(id[n+1]) == root(index);
    } // is site (row, col) full?

    public int numberOfOpenSites() {
        int num = 0;
        for (Boolean[] row : board) {
            for (Boolean b : row) {
                if (b)
                    num++;
            }
        }
        return num;
    } // number of open sites

    public boolean percolates() {
        return root(id[n+1]) == root(id[n+2]);
    } // does the system percolate?
}