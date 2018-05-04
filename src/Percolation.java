public class Percolation {
    private Boolean[][] board;
    private int n;
    private int[] id;
    private int[] size;
    private int numOfOpen;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n <= 0)
            throw new java.lang.IllegalArgumentException();
        this.n = n;
        board = new Boolean[n][n];
        id = new int[n * n + 2];
        size = new int[n * n + 2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = false;
                int index = i * n + j;
                id[index] = index;
                size[index] = 1;
            }
        }
        id[n * n] = id[n * n - 1] + 1; //top
        id[n * n + 1] = id[n * n] + 1; //bottom
        size[n * n] = 1; //top
        size[n * n + 1] = 1; //bottom

    }

    public void open(int row, int col) {
        if(row < 1 || col < 1 || row > n || col > n)
            throw new java.lang.IllegalArgumentException();
        int x = row -1;
        int y = col -1;
        if(!board[x][y]) {
            board[x][y] = true;
            numOfOpen++;
            if (row == 1) {
                union(id[n*n], getID(x, y));
            }
            if (row == n) {
                union(id[n*n + 1], getID(x, y));
            }

            if (row > 1 && board[x - 1][y]) union(getID(x, y), getID(x - 1, y));//connect above
            if (col > 1 && board[x][y - 1]) union(getID(x, y), getID(x, y - 1));//connect left
            if (col < n && board[x][y + 1]) union(getID(x, y), getID(x, y + 1));//connect right
            if (row < n && board[x + 1][y]) union(getID(x, y), getID(x + 1, y));//connect below
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
        if(row < 1 || col < 1 || row > n || col > n)
            throw new java.lang.IllegalArgumentException();
        return board[row - 1][col - 1];
    } // is site (row, col) open?

    public boolean isFull(int row, int col) {
        if(row < 1 || col < 1 || row > n || col > n)
            throw new java.lang.IllegalArgumentException();
        int index = getID(row-1, col-1);
        return root(id[n*n]) == root(index);
    } // is site (row, col) full?

    public int numberOfOpenSites() {
        return numOfOpen;
    } // number of open sites

    public boolean percolates() {
        return root(id[n*n]) == root(id[n*n+1]);
    } // does the system percolate?


    public void renderBoard(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(board[i][j] + "     ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("ID: ");
        for(int i=0; i<id.length; i++) {
            System.out.print(id[i] + " | ");
        }
        System.out.println();
        System.out.println("Size: ");
        for(int i=0; i<size.length; i++) {
            System.out.print(size[i] + " | ");
        }
        System.out.println();
    }

}